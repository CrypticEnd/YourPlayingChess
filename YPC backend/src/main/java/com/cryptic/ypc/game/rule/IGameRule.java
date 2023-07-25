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
	 * This checks if a movie is valid given parameters. If move is valid will work
	 * out all the changes made to the board from that move and the board state
	 * after the move is completed
	 * 
	 * @param boardState The current boardstate of a game in string format (how it
	 *                   is saved in Databases)
	 * @param move       A single board change, that will possible effect the game
	 *                   board in a number of ways
	 * @param playerTurn Whos turn is it currently for the purposes of checking if
	 *                   move is vaild
	 * @return Returns the given BoardChange as a move object with all the
	 *         boardchanges filled out, and the boardStateAfterMove Set. If move is
	 *         invaild will return null
	 */
	public Move makeMove(BoardState boardState, BoardChange move, Player playerTurn);

	/**
	 * Checks if the game is won and if so returns a player object
	 * 
	 * @param boardState The current boardstate of a game in string format (how it
	 *                   is saved in Databases)
	 * @return The player who won or NONE if no player has won
	 */
	public Player checkGameWinner(BoardState boardState);

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
