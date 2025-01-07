package org.adv.gis.custom;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.adv.gis.model.GeoJSONObjectDto;
import org.adv.gis.model.geometry.GeometryDto;
import org.adv.gis.jsonperser.UltimateGeoJSONParser;
import org.adv.gis.util.JtsUGeojsonConversionUtil;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

public class CustomUGeojsonDeseirializer<T extends Geometry> extends JsonDeserializer<T> {

    @Override
    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        String geoJson = jsonNode.toString();
        GeoJSONObjectDto geoJSONObjectDto = UltimateGeoJSONParser.getInstance().parse(geoJson);
        JtsUGeojsonConversionUtil jtsUGeojsonConversionUtil = new JtsUGeojsonConversionUtil();
        Geometry geometry = jtsUGeojsonConversionUtil.toJtsGeometry((GeometryDto) geoJSONObjectDto);
        return (T) geometry;
    }
}
