package com.cryptic.ypc.game;

import org.junit.jupiter.api.BeforeAll;

public class BoardChangeTests {
	private static int boardRange;
	
	@BeforeAll
	public static void setup() {
		boardRange = BoardState.boardSize * BoardState.boardSize;
	}
	
}
