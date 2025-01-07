package org.adv.gis.model.geometry;

import org.adv.gis.common.GeoJSONObjectTypeEnum;

import java.util.List;

public class GeometryCollectionDto extends GeometryDto {
	private static final long serialVersionUID = 1L;

	private List<GeometryDto> geometries;

	public List<GeometryDto> getGeometries() {
		return geometries;
	}

	public void setGeometries(List<GeometryDto> geometries) {
		this.geometries = geometries;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.GeometryCollection;
	}
}
