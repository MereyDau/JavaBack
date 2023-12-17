package com.example.project.service.impl;

import com.example.project.DTO.FootballerDTO;
import com.example.project.model.Footballer;
import com.example.project.service.FootballerService;
import com.example.project.DTO.FootballerResponse;
import com.example.project.exception.ResourceNotFoundException;
import com.example.project.repository.FootballerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballerServiceImpl implements FootballerService {
    @Autowired
    private FootballerRepo footballerRepo;

    @Override
    public FootballerResponse getAllFootballers(int pageNo, int pageSize, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Footballer> posts = footballerRepo.findAll(pageable);
        List<Footballer> listOfPosts = posts.getContent();

        List<FootballerDTO> content = listOfPosts.stream().map(footballer -> mapToDTO(footballer)).collect(Collectors.toList());
        FootballerResponse footballerResponse = new FootballerResponse();
        footballerResponse.setContent(content);
        footballerResponse.setPageNo(posts.getTotalPages());
        footballerResponse.setPageSize(posts.getTotalPages());
        footballerResponse.setTotalElements(footballerResponse.getTotalElements());
        footballerResponse.setTotalPages(posts.getTotalPages());
        footballerResponse.setLast(posts.isLast());

        return footballerResponse;
    }

    public List<Footballer> searchFootballersByName(String keyword) {
        return footballerRepo.findByNameContaining(keyword);
    }


//        List<Article> articles = articleRepo.findAll();
//        List<ArticleDTO> articleDTOs = new ArrayList<>();

//        for (Article article : articles) {
//            ArticleDTO articleDTO = new ArticleDTO();
//            articleDTO.setId(article.getId());
//            articleDTO.setName(article.getName());
//            articleDTO.setNumber(article.getNumber());
//            articleDTO.setClub(article.getClub());
//
//            articleDTOs.add(articleDTO);
//        }
//
//        return articleDTOs;
//    }

    @Override
    public FootballerDTO getById(long id) {
        Footballer a = footballerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("article", "id", id));
        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setId(a.getId());
        footballerDTO.setName(a.getName());
        footballerDTO.setNumber(a.getNumber());
        footballerDTO.setClub(a.getClub());

        return footballerDTO;
    }

    @Override
    public FootballerDTO newFootballer(FootballerDTO a) {
        Footballer footballer = new Footballer();
        footballer.setName(a.getName());
        footballer.setNumber(a.getNumber());
        footballer.setClub(a.getClub());
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        footballer.setBorn(timestamp);
        footballerRepo.save(footballer);

        a.setId(footballer.getId());
        return a;
    }

    @Override
    public void deleteById(long id) {
        footballerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("article", "id", id));
        footballerRepo.deleteById(id);
    }


    @Override
    public void updateFootballer(long id, FootballerDTO footballerDTO) {
        footballerRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("article", "id", id));
        Footballer a = footballerRepo.getById(id);
        a.setName(footballerDTO.getName());
        a.setNumber(footballerDTO.getNumber());
        a.setClub(footballerDTO.getClub());
        footballerRepo.save(a);
    }


    private FootballerDTO mapToDTO(Footballer footballer) {
        FootballerDTO footballerDTO = new FootballerDTO();
        footballerDTO.setId(footballer.getId());
        footballerDTO.setName(footballer.getName());
        footballerDTO.setNumber(footballer.getNumber());
        footballerDTO.setClub(footballer.getClub());
        return footballerDTO;
    }


    private Footballer mapToEntity(FootballerDTO footballerDTO) {
        Footballer footballer = new Footballer();
        footballer.setId(footballerDTO.getId());
        footballer.setName(footballerDTO.getName());
        footballer.setNumber(footballerDTO.getNumber());
        footballer.setClub(footballerDTO.getClub());
        return footballer;
    }
}
