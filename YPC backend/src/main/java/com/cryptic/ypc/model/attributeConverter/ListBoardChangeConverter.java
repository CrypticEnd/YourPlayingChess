package com.cryptic.ypc.model.attributeConverter;

import java.util.ArrayList;
import java.util.List;

import com.cryptic.ypc.game.BoardChange;

import jakarta.persistence.AttributeConverter;

/**
 * Converts a list of board changes to a stirng. Since each board change is
 * stored as a char a string is best to store it
 * 
 * Can be used outside of spring data
 * 
 * @author Cryptic
 *
 */
public class ListBoardChangeConverter implements AttributeConverter<List<BoardChange>, String> {

	@Override
	public String convertToDatabaseColumn(List<BoardChange> attribute) {
		StringBuilder stringBuilder = new StringBuilder();

		attribute.forEach(b -> stringBuilder.append(b.getMove()));

		return stringBuilder.toString();
	}

	@Override
	public List<BoardChange> convertToEntityAttribute(String dbData) {
		List<BoardChange> boardChanges = new ArrayList<>();

		dbData.chars().forEach(c -> boardChanges.add(new BoardChange((char) c)));

		return boardChanges;
	}

}
