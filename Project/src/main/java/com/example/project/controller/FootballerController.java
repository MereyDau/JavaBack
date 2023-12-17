package com.example.project.controller;

import com.example.project.DTO.FootballerDTO;
import com.example.project.DTO.FootballerResponse;
import com.example.project.model.Footballer;
import com.example.project.service.FootballerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/footballers")
public class FootballerController {
    @Autowired
    private FootballerService footballerService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FootballerDTO> getFootballer(@PathVariable("id") int footballerId) {
        return ResponseEntity.ok(footballerService.getById(footballerId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FootballerDTO> newFootballer(@RequestBody Footballer footballer) {
        FootballerDTO footballerDTO = footballerService.newFootballer(new FootballerDTO(footballer.getName(), footballer.getNumber(), footballer.getClub()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(footballerDTO);
    }

    @GetMapping()
    public ResponseEntity<FootballerResponse> getAllFootballers(@RequestParam int pageNo,
                                                                @RequestParam int pageSize,
                                                                @RequestParam String sortBy,
                                                                @RequestParam String sortOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(footballerService.getAllFootballers(pageNo, pageSize, sortBy, sortOrder));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFootballer(@PathVariable("id") Long footballerId) {
        footballerService.deleteById(footballerId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateFootballer(@PathVariable("id") Long footballerId, @RequestBody FootballerDTO footballerDTO) {
        footballerService.updateFootballer(footballerId, footballerDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Footballer>> searchFootballers(@RequestParam String keyword) {
        List<Footballer> footballers = footballerService.searchFootballersByName(keyword);
        return ResponseEntity.ok(footballers);
    }
}