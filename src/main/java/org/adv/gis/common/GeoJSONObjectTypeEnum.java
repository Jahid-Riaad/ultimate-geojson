package org.adv.gis.common;

import org.adv.gis.model.geometry.*;

public enum GeoJSONObjectTypeEnum {

	Point(PointDto.class), MultiPoint(MultiPointDto.class), Polygon(PolygonDto.class), MultiPolygon(
					MultiPolygonDto.class), GeometryCollection(GeometryCollectionDto.class), LineString(LineStringDto.class);

	private final Class dtoClass;

	private GeoJSONObjectTypeEnum(Class dtoClass) {
		this.dtoClass = dtoClass;
	}

	public Class getDtoClass() {
		return dtoClass;
	}
}
