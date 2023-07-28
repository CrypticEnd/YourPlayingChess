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
import lombok.Setter;
import lombok.ToString;

/**
 * Not a singleton, has a 'beenChecked' value that may want to be kept uniqe to
 * not certain if this will be used
 * 
 * @author Cryptic
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class King extends ChessPiece {
	private static final String name = "King";

	public King(Player player) {
		super(player);
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		IChessMover chessMover = this.convertIMoverOrThrow(mover);

		return chessMover.moveKing(boardState, this, move);
	}

	@Override
	public BoardPiece clone() {
		return new King();
	}

}
