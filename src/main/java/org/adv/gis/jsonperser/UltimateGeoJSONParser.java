package org.adv.gis.jsonperser;

import com.google.gson.*;
import org.adv.gis.model.GeoJSONObjectDto;
import org.adv.gis.common.GeoJSONObjectTypeEnum;
import org.adv.gis.model.PositionDto;
import org.adv.gis.model.geometry.*;
import org.adv.gis.jsonperser.deserializer.*;

public class UltimateGeoJSONParser {

	private Gson gson;

	private static final UltimateGeoJSONParser INSTANCE = new UltimateGeoJSONParser();

	private UltimateGeoJSONParser() {
		initialize();
	}

	public static UltimateGeoJSONParser getInstance() {
		return INSTANCE;
	}

	public GeoJSONObjectDto parse(String geoJson) {
		if (geoJson == null) {
			return null;
		}

		JsonParser parser = new JsonParser();
		JsonElement parsed = parser.parse(geoJson);
		JsonObject jsonObject = parsed.getAsJsonObject();
		String typeString = jsonObject.get("type").getAsString();
		GeoJSONObjectTypeEnum typeEnum = GeoJSONObjectTypeEnum.valueOf(typeString);
		return (GeoJSONObjectDto) gson.fromJson(parsed, typeEnum.getDtoClass());
	}

	private void initialize() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(PositionDto.class, new PositionDeserializer());
		gsonBuilder.registerTypeAdapter(PointDto.class, new PointDeserializer());
		gsonBuilder.registerTypeAdapter(LineStringDto.class, new LineStringDeserializer());
		gsonBuilder.registerTypeAdapter(PolygonDto.class, new PolygonDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPointDto.class, new MultiPointDeserializer());
		gsonBuilder.registerTypeAdapter(MultiPolygonDto.class, new MultiPolygonDeserializer());
		gsonBuilder.registerTypeAdapter(GeometryCollectionDto.class, new GeometryCollectionDeserializer());
		gson = gsonBuilder.create();
	}

}
