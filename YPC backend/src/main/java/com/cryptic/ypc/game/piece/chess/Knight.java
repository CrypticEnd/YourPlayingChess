package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.moveValidation.IMoveValidator;

import lombok.ToString;

@ToString
public final class Knight implements IBoardPiece {
	private static Knight INSTANCE;
	private final byte id = 102;
	private static final String name = "Knight";

	private Knight() {
	}

	public static Knight getKnight() {
		if (INSTANCE == null) {
			INSTANCE = new Knight();
		}

		return INSTANCE;
	}

	@Override
	public Byte getId() {
		return id;
	}

	@Override
	public boolean canMakeMove(IMoveValidator validator, BoardState boardState, char move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}
}
