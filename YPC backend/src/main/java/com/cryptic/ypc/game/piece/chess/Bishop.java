package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.piece.BoardPiece;
import com.cryptic.ypc.model.enums.Player;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public final class Bishop extends ChessPiece {
	private static final String name = "Bishop";

	public Bishop(Player player) {
		super(player);
	}

	@Override
	public BoardPiece clone() {
		return new Bishop();
	}

}
