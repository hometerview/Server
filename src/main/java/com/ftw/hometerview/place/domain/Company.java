package com.ftw.hometerview.place.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.place.controller.dto.CompanyDto;
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
@Document(collection = "company")
public class Company extends AbstractDocument {

    @Id
    ObjectId id;

    String name;
    String loadName;
    String province;
    String stationId;
    Double lat;
    Double lon;


    public void setNearliestStation(String stationId) {
        this.stationId = stationId;
    }

    public CompanyDto.Meta toMeta() {
        return CompanyDto.Meta.builder()
            .companyId(String.valueOf(this.id))
            .name(this.name)
            .loadName(this.loadName)
            .build();
    }

}
