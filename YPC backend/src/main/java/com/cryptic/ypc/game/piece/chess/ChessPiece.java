package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.piece.BoardPiece;
import com.cryptic.ypc.model.enums.Player;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ChessPiece extends BoardPiece {
	public ChessPiece(Player player) {
		super(player);
	}
}
