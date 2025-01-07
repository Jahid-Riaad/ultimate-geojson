package org.adv.gis.jsonbuilder;


import org.adv.gis.jsonbuilder.geometry.*;
import org.adv.gis.model.GeoJSONObjectDto;
import org.adv.gis.model.geometry.*;
import org.adv.gis.common.BuilderConstants;

public class UltimateGeoJSONBuilder {

	private static final UltimateGeoJSONBuilder INSTANCE = new UltimateGeoJSONBuilder();

	public static UltimateGeoJSONBuilder getInstance() {
		return INSTANCE;
	}

	private UltimateGeoJSONBuilder() {
	}

	public String toGeoJSON(GeoJSONObjectDto geoJsonObjectDto) {
		if (geoJsonObjectDto == null) {
			return BuilderConstants.NULL_VALUE;
		}

		if (geoJsonObjectDto instanceof PointDto) {
			return PointBuilder.getInstance().toGeoJSON((PointDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof LineStringDto) {
			return LineStringBuilder.getInstance().toGeoJSON((LineStringDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof PolygonDto) {
			return PolygonBuilder.getInstance().toGeoJSON((PolygonDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiPointDto) {
			return MultiPointBuilder.getInstance().toGeoJSON((MultiPointDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof MultiPolygonDto) {
			return MultiPolygonBuilder.getInstance().toGeoJSON((MultiPolygonDto) geoJsonObjectDto);
		}

		if (geoJsonObjectDto instanceof GeometryCollectionDto) {
			return GeometryCollectionBuilder.getInstance().toGeoJSON((GeometryCollectionDto) geoJsonObjectDto);
		}

		return BuilderConstants.NULL_VALUE;
	}

}
