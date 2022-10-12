package com.ftw.hometerview.place.repository.company;

import com.ftw.hometerview.place.domain.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends
    MongoRepository<Company, String>, CompanyRepositoryCustom {

}
