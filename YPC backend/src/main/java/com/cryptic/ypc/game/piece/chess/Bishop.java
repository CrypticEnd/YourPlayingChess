package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.mover.IMover;

public final class Bishop implements IBoardPiece {
	private static Bishop INSTANCE;
	private final byte id = 101;
	private static final String name = "Bishop";

	private Bishop() {
	}

	public static Bishop getBishop() {
		if (INSTANCE == null) {
			INSTANCE = new Bishop();
		}

		return INSTANCE;
	}

	@Override
	public Byte getId() {
		return id;
	}

	@Override
	public boolean canMakeMove(IMover validator, BoardState boardState, char move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}
}
