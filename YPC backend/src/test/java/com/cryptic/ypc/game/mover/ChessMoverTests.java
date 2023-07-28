package com.cryptic.ypc.game.mover;

import org.junit.jupiter.api.BeforeEach;

public class ChessMoverTests {
	private ChessMover mover;
	
	@BeforeEach
	public void setup() {
		// Default values some tests will change these
		mover = new ChessMover(false, false, true);		
	}
	
	
}
