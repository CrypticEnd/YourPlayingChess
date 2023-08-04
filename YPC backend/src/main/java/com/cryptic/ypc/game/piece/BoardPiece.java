package com.cryptic.ypc.game.piece;

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
