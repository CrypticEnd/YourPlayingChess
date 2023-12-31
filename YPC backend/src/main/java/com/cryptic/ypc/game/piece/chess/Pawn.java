package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.mover.IChessMover;
import com.cryptic.ypc.game.mover.IMover;
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
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		IChessMover chessMover = this.convertIMoverOrThrow(mover);
		
		return chessMover.movePawn(boardState, this, move);
	}

	@Override
	public BoardPiece clone() {
		return new Pawn();
	}

}
