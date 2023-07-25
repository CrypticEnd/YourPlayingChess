package com.cryptic.ypc.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cryptic.ypc.exceptions.BadRequestException;
import com.cryptic.ypc.game.piece.BoardPieceIdMap;
import com.cryptic.ypc.model.enums.MoveType;

/**
 * A single move is stored as a char this class stores and value checks each
 * move able to convert to required data types
 * 
 * @author reece
 *
 */
public class BoardChange {
	/**
	 * Non inclusive
	 */
	private byte maxBoardRange;

	/**
	 * 0-maxBoardRange (63) Represents Board position
	 * 
	 * Out of range is create object of given ID Should relate to a board piece ID
	 */
	private byte moveFrom;

	/**
	 * 0-maxBoardRange (63) Represents Board position (move to or create)
	 * 
	 * maxBoardRange+1 (64) is remove (if from from is a board pos)
	 * 
	 * out of range board piece ID is upgrade (if first postion is board pos)
	 * 
	 * Represents piece taken/removed
	 */
	private byte moveTo;

	private MoveType moveType;

	private static Logger logger = LoggerFactory.getLogger(BoardChange.class);

	/**
	 * @param moveTo   First move byte
	 * @param moveFrom Second move byte
	 * @throws BadRequestException If any of these bytes are out of range will throw
	 *                             bad request
	 */
	public BoardChange(byte moveFrom, byte moveTo) throws BadRequestException {
		super();

		this.maxBoardRange = BoardState.boardSize * BoardState.boardSize;

		this.setMove(moveTo, moveFrom);
	}

	/**
	 * @param c Stores two bytes related to two parts of a board change
	 * @throws BadRequestException If char is out of range throw bad request
	 */
	public BoardChange(char c) throws BadRequestException {
		super();

		this.maxBoardRange = BoardState.boardSize * BoardState.boardSize;

		this.setMove(c);
	}

	public byte getMoveTo() {
		return moveTo;
	}

	public byte getMoveFrom() {
		return moveFrom;
	}

	public char getMove() {
		return (char) (((moveTo & 0xFF) << 8) + (moveFrom & 0xFF));
	}

	/**
	 * @param c Stores two bytes related to two parts of a board change
	 * @throws BadRequestException If char is out of range throw bad request
	 */
	public void setMove(char c) throws BadRequestException {
		short s = (short) c;

		byte[] moveArr = new byte[] { (byte) (s >>> 8), (byte) s };

		this.setMove(moveArr[0], moveArr[1]);
	}

	/**
	 * Sets the move of the board pieces. And validates it as correct
	 * 
	 * @param moveFrom The position moving from (or piece id for creation)
	 * @param moveTo   The position moving to (or one out of range for deletion)
	 * @throws BadRequestException If any of these bytes are out of range will throw
	 *                             bad request
	 */
	private void setMove(byte moveFrom, byte moveTo) throws BadRequestException {
		boolean firstPostionBoard;

		// Set to invalid at the start
		this.moveType = MoveType.INVALID;

		if (moveFrom >= 0 && moveFrom <= maxBoardRange) {
			firstPostionBoard = true;

			logger.debug("First byte is a board postion: " + moveFrom);

		} else if (BoardPieceIdMap.pieceIdExsists(moveTo)) {
			firstPostionBoard = false;

			logger.debug("First byte is to create a game peice of ID: " + moveFrom);
		} else {
			throw new BadRequestException(
					String.format("Byte move from [%s] is out of range board range [%s-%s] or an avliable board piece",
							moveFrom, 0, this.maxBoardRange));
		}

		// If its another board postion
		if (moveTo >= 0 && moveTo < maxBoardRange) {
			this.moveType = MoveType.MOVE;

			logger.debug("Seccond byte is to move to postion: " + moveFrom);

		}
		// If its a removal
		else if (moveTo == maxBoardRange && firstPostionBoard) {
			this.moveType = MoveType.REMOVE;

			logger.debug("Seccond byte is a removeal");
		} else if (BoardPieceIdMap.pieceIdExsists(moveTo) && firstPostionBoard) {
			this.moveType = MoveType.UPGRADE;

			logger.debug("seccond byte is to upgrade a peice: " + moveTo);
		} else {
			throw new BadRequestException(
					String.format("Byte move to [%s] is out of range [%s-%s]", moveTo, 0, this.maxBoardRange + 1));
		}

		// Set values at the end
		this.moveFrom = moveFrom;
		this.moveTo = moveTo;

	}
}
