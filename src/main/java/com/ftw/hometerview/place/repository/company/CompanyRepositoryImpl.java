package com.ftw.hometerview.place.repository.company;

import static com.ftw.hometerview.core.util.Constants.LOAD_NAME;
import static com.ftw.hometerview.core.util.Constants.NAME;
import static com.ftw.hometerview.core.util.Constants.STATION;

import com.ftw.hometerview.place.domain.Company;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Company> searchByKeyword(String keyword, Pageable pageable) {
        Criteria criteria = new Criteria().orOperator(
            Criteria.where(NAME).regex(keyword, "i"),
            Criteria.where(LOAD_NAME).regex(keyword, "i"),
            Criteria.where(STATION).regex(keyword, "i")
        );

        return mongoTemplate.find(Query.query(criteria).with(pageable), Company.class);
    }

    @Override
    public boolean existsByNameAndLoadName(String name, String loadName) {
        Criteria criteria = Criteria.where(NAME).is(name).and(LOAD_NAME).is(loadName);
        return mongoTemplate.exists(Query.query(criteria), Company.class);
    }

}
