package com.cryptic.ypc.game.rule;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.mover.ChessVSConnectFourMover;
import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.game.piece.BoardPiece;
import com.cryptic.ypc.game.piece.BoardPieceIdMap;
import com.cryptic.ypc.game.piece.chess.ChessPiece;
import com.cryptic.ypc.game.piece.connectFour.ConnectFourToken;
import com.cryptic.ypc.model.Move;
import com.cryptic.ypc.model.enums.Player;

public final class ChessVSConnectFour implements IGameRule {
	private ChessVSConnectFour INSTANCE;
	/**
	 * Max amount of players in this game rule system (inclusive)
	 */
	private final static int playerMaxAmount = 2;
	/**
	 * Min amount of player in this game rule system (inclusive)
	 */
	private final static int playerMinAmount = 2;
	/**
	 * Here incase wanting to change the rules of connect four
	 */
	private final static int tokensInARowToWin = 4;
	private final IMover mover = new ChessVSConnectFourMover();

	private static Logger logger = LoggerFactory.getLogger(ChessVSConnectFour.class);

	private ChessVSConnectFour() {
	}

	public ChessVSConnectFour getChessVSConnectFour() {
		if (INSTANCE == null)
			INSTANCE = new ChessVSConnectFour();

		return INSTANCE;
	}

	@Override
	public Move makeMove(BoardState boardState, BoardChange move, Player playerTurn) {
		// Get peice at current POS
		BoardPiece piece = boardState.getPieceAtPostion(move.getMoveFrom());

		// If null check if its creating a board token
		if (piece == null) {
			if (!BoardPieceIdMap.pieceIdExsists(move.getMoveFrom())) {
				return null;
			}

			piece = BoardPieceIdMap.getBoardPieceFromId(move.getMoveFrom());

			// If piece is not a connect four token
			if (!(piece instanceof ConnectFourToken)) {
				return null;
			}
		}

		// Check turn valid
		if (playerTurn == Player.ONE) {
			if (!(piece instanceof ChessPiece)) {
				return null;
			}
		}

		if (playerTurn == Player.TWO) {
			if (!(piece instanceof ConnectFourToken)) {
				return null;
			}
		}

		List<BoardChange> changes = piece.move(mover, boardState, move);

		// If no changes made something went wrong
		if (changes.size() == 0) {
			return null;
		}

		Move moveToSave = new Move();
		List<BoardChange> changesToSave = new ArrayList<>();

		// Change board state while changes are needed to make
		while (changes.size() != 0) {
			// Update board state
			boardState.performBoardChanges(changes);

			changesToSave.addAll(changes);

			changes.clear();

			// Check if any connect four peices need to move
			boardState.getAllBoardPieces().stream().filter(b -> b instanceof ConnectFourToken).forEach(token -> {
				changes.addAll(token.move(mover, boardState, null));
			});
		}

		moveToSave.setMove(move);
		moveToSave.setBoardChanges(changesToSave);
		moveToSave.setBoardStateAftermove(boardState);

		return moveToSave;
	}

	@Override
	public Player calculatePlayerTurn(Player whoWentFirst, int amountOfPlayers, int AmountOfTurns) {
		if (whoWentFirst == Player.NONE)
			return Player.NONE;

		// If game cannot start it cannot be a players turn
		if (!this.canStartGame(amountOfPlayers))
			return Player.NONE;

		// Expects only two players so only player one and player two can play
		if (whoWentFirst != Player.ONE || whoWentFirst != Player.TWO)
			return Player.NONE;

		// If amount of turns is even whoever went first goes
		if (AmountOfTurns % 2 == 0) {
			return whoWentFirst;
		} else {
			// Otherwise its the player who did not go first
			if (whoWentFirst == Player.ONE)
				return Player.TWO;
			else {
				return Player.ONE;
			}
		}
	}

	@Override
	public boolean canAddPlayer(int amountOfCurrentPlayers) {
		return amountOfCurrentPlayers < playerMaxAmount;
	}

	@Override
	public boolean canStartGame(int amountOfCurrentPlayers) {
		return amountOfCurrentPlayers >= playerMinAmount && amountOfCurrentPlayers <= playerMaxAmount;
	}

	/**
	 * To win this game as player 1 (chess player) player 2 (connect four) cannot
	 * make another move. To win as a connect four player you need to have four in a
	 * row.
	 * 
	 * If The connect four player cannot move but has a connect four. Connect four
	 * player (player two) wins.
	 */
	@Override
	public Player checkGameWinner(BoardState boardState) {
		BoardPiece[][] boardPieces = boardState.getAllBoardPiecesWithPostions();
		int lastArrayIndex = boardPieces.length - 1;

		logger.debug("---Checking if Chess VS Connect Four Has Wiiner---");

		// ------Connect four win condition------
		logger.debug("If connect four has a winner");

		int[] verticalCounter = new int[boardPieces.length];

		// From top left to top right check each position
		for (int y = 0; y < boardPieces.length; y++) {
			int horizontalCounter = 0;

			for (int x = 0; x < boardPieces.length; x++) {

				BoardPiece tmp = boardPieces[x][y];

				// If not connect four
				if (!this.checkIfPieceIsConnectFourToken(tmp)) {
					continue;
				}

				horizontalCounter++;
				verticalCounter[y]++;

				logger.debug(String.format(
						"Token piece found at [%d/%d] horizontalCounter: %d, verticalCounter for current y: %d", x, y,
						horizontalCounter, verticalCounter[y]));

				// Won from horizontal
				if (horizontalCounter == tokensInARowToWin) {
					logger.debug("Player two won from horizonal");
					return Player.TWO;
				}

				if (horizontalCounter == verticalCounter[y]) {
					logger.debug("Player two won from vertical");
					return Player.TWO;
				}

				if (checkDownwardsHorizontal(boardPieces, x, y)) {
					logger.debug("Player two won from horizontal");
					return Player.TWO;
				}

			}
		}

		// ------Connect four lose condition------
		logger.debug("If player one has won");
		// For each board title in the last row
		// Check if there is a spot for player two to go
		for (int i = 0; i < boardPieces[lastArrayIndex].length; i++) {
			if (boardPieces[lastArrayIndex][i] == null) {
				logger.debug(String.format("Connect four player can still make a move at [%d/%d]", i, lastArrayIndex));
				return Player.NONE;
			}
		}

		// Player one wins because player two didn't win and cannot move
		logger.debug("Player one won");
		return Player.ONE;
	}

	protected boolean checkDownwardsHorizontal(BoardPiece[][] boardPieces, int x, int y) {

		return false;

	}

	protected boolean checkIfPieceIsConnectFourToken(BoardPiece piece) {
		return piece instanceof ConnectFourToken;
	}

}
