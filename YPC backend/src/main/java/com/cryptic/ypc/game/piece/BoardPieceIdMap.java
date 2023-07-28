package com.cryptic.ypc.game.piece;

import java.util.HashMap;
import java.util.Map;

import com.cryptic.ypc.exceptions.NotFoundException;
import com.cryptic.ypc.game.piece.chess.Bishop;
import com.cryptic.ypc.game.piece.chess.King;
import com.cryptic.ypc.game.piece.chess.Knight;
import com.cryptic.ypc.game.piece.chess.Pawn;
import com.cryptic.ypc.game.piece.chess.Queen;
import com.cryptic.ypc.game.piece.chess.Rook;
import com.cryptic.ypc.game.piece.connectFour.ConnectFourToken;

/**
 * This class is made to store all board pieces and convert between them
 * 
 * @author Cryptic
 *
 */
public final class BoardPieceIdMap {
	/**
	 * Stores the IDs of all the board BoardPieces
	 */
	private static HashMap<Byte, BoardPiece> map;

	/**
	 * initializes the hasmap.Should be called on each method as a test DO NOT
	 * CHANGE THE IDS UNLESS CLEARING DATABSE
	 */
	private static void initialize() {
		if (map != null)
			return;

		map = new HashMap<>();

		// Chess (alphabetical order)
		map.put((byte) 100, new Bishop());
		map.put((byte) 101, new King());
		map.put((byte) 102, new Knight());
		map.put((byte) 103, new Pawn());
		map.put((byte) 104, new Queen());
		map.put((byte) 105, new Rook());

		// Connect Four
		map.put((byte) 106, new ConnectFourToken());

	}

	public static Boolean pieceIdExsists(byte id) {
		initialize();

		return map.containsKey(id);
	}

	public static byte getPieceId(BoardPiece boardPiece) throws NotFoundException {
		initialize();

		for (Map.Entry<Byte, BoardPiece> entry : map.entrySet()) {
			if (entry.getValue().getClass() == boardPiece.getClass())
				return entry.getKey();
		}

		throw new NotFoundException("Board piece Id not found with given boardpeice class: " + boardPiece.getClass());
	}

	public static Class<? extends BoardPiece> getPieceClassObjectById(byte id) throws NotFoundException {
		initialize();

		return find(id).getClass();
	}

	/**
	 * This returns a cloned board piece given its ID. Cloned as to not change saved
	 * hasmap
	 * 
	 * @param id
	 * @return Cloned board piece
	 */
	public static BoardPiece getBoardPieceFromId(byte id) throws NotFoundException {
		initialize();

		BoardPiece boardPiece = find(id);

		return boardPiece.clone();
	}

	private static BoardPiece find(byte id) throws NotFoundException {
		if (map.containsKey(id))
			return map.get(id);

		throw new NotFoundException("Board piece not found with given id: " + id);
	}

}
