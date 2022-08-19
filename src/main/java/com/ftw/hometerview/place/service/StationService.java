package com.ftw.hometerview.place.service;

import com.ftw.hometerview.place.controller.dto.StationDto;
import com.ftw.hometerview.place.domain.Station;
import com.ftw.hometerview.place.repository.station.StationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StationService {

    private final StationRepository stationRepository;

    public List<StationDto.Meta> searchByKeyword(String keyword, Pageable pageable) {
        List<Station> stations = this.stationRepository.searchByKeyword(keyword, pageable);
        List<StationDto.Meta> response = stations.stream().map(Station::toMeta).toList();
        return response;
    }

}
