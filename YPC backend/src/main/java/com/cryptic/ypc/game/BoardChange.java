package com.cryptic.ypc.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cryptic.ypc.exceptions.BadRequestException;
import com.cryptic.ypc.game.piece.BoardPieceIdMap;

/**
 * A single move is stored as a char this class stores each move and can convert
 * to required data types
 * 
 * @author reece
 *
 */
public class BoardChange {
	private byte maxBoardRange = 63;

	/**
	 * 0-maxBoardRange (63) Represents Board position
	 * 
	 * Out of range is create object of given ID Should relate to a board piece ID
	 */
	private byte moveFrom;

	/**
	 * 0-maxBoardRange (63) Represents Board position maxBoardRange+1 (64)
	 * Represents piece taken/removed
	 */
	private byte moveTo;

	private static Logger logger = LoggerFactory.getLogger(BoardChange.class);

	/**
	 * @param moveTo   First move byte
	 * @param moveFrom Second move byte
	 * @throws BadRequestException If any of these bytes are out of range will throw
	 *                             bad request
	 */
	public BoardChange(byte moveFrom, byte moveTo) throws BadRequestException {
		super();
		this.setMove(moveTo, moveFrom);
	}

	/**
	 * @param c Stores two bytes related to two parts of a board change
	 * @throws BadRequestException If char is out of range throw bad request
	 */
	public BoardChange(char c) throws BadRequestException {
		super();
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
	 * Sets the move of the board pieces
	 * 
	 * @param moveFrom The position moving from (or piece id for creation)
	 * @param moveTo   The position moving to (or one out of range for deletion)
	 * @throws BadRequestException If any of these bytes are out of range will throw
	 *                             bad request
	 */
	private void setMove(byte moveFrom, byte moveTo) throws BadRequestException {
		if (moveFrom >= 0 && moveFrom <= maxBoardRange) {
			this.moveTo = moveFrom;

			logger.debug("First byte is to move from postion: " + moveFrom);

		} else if (BoardPieceIdMap.pieceIdExsists(moveTo)) {
			this.moveFrom = moveFrom;

			logger.debug("First byte is to create a game peice of ID: " + moveFrom);
		} else {
			throw new BadRequestException(
					String.format("Byte move from [%s] is out of range board range [%s-%s] or an avliable board piece",
							moveFrom, 0, this.maxBoardRange));
		}

		// It's +1 because that is piece taken
		if (moveTo >= 0 && moveTo <= maxBoardRange + 1) {
			this.moveTo = moveTo;

			if (moveTo <= maxBoardRange)
				logger.debug("Seccond byte is to move to postion: " + moveFrom);
			else
				logger.debug("Seccond byte is to remove piece");

		} else {
			throw new BadRequestException(
					String.format("Byte move to [%s] is out of range [%s-%s]", moveTo, 0, this.maxBoardRange + 1));
		}

	}
}
