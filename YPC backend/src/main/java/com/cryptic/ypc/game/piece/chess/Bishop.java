package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.mover.IChessMover;
import com.cryptic.ypc.game.mover.IMover;
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
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		IChessMover chessMover = this.convertIMoverOrThrow(mover);
		
		return chessMover.moveBishop(boardState, this, move);
	}

	@Override
	public BoardPiece clone() {
		return new Bishop();
	}

}
