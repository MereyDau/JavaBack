package com.example.project.service.impl;

import com.example.project.DTO.FootballerDTO;
import com.example.project.DTO.FootballerResponse;

public interface FootballerService {
    FootballerResponse getAllFootballers(int pageNo, int pageSize, String sortBy, String sortOrder);

    FootballerDTO getById(long id);

    FootballerDTO newFootballer(FootballerDTO a);

    void deleteById(long id);

    void updateFootballer(long id, FootballerDTO footballerDTO);
}