package com.cryptic.ypc.model.attributeConverter;

import com.cryptic.ypc.game.BoardState;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class BoardStateConverter implements AttributeConverter<BoardState, String> {

	@Override
	public String convertToDatabaseColumn(BoardState attribute) {
		return attribute.getBoardState();
	}

	@Override
	public BoardState convertToEntityAttribute(String dbData) {
		return new BoardState(dbData);
	}

}
