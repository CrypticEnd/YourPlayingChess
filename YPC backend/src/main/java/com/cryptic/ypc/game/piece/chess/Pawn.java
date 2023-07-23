package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class Pawn implements IBoardPiece {
	private static Pawn INSTANCE;
	private static final byte id = 100;
	private static final String name = "Pawn";

	private Pawn() {
	}

	public static Pawn getPawn() {
		if (INSTANCE == null) {
			INSTANCE = new Pawn();
		}

		return INSTANCE;
	}

	@Override
	public Byte getId() {
		return id;
	}

	@Override
	public boolean move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}
}
