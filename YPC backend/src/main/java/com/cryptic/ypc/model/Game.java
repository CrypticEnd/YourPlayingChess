package com.cryptic.ypc.model;

import java.util.List;

import com.cryptic.ypc.model.enums.GameType;
import com.cryptic.ypc.model.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id")
	private int id;

	@ManyToOne
	private User playerChess;

	@ManyToOne
	private User playerOther;

	private GameType type;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private List<Move> moves;

}
