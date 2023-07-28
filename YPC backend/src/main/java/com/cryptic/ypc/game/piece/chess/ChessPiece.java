package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.mover.IChessMover;
import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.game.piece.BoardPiece;
import com.cryptic.ypc.model.enums.Player;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ChessPiece extends BoardPiece {

	public ChessPiece(Player player) {
		super(player);
	}

	/**
	 * * A method for children of chess piece Checks if passed IMover is of correct
	 * type for chess. Throws expection if not
	 * 
	 * @param mover
	 * @return
	 * @throws IllegalArgumentException If Imover is not a Ichess mover or a child
	 *                                  of
	 */
	protected IChessMover convertIMoverOrThrow(IMover mover)
			throws IllegalArgumentException
	{
		if (mover instanceof IChessMover) return (IChessMover) mover;

		throw new IllegalArgumentException("Chess piece cannot move with a non chess piece mover");
	}
}
