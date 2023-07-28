package com.cryptic.ypc.game.piece;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.model.enums.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Board piece abstract class . Expects pieces to be owned by a player
 * 
 * @author Cryptic
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class BoardPiece implements Cloneable {
	protected Player player = Player.NONE;

	public abstract List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move);

	public BoardPiece(Player player) {
		super();
		this.player = player;
	}

	/**
	 * Forces each BoardPiece to have a clone method
	 */
	@Override
	public abstract BoardPiece clone();

}
