package com.cryptic.ypc.game;

import com.cryptic.ypc.exceptions.BadRequestException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardState {
	private final static int boardSize = 8;
	
	/**
	 * This stores the position of each piece. So boardPieces[0] would be the A1
	 * FIXME change to a hasmap 
	 * Converts string to hasmap and vice vera
	 */
	private BoardPiece[] boardPieces;

	public BoardState(BoardPiece[] boardPieces) {
		super();
		this.setBoardPieces(boardPieces);
	}

	/**
	 * Sets the state of the board.
	 * 
	 * @param boardPieces
	 */
	public void setBoardPieces(BoardPiece[] boardPieces) {
		int expectedSize = BoardState.boardSize*BoardState.boardSize;
		
		if (boardPieces.length != expectedSize) {
			throw new BadRequestException(String.format("Board must have %d spaces", expectedSize));
		}

		this.boardPieces = boardPieces;
	}
}
