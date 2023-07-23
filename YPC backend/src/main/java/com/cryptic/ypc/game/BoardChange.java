package com.cryptic.ypc.game;

import lombok.Getter;
import lombok.val;

/**
 * A single move is stored as a char this class stores each move and can convert
 * to required data types
 * 
 * @author reece
 *
 */
@Getter
public class BoardChange {
	/**
	 * 0-63 Represents Board position Out of range is create object of given ID
	 * Should relate to a board piece ID
	 */
	private Byte moveTo;

	/**
	 * 0-63 Represents Board position Out of range is error
	 */
	private Byte moveFrom;

	public char getMove() {
		return (char) (((moveTo & 0xFF) << 8) + (moveFrom & 0xFF));
	}

	public void setMove(char c) {
		short s = (short) c;

		byte[] moveArr = new byte[] { (byte) (s >>> 8), (byte) s };

		this.setMoveFrom(moveArr[0]);
		this.setMoveFrom(moveArr[1]);
	}

	public void setMoveTo(byte b) {
		// TODO validation
		this.moveTo = b;
	}

	public void setMoveFrom(byte b) {
		// TODO validation
		this.moveFrom = b;
	}

}
