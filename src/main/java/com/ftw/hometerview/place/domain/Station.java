package com.ftw.hometerview.place.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.place.controller.dto.StationDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "station")
public class Station extends AbstractDocument {

    @MongoId(FieldType.OBJECT_ID)
    String id;
    String lineNumber;
    String name;
    String lotNumberAddress;
    String roadAddress;
    Double lat;
    Double lon;

    public StationDto.Meta toMeta() {
        return StationDto.Meta.builder()
                .stationId(this.id)
                .lineNumber(this.lineNumber)
                .name(this.name)
                .lotNumberAddress(this.lotNumberAddress)
                .roadAddress(this.roadAddress)
                .lat(this.lat)
                .lon(this.lon)
                .build();
    }

}
