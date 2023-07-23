package com.cryptic.ypc.game.piece;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.BoardPiece;
import com.cryptic.ypc.game.mover.IMover;
import com.cryptic.ypc.model.enums.Player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public final class ConnectFourToken extends BoardPiece {
	private static final String name = "Token";

	public ConnectFourToken(Player player) {
		super(player);
	}

	@Override
	public List<BoardChange> move(IMover mover, BoardState boardState, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

}
