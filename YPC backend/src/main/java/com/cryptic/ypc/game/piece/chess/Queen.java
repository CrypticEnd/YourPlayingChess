package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.moveValidation.IMoveValidator;

import lombok.ToString;

@ToString
public final class Queen implements IBoardPiece{
	private static Queen INSTANCE;
	private final byte id = 104;
	private static final String name = "Queen";

	private Queen() {
	}

	public static Queen getQueen() {
		if (INSTANCE == null) {
			INSTANCE = new Queen();
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
