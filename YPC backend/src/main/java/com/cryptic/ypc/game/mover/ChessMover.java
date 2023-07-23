package com.cryptic.ypc.game.mover;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.game.piece.chess.Bishop;
import com.cryptic.ypc.game.piece.chess.King;
import com.cryptic.ypc.game.piece.chess.Knight;
import com.cryptic.ypc.game.piece.chess.Pawn;
import com.cryptic.ypc.game.piece.chess.Queen;
import com.cryptic.ypc.game.piece.chess.Rook;

public class ChessMover implements IChessMover{
	private boolean canTakePiece = false;
	
	/**
	 * Determines what board side the chess pieces start on
	 * True to start at 0/0, false to start at the opsit 
	 */
	private boolean startZeroZero;

	@Override
	public List<BoardChange> moveBishop(BoardState boardState, Bishop bishop, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardChange> moveKing(BoardState boardState, King king, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardChange> moveKnight(BoardState boardState, Knight knight, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardChange> movePawn(BoardState boardState, Pawn pawn, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardChange> moveQueen(BoardState boardState, Queen queen, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardChange> moveRook(BoardState boardState, Rook rook, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
