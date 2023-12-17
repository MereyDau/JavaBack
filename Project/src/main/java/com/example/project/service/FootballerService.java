package com.example.project.service;

import com.example.project.DTO.FootballerDTO;
import com.example.project.DTO.FootballerResponse;
import com.example.project.model.Footballer;

import java.util.List;

public interface FootballerService {
    FootballerResponse getAllFootballers(int pageNo, int pageSize, String sortBy, String sortOrder);

    List<Footballer> searchFootballersByName(String keyword);

    FootballerDTO getById(long id);

    FootballerDTO newFootballer(FootballerDTO a);

    void deleteById(long id);

    void updateFootballer(long id, FootballerDTO footballerDTO);
}