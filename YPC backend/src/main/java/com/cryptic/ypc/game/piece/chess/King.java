package com.cryptic.ypc.game.piece.chess;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.IBoardPiece;
import com.cryptic.ypc.game.mover.IMover;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Not a singleton, has a 'beenChecked' value that may want to be kept uniqe to
 * not certain if this will be used
 * 
 * @author reece
 *
 */
@Getter
@Setter
@ToString
public class King implements IBoardPiece {
	private final byte id = 105;
	private static final String name = "King";
	private Boolean beenChecked = false;

	@Override
	public Byte getId() {
		return id;
	}

	@Override
	public boolean move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}
}
