package com.ftw.hometerview.place.repository.building;

import static com.ftw.hometerview.core.util.Constants.LOAD_NAME;
import static com.ftw.hometerview.core.util.Constants.NAME;

import com.ftw.hometerview.place.domain.Building;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Building> searchByKeyword(String keyword, Pageable pageable) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where(NAME).regex(keyword, "i"),
            Criteria.where(LOAD_NAME).regex(keyword, "i")
        );

        return mongoTemplate.find(Query.query(criteria).with(pageable), Building.class);
    }
}
