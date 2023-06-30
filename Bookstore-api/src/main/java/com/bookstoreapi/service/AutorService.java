package com.bookstoreapi.service;


import com.bookstoreapi.dto.AutorDto;
import com.bookstoreapi.entity.AutorEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AutorService {

    List<AutorEntity> listarAutores();

    ResponseEntity<AutorEntity> findAutorById(Long id);

    void adicionarAutor(AutorDto autorDto);

    ResponseEntity updateByAutorId(AutorDto autorDto, Long id);

    void deleteById(Long id);
}
