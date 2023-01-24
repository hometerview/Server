package com.ftw.hometerview.place.repository.company;

import com.ftw.hometerview.place.domain.Company;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CompanyRepositoryCustom {

    List<Company> searchByKeyword(String keyword, Pageable pageable);
    boolean existsByNameAndRoadAddress(String name, String roadAddress);

}
