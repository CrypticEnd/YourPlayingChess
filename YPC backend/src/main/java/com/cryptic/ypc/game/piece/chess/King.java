package com.cryptic.ypc.game.piece.chess;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.model.enums.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class King extends BoardPiece {
	private static final String name = "King";

	public King(Player player) {
		super(player);
	}
	
	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BoardPiece clone() {
		return new King();
	}



}
