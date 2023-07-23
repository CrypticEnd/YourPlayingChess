package com.cryptic.ypc.game;

import com.cryptic.ypc.game.mover.IMover;

/**
 * Board piece interface.
 * Expects all children to have an ID that in Unique across the game.
 * Each board piece is only needed once so should use singleton design 
 * 
 * 
 * Clained IDs
 * 		100-105 = Chess
 * 
 * 
 * @author reece
 *
 */
public interface IBoardPiece {

	public Byte getId();
	public boolean canMakeMove(IMover validator, BoardState boardState, char move);
	public String getName();
}
