package com.cryptic.ypc.game.rule;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.model.GameType;
import com.cryptic.ypc.model.Move;
import com.cryptic.ypc.model.enums.Player;

/**
 * Game rule implementation in most cases should be a singleton.
 * 
 * Main functionality is to store game rules and handle how the game moves and
 * "plays"
 * 
 * @author reece
 *
 */
public interface IGameRule {
	/**
	 * This makes a move on a gameboard if move can be made. Returns null if mone is
	 * illegal
	 * FIXME need to return a boardstate to update game object 
	 * @param boardState
	 * @param move
	 * @param playerTurn
	 * @return
	 */
	public Move makeMove(String boardState, BoardChange move, Player playerTurn);

	/**
	 * Works out the current player turn based on who went first, amount of players
	 * and amount of turns taken. Returns NONE if data is incorrect
	 * 
	 * @param whoWentFirst
	 * @param amountOfPlayers
	 * @param AmountOfTurns
	 * @return Returns current player turn or NONE if data is incorrect or
	 *         imcomplete
	 */
	public Player calculatePlayerTurn(Player whoWentFirst, int amountOfPlayers, int AmountOfTurns);

	/**
	 * @param amountOfCurrentPlayers
	 * @return
	 */
	public boolean canAddPlayer(int amountOfCurrentPlayers);

	/**
	 * @param amountOfCurrentPlayers
	 * @return
	 */
	public boolean canStartGame(int amountOfCurrentPlayers);
}
