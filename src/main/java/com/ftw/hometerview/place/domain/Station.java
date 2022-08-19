package com.ftw.hometerview.place.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.place.controller.dto.StationDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "station")
public class Station extends AbstractDocument {

    @Id
    ObjectId id;
    String name;
    String loadName;
    Double lat;
    Double lon;

    public StationDto.Meta toMeta() {
        return StationDto.Meta.builder()
            .stationId(String.valueOf(this.id))
            .name(this.name)
            .loadName(this.loadName)
            .build();
    }

}
