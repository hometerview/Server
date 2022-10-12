package com.ftw.hometerview.place.repository.station;

import com.ftw.hometerview.place.domain.Station;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface StationRepositoryCustom {

    List<Station> searchByKeyword(String keyword, Pageable pageable);

}
