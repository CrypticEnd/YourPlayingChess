package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.piece.BoardPiece;
import com.cryptic.ypc.model.enums.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public final class Pawn extends ChessPiece {
	private static final String name = "Pawn";

	public Pawn(Player player) {
		super(player);
	}

	@Override
	public BoardPiece clone() {
		return new Pawn();
	}
}
