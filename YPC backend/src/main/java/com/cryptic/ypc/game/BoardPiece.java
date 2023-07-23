package com.cryptic.ypc.game;

import java.util.List;

import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.model.enums.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Board piece abstract class .
 * Expects pieces to be owned by a player
 * 
 * @author reece
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class BoardPiece {
	protected Player player = Player.NONE;
		
	public abstract List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move);

	public BoardPiece(Player player) {
		super();
		this.player = player;
	}
}
