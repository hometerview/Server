package com.ftw.hometerview.place.repository.station;

import com.ftw.hometerview.place.domain.Station;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends MongoRepository<Station, String>,StationRepositoryCustom {

    List<Station> findAllByIdIn(List<String> ids);

}
