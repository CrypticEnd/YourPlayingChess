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

/**
 * This uses the Visitor pattern For each chess piece has its own move method
 * that is on for the concrete implementation Returns a list of board changes
 * It's own move is one change, but concrete implementation could have removing
 * a piece it landed on or changing it into a another piece
 * 
 * @author Cryptic
 *
 */
public interface IChessMover extends IMover {
	public List<BoardChange> moveBishop(BoardState boardState, Bishop bishop, BoardChange move);

	public List<BoardChange> moveKing(BoardState boardState, King king, BoardChange move);

	public List<BoardChange> moveKnight(BoardState boardState, Knight knight, BoardChange move);

	public List<BoardChange> movePawn(BoardState boardState, Pawn pawn, BoardChange move);

	public List<BoardChange> moveQueen(BoardState boardState, Queen queen, BoardChange move);

	public List<BoardChange> moveRook(BoardState boardState, Rook rook, BoardChange move);
}
