package org.adv.gis.jsonbuilder.geometry;


import org.adv.gis.model.geometry.*;
import org.adv.gis.common.BuilderConstants;

import java.util.HashMap;
import java.util.Map;

public final class CommonGeometryBuilder {

	private static Map<Class<? extends GeometryDto>, GeometryBuilder<?>> builders = new HashMap<>();

	static {
		builders.put(PointDto.class, PointBuilder.getInstance());
		builders.put(LineStringDto.class, LineStringBuilder.getInstance());
		builders.put(PolygonDto.class, PolygonBuilder.getInstance());
		builders.put(MultiPointDto.class, MultiPointBuilder.getInstance());
		builders.put(MultiPolygonDto.class, MultiPolygonBuilder.getInstance());
		builders.put(GeometryCollectionDto.class, GeometryCollectionBuilder.getInstance());
	}

	private CommonGeometryBuilder() {
	}

	/**
	 * Get suitable GeometryBuilder
	 * 
	 * @param geometryDto
	 *            An instance of GeometryDto
	 * @return
	 */
	public static GeometryBuilder getBuilder(GeometryDto geometryDto) {
		if (geometryDto == null) {
			return null;
		}
		return builders.get(geometryDto.getClass());
	}

	/**
	 * Find suitable jsonbuilder and return build result;
	 * 
	 * @param geometryDto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String toGeometryGeoJSON(GeometryDto geometryDto) {
		GeometryBuilder builder = getBuilder(geometryDto);
		if (builder != null) {
			return builder.toGeoJSON(geometryDto);
		}
		return BuilderConstants.NULL_VALUE;
	}

}
