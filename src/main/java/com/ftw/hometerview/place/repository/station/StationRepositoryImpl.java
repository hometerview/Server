package com.ftw.hometerview.place.repository.station;

import static com.ftw.hometerview.core.util.Constants.ROAD_ADDRESS;
import static com.ftw.hometerview.core.util.Constants.NAME;

import com.ftw.hometerview.place.domain.Station;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class StationRepositoryImpl implements StationRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Station> searchByKeyword(String keyword, Pageable pageable) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where(NAME).regex(keyword, "i"),
            Criteria.where(ROAD_ADDRESS).regex(keyword, "i")
        );

        return mongoTemplate.find(Query.query(criteria).with(pageable), Station.class);
    }
}
