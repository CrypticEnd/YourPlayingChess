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
	public Move makeMove(String boardState, BoardChange move, Player playerTurn);

	public Player calculatePlayerTurn(Player whoWentFirst, int amountOfPlayers, int AmountOfTurns);

	public boolean canAddPlayer(int amountOfCurrentPlayers);
}
