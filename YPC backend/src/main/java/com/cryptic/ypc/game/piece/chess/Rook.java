package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.moveValidation.IMoveValidator;

import lombok.ToString;

@ToString
public final class Rook implements IBoardPiece {
	private static Rook INSTANCE;
	private final byte id = 103;
	private static final String name = "Rook";

	private Rook() {
	}

	public static Rook getRook() {
		if (INSTANCE == null) {
			INSTANCE = new Rook();
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
