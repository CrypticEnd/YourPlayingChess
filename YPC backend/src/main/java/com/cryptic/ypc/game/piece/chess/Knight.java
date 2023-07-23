package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class Knight extends BoardPiece {
	private static Knight INSTANCE;
	private static final byte id = 102;

	private Knight() {
		this.name = "Knight";
	}

	public static Knight getKnight() {
		if (INSTANCE == null) {
			INSTANCE = new Knight();
		}

		return INSTANCE;
	}

	@Override
	public byte getId() {
		return Knight.id;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}


}
