package org.adv.gis.jsonperser.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.adv.gis.model.PositionDto;
import org.adv.gis.model.geometry.PointDto;
import org.adv.gis.jsonperser.BoundingBoxParser;

import java.lang.reflect.Type;

public class PointDeserializer implements JsonDeserializer<PointDto> {

	@Override
	public PointDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		PointDto point = new PointDto();

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonElement jsonElement = asJsonObject.get("coordinates");
		PositionDto positionDto = context.deserialize(jsonElement, PositionDto.class);
		point.setPosition(positionDto);

		point.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return point;
	}

}
