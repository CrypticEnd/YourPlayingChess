package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class Rook extends BoardPiece {
	private static Rook INSTANCE;
	private static final byte id = 103;

	private Rook() {
		this.name = "Rook";
	}

	public static Rook getRook() {
		if (INSTANCE == null) {
			INSTANCE = new Rook();
		}

		return INSTANCE;
	}

	@Override
	public byte getId() {
		return Rook.id;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}


}
