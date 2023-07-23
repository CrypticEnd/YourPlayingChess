package com.cryptic.ypc.game;

import java.util.List;

import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.model.enums.Player;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Board piece abstract class .
 * Expects all children to have an ID that in Unique across the game.
 * Each board piece is only needed once so should use singleton design 
 * 
 * 
 * Clained IDs
 * 		100-105 - 	Chess
 * 		106 	-	Connect Four
 * 
 * 
 * @author reece
 *
 */
@Getter
@Setter
@ToString
public abstract class IBoardPiece {
	protected Player player;
	protected String name;
	
	public abstract byte getId();
	
	public abstract List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move);
}
