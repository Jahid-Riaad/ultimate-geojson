package org.adv.gis.jsonperser.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.adv.gis.model.PositionDto;
import org.adv.gis.model.geometry.LineStringDto;
import org.adv.gis.model.geometry.PolygonDto;
import org.adv.gis.jsonperser.BoundingBoxParser;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PolygonDeserializer implements JsonDeserializer<PolygonDto> {

	@Override
	public PolygonDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		PolygonDto dto = new PolygonDto();
		List<LineStringDto> rings = new ArrayList<>();
		dto.setLinearRings(rings);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			List<PositionDto> positions = context.deserialize(jsonArray.get(i), positionCollectionType);
			LineStringDto ring = new LineStringDto();
			ring.setPositions(positions);
			rings.add(ring);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
