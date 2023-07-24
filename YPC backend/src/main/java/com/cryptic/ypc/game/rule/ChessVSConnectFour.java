package com.cryptic.ypc.game.rule;

import com.cryptic.ypc.game.BoardChange;
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

	private ChessVSConnectFour() {
	}

	public ChessVSConnectFour getChessVSConnectFour() {
		if (INSTANCE == null)
			INSTANCE = new ChessVSConnectFour();

		return INSTANCE;
	}

	@Override
	public Move makeMove(String boardState, BoardChange move, Player playerTurn) {
		// TODO Auto-generated method stub
		return null;
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

}
