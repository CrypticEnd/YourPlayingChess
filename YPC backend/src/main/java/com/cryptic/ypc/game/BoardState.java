package com.cryptic.ypc.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cryptic.ypc.exceptions.BadRequestException;
import com.cryptic.ypc.exceptions.ForbiddenException;
import com.cryptic.ypc.exceptions.NotFoundException;
import com.cryptic.ypc.game.piece.BoardPiece;
import com.cryptic.ypc.game.piece.BoardPieceIdMap;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BoardState {
	protected final static int boardSize = 8;

	private static Logger logger = LoggerFactory.getLogger(BoardState.class);

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
	public BoardState(String boardString)
			throws NotFoundException,
			BadRequestException,
			ForbiddenException {
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
	public void setBoard(String boardString)
			throws NotFoundException,
			BadRequestException,
			ForbiddenException
	{
		this.boardMap = this.convertBoardStringToState(boardString);
	}

	/**
	 * Converts current boardstae into a string
	 * 
	 * @return Returns a string contain each BoardPiece (type, no other data) with a
	 *         position attached
	 */
	public String getBoardState() {
		StringBuilder stringBuilder = new StringBuilder();

		logger.debug("Converting boardState into string");

		for (Map.Entry<Byte, BoardPiece> entry : boardMap.entrySet()) {
			Byte key = entry.getKey();
			BoardPiece val = entry.getValue();

			byte pieceId = BoardPieceIdMap.getPieceId(val);

			char c = (char) (((key & 0xFF) << 8) + (pieceId & 0xFF));

			logger.debug(String.format("Converting BoardPos [%d] and BoardPieceId [%d] into %s", key, pieceId, c));

			stringBuilder.append(c);
		}

		String boardState = stringBuilder.toString();

		logger.debug("Converted String: " + boardState);

		return boardState;
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
		if (boardMap.containsKey(boardPos)) {
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

		logger.debug(String.format("Converting X: %d, Y: %d, into postion: %d", x, y, boardPos));

		return this.getPieceAtPostion(boardPos);
	}

	/**
	 * @return A deep clone list of BoardPiece on the current board
	 */
	public List<BoardPiece> getAllBoardPieces() {
		List<BoardPiece> pieces = new ArrayList<>();

		for (Map.Entry<Byte, BoardPiece> entry : boardMap.entrySet()) {
			BoardPiece boardPiece = entry.getValue();

			BoardPiece pieceClone = boardPiece.clone();
			pieceClone.setPlayer(boardPiece.getPlayer());

			pieces.add(pieceClone);
		}

		return pieces;
	}

	/**
	 * Returns an array of arrays. Where [0][0] is 0,0 on the board. Size is based
	 * on {@link BoardState.boardSize}
	 * 
	 * @return An array of arrays of board pieces [X][Y]. If no board piece exists
	 *         that Position in the array will be null. The board pieces are deep
	 *         cloned
	 */
	public BoardPiece[][] getAllBoardPiecesWithPostions() {
		BoardPiece[][] boardPieces = new BoardPiece[boardSize][boardSize];

		for (Map.Entry<Byte, BoardPiece> entry : boardMap.entrySet()) {
			// Add one because 1/1 is stored as 0/0
			int absolutePos = entry.getKey() + 1;

			int x = absolutePos % boardSize;
			// Take one away because arrays start at index 0
			int y = (absolutePos / boardSize) - 1;

			// Deep cloning the BoardPiece
			BoardPiece boardPiece = entry.getValue();

			BoardPiece pieceClone = boardPiece.clone();
			pieceClone.setPlayer(boardPiece.getPlayer());

			boardPieces[x][y] = pieceClone;
		}

		return boardPieces;
	}

	/**
	 * Perform a list of board changes and updates the state of the board. Assumes
	 * boardChanges are correct and does not perform move validation
	 * 
	 * @param changes A list of changes to be made
	 */
	public void performBoardChanges(List<BoardChange> boardChanges) {
		// TODO implement / complete

		for (BoardChange change : boardChanges) {
			try {
				switch (change.geMoveType()) {
				case MOVE: {

				}
				case CREATE: {
					// TODO how to know who owns the peice
				}
				case REMOVE: {

				}
				case UPGRADE: {

				}
				default:
					throw new IllegalArgumentException("Board change is invaild : " + change);
				}
			}
			catch (IndexOutOfBoundsException e) {
				throw new IllegalArgumentException("Index was out of bounds, move type invaild : " + change);
			}

		}
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
			throws NotFoundException,
			BadRequestException,
			ForbiddenException
	{
		HashMap<Byte, BoardPiece> map = new HashMap<>();

		logger.debug(String.format("Beginning convertion of string [%s] into boardState", boardString));

		// This gets the upper range (non inclusive) as 0 is a position
		int boardRange = boardSize * boardSize;

		char[] boardPiecePos = boardString.toCharArray();

		for (char c : boardPiecePos) {
			byte boardPos = (byte) (c >>> 8);
			byte bordPieceId = (byte) c;

			logger.debug(String.format("Char [%s] becamse [%d] and [%d]", c, boardPos, bordPieceId));

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

			logger.debug("Board postion and ID added to boardState");
		}

		return map;
	}
}
