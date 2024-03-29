package com.ftw.hometerview.place.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.place.controller.dto.BuildingDto;
import java.util.Collections;
import java.util.List;
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
@Document(collection = "building")
public class Building extends AbstractDocument {

    @MongoId(FieldType.OBJECT_ID)
    String id;

    String name;
    String roadAddress;
    String city;
    Double lat;
    Double lon;
    ResidenceType residenceType;

    Double totalRating = 0D;
    List<String> likedUsers = Collections.EMPTY_LIST;

    public enum ResidenceType {
        OFFICETEL, ONEROOM, VILLA, APARTMENT
    }

    public BuildingDto.Meta toMeta() {
        return BuildingDto.Meta.builder()
            .buildingId(this.id)
            .name(this.name)
            .roadAddress(this.roadAddress)
            .build();
    }
}
