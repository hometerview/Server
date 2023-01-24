package com.ftw.hometerview.place.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import com.ftw.hometerview.place.controller.dto.CompanyDto;
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
@Document(collection = "company")
public class Company extends AbstractDocument {

    @MongoId(FieldType.OBJECT_ID)
    String id;

    String name;
    String roadAddress;
    String province;
    String stationId;
    Double lat;
    Double lon;


    public void setNearliestStation(String stationId) {
        this.stationId = stationId;
    }

    public CompanyDto.Meta toMeta() {
        return CompanyDto.Meta.builder()
            .companyId(this.id)
            .name(this.name)
            .roadAddress(this.roadAddress)
            .build();
    }

}
