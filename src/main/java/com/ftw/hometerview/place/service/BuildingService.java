package com.ftw.hometerview.place.service;

import com.ftw.hometerview.place.controller.dto.BuildingDto;
import com.ftw.hometerview.place.domain.Building;
import com.ftw.hometerview.place.repository.building.BuildingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BuildingService {

    private final BuildingRepository buildingRepository;

    public List<BuildingDto.Meta> searchByKeyword(String keyword, Pageable pageable) {
        List<Building> buildings = this.buildingRepository.searchByKeyword(keyword, pageable);
        List<BuildingDto.Meta> response = buildings.stream().map(Building::toMeta).toList();
        return response;
    }

}
