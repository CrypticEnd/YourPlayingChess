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
import com.cryptic.ypc.model.Move;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract class of chess mover, containing all the basic logic required to
 * play chess. Since all games use chess as a base for movement
 * 
 * @author Cryptic
 *
 */
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class ChessMover implements IMover {
	private boolean canTakePiece;
	private boolean canTakeSelf;

	/**
	 * Determines what board side the chess pieces start on True to start at 0/0,
	 * false to start at the opsit
	 */
	private boolean startZeroZero;

	public Move move(BoardState boardState, BoardChange boardChange) {
		// TODO Auto-generated method stub
		return null;
	}

	protected List<BoardChange> moveBishop(BoardState boardState, Bishop bishop, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	protected List<BoardChange> moveKing(BoardState boardState, King king, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	protected List<BoardChange> moveKnight(BoardState boardState, Knight knight, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	protected List<BoardChange> movePawn(BoardState boardState, Pawn pawn, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	protected List<BoardChange> moveQueen(BoardState boardState, Queen queen, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

	protected List<BoardChange> moveRook(BoardState boardState, Rook rook, BoardChange move) {
		// TODO Auto-generated method stub
		return null;
	}

}
