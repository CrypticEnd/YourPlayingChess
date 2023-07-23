package com.cryptic.ypc.game;

import com.cryptic.ypc.exceptions.BadRequestException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardState {
	/**
	 * This stores the position of each piece. So boardPieces[0] would be the A1
	 */
	private IBoardPiece[] boardPieces;

	public BoardState(IBoardPiece[] boardPieces) {
		super();
		this.setBoardPieces(boardPieces);
	}

	/**
	 * Sets the state of the board.
	 * 
	 * @param boardPieces
	 */
	public void setBoardPieces(IBoardPiece[] boardPieces) {
		if (boardPieces.length != 64) {
			throw new BadRequestException("Board must have 64 spaces");
		}

		this.boardPieces = boardPieces;
	}
}
