package com.bookstoreapi.service.impl;


import com.bookstoreapi.dto.AutorDto;
import com.bookstoreapi.dto.EditoraDto;
import com.bookstoreapi.entity.AutorEntity;
import com.bookstoreapi.entity.EditoraEntity;
import com.bookstoreapi.exception.BusinessException;
import com.bookstoreapi.repository.EditoraRepository;
import com.bookstoreapi.service.EditoraService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bookstoreapi.util.ConstantUtils.AUTOR_NAO_ENCONTRADO;

@Service
public class EditoraServiceImpl implements EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraServiceImpl(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @Override
    public void adicionarAutor(EditoraDto editoraDto) {

        EditoraEntity editoraEntity = new EditoraEntity();
        editoraEntity.setNome(editoraDto.getNome());
        editoraEntity.getNacionalidade(editoraDto.getNacionalidade());
        editoraRepository.save(editoraEntity);
    }

    @Override
    public List<EditoraEntity> listarEditoras() {
        return editoraRepository.findAll();
    }

    @Override
    public ResponseEntity<EditoraEntity> findEditoraById(Long id) {
        try {
            Optional<EditoraEntity> editoraOptional = editoraRepository.findById(id);
            if (!editoraOptional.isPresent()) {
                throw new BusinessException("Editora com ID número: " + id + " não foi encontrado no sistema.");
            }
            EditoraEntity editora = editoraOptional.get();
            return ResponseEntity.ok(editora);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Editora com ID número: " + id + " não foi encontrado no sistema.");
        }
    }

    @Override
    public ResponseEntity updateByEditoraId(EditoraDto editoraDto, Long id) {
        try {
            Optional<EditoraEntity> optionalEditora = editoraRepository.findById(id);
            if (optionalEditora.isEmpty()) {
                throw new BusinessException("Editora com ID número: " + id + " não foi encontrado no sistema.");
            }

            EditoraEntity editorExistente = optionalEditora.get();
            editorExistente.setNome(editoraDto.getNome());
            editorExistente.setNacionalidade(editoraDto.getNacionalidade());
            Object editorAtualizado = editoraRepository.save(editorExistente);

            if (editorAtualizado != null) {
                return ResponseEntity.ok("Editor atualizado com successo!");
            } else {
                throw new BusinessException("Erro ao atualizar editor: " + id);
            }
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar editor: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (editoraRepository.existsById(id)) {
                editoraRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(AUTOR_NAO_ENCONTRADO);
        }
    }

}


