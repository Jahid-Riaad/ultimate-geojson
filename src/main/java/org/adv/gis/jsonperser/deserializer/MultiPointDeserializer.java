package org.adv.gis.jsonperser.deserializer;

import com.google.gson.*;
import org.adv.gis.model.PositionDto;
import org.adv.gis.model.geometry.MultiPointDto;
import org.adv.gis.jsonperser.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MultiPointDeserializer implements JsonDeserializer<MultiPointDto> {

	@Override
	public MultiPointDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiPointDto dto = new MultiPointDto();
		List<PositionDto> positions = new ArrayList<>();
		dto.setPositions(positions);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		for (int i = 0; i < jsonArray.size(); i++) {
			PositionDto position = context.deserialize(jsonArray.get(i), PositionDto.class);
			positions.add(position);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
