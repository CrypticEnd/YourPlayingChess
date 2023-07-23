package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class Pawn extends BoardPiece {
	private static Pawn INSTANCE;
	private static final byte id = 100;

	private Pawn() {
		this.name = "Pawn";
	}

	public static Pawn getPawn() {
		if (INSTANCE == null) {
			INSTANCE = new Pawn();
		}

		return INSTANCE;
	}

	@Override
	public byte getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}


}
