package com.cryptic.ypc.model.attributeConverter;

import com.cryptic.ypc.game.BoardChange;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * Converts a boardchange to char and back for purposes of spring data
 * @author reece
 *
 */
@Converter
public class BoardChangeConverter implements AttributeConverter<BoardChange, Character>{

	@Override
	public Character convertToDatabaseColumn(BoardChange attribute) {
		return attribute.getMove();
	}

	@Override
	public BoardChange convertToEntityAttribute(Character dbData) {
		return new BoardChange(dbData);
	}

}
