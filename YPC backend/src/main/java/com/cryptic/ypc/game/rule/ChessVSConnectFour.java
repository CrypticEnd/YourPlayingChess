package com.cryptic.ypc.game.rule;

import java.util.ArrayList;
import java.util.List;

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
	private final IMover mover = new ChessVSConnectFourMover();

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
			boardState.getAllBoardPieces().stream()
			.filter(b -> b instanceof ConnectFourToken)
			.forEach(token -> {
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

	@Override
	public Player checkGameWinner(BoardState boardState) {
		// TODO Auto-generated method stub
		return null;
	}

}
