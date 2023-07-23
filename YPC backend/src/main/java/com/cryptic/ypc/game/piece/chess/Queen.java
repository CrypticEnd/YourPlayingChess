package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.ToString;

@ToString
public final class Queen extends BoardPiece{
	private static Queen INSTANCE;
	private static final byte id = 104;

	private Queen() {
		this.name = "Queen";
	}

	public static Queen getQueen() {
		if (INSTANCE == null) {
			INSTANCE = new Queen();
		}

		return INSTANCE;
	}

	@Override
	public byte getId() {
		return Queen.id;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}


}
