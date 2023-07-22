package com.cryptic.ypc.model;

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
	
	/**
	 * A Single move is stored as two bytes
	 * Chars in java are two bytes (same as a short)
	 * Using char because board Changes is saved as a string 
	 */
	private char move;
	
	/**
	 * Like move each char in this string is a move on the board
	 * Each move saved as two bytes.
	 * 
	 * Max length 20: 
	 * 		Think this is on the high end 
	 * 		but might be too low in some edge cases 
	 * 
	 * Why have move and board changes:
	 * 		Move is for the user inputed move
	 * 		board changes is for everything that changed on the board 
	 * 		(such as falling pieces) 
	 */
	@Column(length = 20)
	private String boardChanges;
}
