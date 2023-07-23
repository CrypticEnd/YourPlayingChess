package com.cryptic.ypc.game;

import com.cryptic.ypc.game.moveValidation.IMoveValidator;

public interface IBoardPiece {

	public Byte getId();
	public boolean canMakeMove(IMoveValidator validator, BoardState boardState, char move);
	public String getName();
}
