package org.adv.gis.model.geometry;

import org.adv.gis.common.GeoJSONObjectTypeEnum;
import org.adv.gis.model.PositionDto;

import java.util.List;

public class MultiPointDto extends GeometryDto {

	private static final long serialVersionUID = 1L;

	private List<PositionDto> positions;
 
	public List<PositionDto> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionDto> positions) {
		this.positions = positions;
	}

	@Override
	public GeoJSONObjectTypeEnum getGeoJSONObjectType() {
		return GeoJSONObjectTypeEnum.MultiPoint;
	}
}
