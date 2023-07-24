package com.cryptic.ypc.game;

import java.util.HashMap;

import com.cryptic.ypc.exceptions.BadRequestException;
import com.cryptic.ypc.exceptions.ForbiddenException;
import com.cryptic.ypc.exceptions.NotFoundException;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardState {
	protected final static int boardSize = 8;

	/**
	 * Stores a BoardPiece at current position. Protected so it can be used by
	 * children
	 */
	protected HashMap<Byte, BoardPiece> boardMap;

	/**
	 * @param boardString Saved string for board state each char in this string
	 *                    holds the piece position and type of piece first byte is
	 *                    board position second byte is piece id (each char is two
	 *                    bytes)
	 * @return HashMap of byte and BoardPiece. Byte is the board location
	 * @throws NotFoundException   Will throw not found if BoardPieceId is not found
	 *                             within the dir map
	 * @throws BadRequestException Will throw bad request if board postion is out of
	 *                             range see {@link BoardState.boardSize}
	 * @throws ForbiddenException  Throws forbidden if two board pieces are on the
	 *                             same tile. This is impossible, but is still valid
	 *                             data so is forbidden not bad request.
	 */
	public BoardState(String boardString) throws NotFoundException, BadRequestException, ForbiddenException {
		super();
		this.boardMap = this.convertBoardStringToState(boardString);

	}

	/**
	 * @param boardString Saved string for board state each char in this string
	 *                    holds the piece position and type of piece first byte is
	 *                    board position second byte is piece id (each char is two
	 *                    bytes)
	 * @return HashMap of byte and BoardPiece. Byte is the board location
	 * @throws NotFoundException   Will throw not found if BoardPieceId is not found
	 *                             within the dir map
	 * @throws BadRequestException Will throw bad request if board postion is out of
	 *                             range see {@link BoardState.boardSize}
	 * @throws ForbiddenException  Throws forbidden if two board pieces are on the
	 *                             same tile. This is impossible, but is still valid
	 *                             data so is forbidden not bad request.
	 */
	public void setBoard(String boardString) throws NotFoundException, BadRequestException, ForbiddenException {
		this.boardMap = this.convertBoardStringToState(boardString);
	}

	/**
	 * Gets the length / height of a game board
	 * 
	 * @return
	 */
	public static int getBoardsize() {
		return boardSize;
	}

	/**
	 * @param boardPos The absolute position of the board
	 * @return A deep clone of a boardpiece (with correct player set) or null if
	 *         space is empty
	 */
	public BoardPiece getPieceAtPostion(byte boardPos) {
		if (boardMap.containsKey(boardMap)) {
			BoardPiece boardPiece = boardMap.get(boardPos);

			BoardPiece pieceClone = boardPiece.clone();
			pieceClone.setPlayer(boardPiece.getPlayer());

			return pieceClone;
		}

		return null;
	}

	/**
	 * @param x The x position on the board accepting value from 0 to one below max
	 *          board size
	 * @param y The y position on the board accepting value from 0 to one below max
	 *          board size
	 * @return A deep clone of a boardpiece (with correct player set) or null if
	 *         space is empty
	 */
	public BoardPiece getPieceAtPostion(byte x, byte y) {
		byte boardPos = (byte) (y * boardSize + x);

		return this.getPieceAtPostion(boardPos);
	}

	/**
	 * @param boardString Saved string for board state each char in this string
	 *                    holds the piece position and type of piece first byte is
	 *                    board position second byte is piece id (each char is two
	 *                    bytes)
	 * @return HashMap of byte and BoardPiece. Byte is the board location
	 * @throws NotFoundException   Will throw not found if BoardPieceId is not found
	 *                             within the dir map
	 * @throws BadRequestException Will throw bad request if board postion is out of
	 *                             range see {@link BoardState.boardSize}
	 * @throws ForbiddenException  Throws forbidden if two board pieces are on the
	 *                             same tile. This is impossible, but is still valid
	 *                             data so is forbidden not bad request.
	 */
	protected HashMap<Byte, BoardPiece> convertBoardStringToState(String boardString)
			throws NotFoundException, BadRequestException, ForbiddenException {
		HashMap<Byte, BoardPiece> map = new HashMap<>();

		// This gets the upper range (non inclusive) as 0 is a position
		int boardRange = boardSize * boardSize;

		char[] boardPiecePos = boardString.toCharArray();

		for (char c : boardPiecePos) {
			byte boardPos = (byte) (c >>> 8);
			byte bordPieceId = (byte) c;

			// If board pos is out of range 0 - (boardRange-1)
			if (boardPos < 0 || boardPos >= boardRange) {
				throw new BadRequestException(
						String.format("Board postion [%d] is out of range [0-%d]", boardPos, boardRange));
			}

			// If postion already mapped
			if (map.containsKey(boardPos)) {
				throw new ForbiddenException(
						String.format("Cannot place piece at postion [%d] as it is occupied by another piece %s",
								boardPos, map.get(boardPos)));
			}

			// This throws not found
			map.put(boardPos, BoardPieceIdMap.getBoardPieceFromId(bordPieceId));
		}

		return map;
	}
}
