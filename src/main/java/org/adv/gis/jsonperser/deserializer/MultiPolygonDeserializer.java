package org.adv.gis.jsonperser.deserializer;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.adv.gis.model.PositionDto;
import org.adv.gis.model.geometry.LineStringDto;
import org.adv.gis.model.geometry.MultiPolygonDto;
import org.adv.gis.model.geometry.PolygonDto;
import org.adv.gis.jsonperser.BoundingBoxParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MultiPolygonDeserializer implements JsonDeserializer<MultiPolygonDto> {

	@Override
	public MultiPolygonDto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {

		MultiPolygonDto dto = new MultiPolygonDto();
		List<PolygonDto> polygons = new ArrayList<>();
		dto.setPolygons(polygons);

		JsonObject asJsonObject = json.getAsJsonObject();
		JsonArray jsonArray = asJsonObject.get("coordinates").getAsJsonArray();
		Type positionCollectionType = new TypeToken<List<PositionDto>>() {
		}.getType();
		for (int i = 0; i < jsonArray.size(); i++) {
			PolygonDto polygonDto = new PolygonDto();
			List<LineStringDto> rings = new ArrayList<>();
			polygonDto.setLinearRings(rings);
			JsonArray jsonPolygonElement = jsonArray.get(i).getAsJsonArray();
			for (int j = 0; j < jsonPolygonElement.size(); j++) {
				List<PositionDto> positions = context.deserialize(jsonPolygonElement.get(j), positionCollectionType);
				LineStringDto ring = new LineStringDto();
				ring.setPositions(positions);
				rings.add(ring);
			}

			polygons.add(polygonDto);
		}

		dto.setBbox(BoundingBoxParser.parseBbox(asJsonObject, context));

		return dto;
	}

}
