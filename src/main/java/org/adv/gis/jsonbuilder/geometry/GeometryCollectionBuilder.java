package org.adv.gis.jsonbuilder.geometry;


import org.adv.gis.common.GeoJSONObjectTypeEnum;
import org.adv.gis.model.geometry.GeometryCollectionDto;
import org.adv.gis.model.geometry.GeometryDto;
import org.adv.gis.common.BuilderConstants;

import java.util.List;

public class GeometryCollectionBuilder extends GeometryBuilder<GeometryCollectionDto> {

	private static final GeometryCollectionBuilder INSTANCE = new GeometryCollectionBuilder();

	public static GeometryCollectionBuilder getInstance() {
		return INSTANCE;
	}

	private GeometryCollectionBuilder() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.ugeojson.jsonbuilder.geometry.GeometryBuilder#toGeometryGeoJSON(com
	 * .erumi.ugeojson.model.geometry.GeometryDto)
	 */
	@Override
	public String toGeoJSON(GeometryCollectionDto geometryCollection) {
		if (geometryCollection == null) {
			return BuilderConstants.NULL_VALUE;
		}

		StringBuilder builder = initializeBuilder();
		buildTypePart(builder, GeoJSONObjectTypeEnum.GeometryCollection);

		builder.append(BuilderConstants.GEOMETRIES_SPACE);
		builder.append(BuilderConstants.OPEN_BRACKET);

		List<GeometryDto> geometries = geometryCollection.getGeometries();
		if (geometries != null) {
			for (int i = 0; i < geometries.size(); i++) {
				String geometryGeoJSON = CommonGeometryBuilder.toGeometryGeoJSON(geometries.get(i));
				builder.append(geometryGeoJSON);
				if (i < geometries.size() - 1) {
					builder.append(BuilderConstants.COMMA);
				}
			}
		}

		builder.append(BuilderConstants.CLOSE_BRACKET);
		
		buildBbox(builder, geometryCollection.getBbox());
		endBuilder(builder);

		return builder.toString();
	}

}
