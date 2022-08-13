package com.ftw.hometerview.place.domain;

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
public class Company {

    @Id
    ObjectId id;

    String name;
    String loadName;
    String city;
    String station;
    String lat;
    String lon;


    public Company setNearliestStation(String station) {
        this.station = station;
        return this;
    }

}
