package com.ftw.hometerview.place.repository.building;

import com.ftw.hometerview.place.domain.Building;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BuildingRepositoryCustom {

    List<Building> searchByKeyword(String keyword, Pageable pageable);

}
