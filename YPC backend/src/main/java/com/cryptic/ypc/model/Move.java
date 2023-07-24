package com.cryptic.ypc.model;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.game.BoardState;
import com.cryptic.ypc.model.attributeConverter.BoardChangeConverter;
import com.cryptic.ypc.model.attributeConverter.ListBoardChangeConverter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Move {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "move_id")
	private long id;

	@Convert(converter = BoardChangeConverter.class)
	private BoardChange move;

	/**
	 * Like with move each char in this string is a move on the board Each move saved as
	 * two bytes.
	 * 
	 * Why have move and board changes: Move is for the user inputed move board
	 * changes is for everything that changed on the board (such as falling pieces)
	 */
	@Convert(converter = ListBoardChangeConverter.class)
	private List<BoardChange> boardChanges;
	
	@Transient
	private BoardState boardStateAftermove;
}
