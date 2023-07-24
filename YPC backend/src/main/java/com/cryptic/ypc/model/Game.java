package com.cryptic.ypc.model;

import java.util.List;

import com.cryptic.ypc.game.BoardChange;
import com.cryptic.ypc.model.enums.Player;
import com.cryptic.ypc.model.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id")
	private int id;

	/**
	 * Games have a list of players, right now its only planned to have two player games 
	 * but in the future this will allow it to be more expandable
	 * The game object that handles movement will deside how many players a single game can have.
	 */
	@ManyToMany
	@JoinTable(name = "game_players", 
		joinColumns = @JoinColumn(name = "game_id"), 
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> players;

	@ManyToOne
	private GameType type;

	private Player turnFirst;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private List<Move> moves;

}
