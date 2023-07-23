package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
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
public class King extends BoardPiece {
	private static final byte id = 105;
	private Boolean beenChecked = false;

	public King() {
		this.name = "King";
	}

	@Override
	public byte getId() {
		return King.id;
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

}
