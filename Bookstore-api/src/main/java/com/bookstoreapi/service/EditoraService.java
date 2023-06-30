package com.bookstoreapi.service;


import com.bookstoreapi.dto.EditoraDto;
import com.bookstoreapi.entity.EditoraEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EditoraService {

    List<EditoraEntity> listarEditoras();

    ResponseEntity<EditoraEntity> findEditoraById(Long id);

    void adicionarAutor(EditoraDto editoraDto);

    ResponseEntity updateByAutorId(EditoraDto editoraDto, Long id);

    void deleteById(Long id);
}
