package com.cryptic.ypc.game.piece.connectFour;

import com.cryptic.ypc.game.piece.BoardPiece;
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
	public BoardPiece clone() {
		return new ConnectFourToken();
	}

}
