package com.ftw.hometerview.place.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.place.controller.dto.BuildingDto;
import java.util.Collections;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "building")
public class Building extends AbstractDocument {

    @Id
    ObjectId id;

    String name;
    String loadName;
    String city;
    Double lat;
    Double lon;
    ResidenceType residenceType;

    Integer totalRating = 0;
    List<String> likedUsers = Collections.EMPTY_LIST;

    public enum ResidenceType {
        OFFICETEL, ONEROOM, VILLA, APARTMENT
    }

    public BuildingDto.Meta toMeta() {
        return BuildingDto.Meta.builder()
            .buildingId(String.valueOf(this.id))
            .name(this.name)
            .loadName(this.loadName)
            .build();
    }
}
