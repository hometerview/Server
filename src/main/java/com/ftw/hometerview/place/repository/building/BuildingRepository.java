package com.ftw.hometerview.place.repository.building;

import com.ftw.hometerview.place.domain.Building;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends
    MongoRepository<Building, String>, BuildingRepositoryCustom {

}
