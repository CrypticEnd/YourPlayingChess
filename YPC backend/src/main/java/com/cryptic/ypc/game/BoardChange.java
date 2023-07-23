package com.cryptic.ypc.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cryptic.ypc.exceptions.BadRequestException;

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
	 * A range of acceptable inputs for a board piece ID Inclusive
	 */
	private byte gamePieceMinRange;
	private byte gamePieceMaxRange;
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
	 * @param moveTo         First move byte
	 * @param moveFrom       Second move byte
	 * @param gamePeiceRange A size two array containing the min game piece ID and
	 *                       max game piece ID (Inclusive)
	 */
	public BoardChange(byte moveFrom, byte moveTo, byte[] gamePeiceRange) {
		super();

		if (gamePeiceRange.length != 2)
			throw new BadRequestException("BoardChange id range not size 2");

		this.gamePieceMinRange = gamePeiceRange[0];
		this.gamePieceMaxRange = gamePeiceRange[1];
		this.setMove(moveTo, moveFrom);
	}

	/**
	 * @param moveTo            First move byte
	 * @param moveFrom          Second move byte
	 * @param gamePeiceMinRange Min range of piece ID (Inclusive)
	 * @param gamePeiceMaxRange Max range of piece IDs (Inclusive)
	 */
	public BoardChange(byte moveFrom, byte moveTo, byte gamePieceMinRange, byte gamePieceMaxRange) {
		super();
		this.gamePieceMinRange = gamePieceMinRange;
		this.gamePieceMaxRange = gamePieceMaxRange;

		this.setMove(moveTo, moveFrom);
	}

	public BoardChange(char c) {
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

	public void setMove(char c) {
		short s = (short) c;

		byte[] moveArr = new byte[] { (byte) (s >>> 8), (byte) s };

		this.setMove(moveArr[0], moveArr[1]);
	}

	private void setMove(byte moveFrom, byte moveTo) {
		if (moveFrom >= 0 && moveFrom <= maxBoardRange) {
			this.moveTo = moveFrom;

			logger.debug("First byte is to move from postion: " + moveFrom);

		} else if (moveFrom >= this.gamePieceMinRange && moveFrom <= this.maxBoardRange) {
			this.moveFrom = moveFrom;

			logger.debug("First byte is to create a game peice of ID: " + moveFrom);
		} else {
			throw new BadRequestException(String.format(
					"Byte move from [%s] is out of range board range [%s-%s] and game piece id range [%s-%s]", moveFrom,
					0, this.maxBoardRange, this.gamePieceMinRange, this.gamePieceMaxRange));
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
