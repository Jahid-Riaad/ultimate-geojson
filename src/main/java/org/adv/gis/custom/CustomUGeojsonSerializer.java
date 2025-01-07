package org.adv.gis.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.adv.gis.jsonbuilder.UltimateGeoJSONBuilder;
import org.adv.gis.model.geometry.GeometryDto;
import org.adv.gis.util.JtsUGeojsonConversionUtil;
import org.locationtech.jts.geom.Geometry;
import java.io.IOException;

public class CustomUGeojsonSerializer extends JsonSerializer<Geometry> {
    @Override
    public void serialize(Geometry geometry, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        JtsUGeojsonConversionUtil conversionUtil = new JtsUGeojsonConversionUtil();
        GeometryDto geometryDto = conversionUtil.toGeometryDto(geometry);
        String geoJson = UltimateGeoJSONBuilder.getInstance().toGeoJSON(geometryDto);
        jsonGenerator.writeRawValue(geoJson);
    }
}
