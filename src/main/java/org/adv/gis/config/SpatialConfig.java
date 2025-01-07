package org.adv.gis.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.adv.gis.custom.CustomUGeojsonDeseirializer;
import org.adv.gis.custom.CustomUGeojsonSerializer;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpatialConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(Geometry.class, new CustomUGeojsonSerializer());

        simpleModule.addDeserializer(Polygon.class, new CustomUGeojsonDeseirializer<>());
        simpleModule.addDeserializer(MultiPolygon.class, new CustomUGeojsonDeseirializer<>());

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
