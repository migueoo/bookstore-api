package com.bookstoreapi.service.impl;


import com.bookstoreapi.dto.AutorDto;
import com.bookstoreapi.entity.AutorEntity;
import com.bookstoreapi.exception.BusinessException;
import com.bookstoreapi.repository.AutorRepository;
import com.bookstoreapi.service.AutorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bookstoreapi.util.ConstantUtils.AUTOR_NAO_ENCONTRADO;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    public AutorServiceImpl( AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public void adicionarAutor(AutorDto autorDto) {

        AutorEntity autorEntity = new AutorEntity();
        autorEntity.setNome(autorDto.getNome());
        autorEntity.setEmail(autorDto.getEmail());
        autorRepository.save(autorEntity);
    }

    @Override
    public List<AutorEntity> listarAutores() {
        return autorRepository.findAll();
    }

    @Override
    public ResponseEntity<AutorEntity> findAutorById(Long id) {
        try {
            Optional<AutorEntity> autorOptional = autorRepository.findById(id);
            if (!autorOptional.isPresent()) {
                throw new BusinessException("Livro com ID número: " + id + " não foi encontrado no sistema.");
            }
            AutorEntity autor = autorOptional.get();
            return ResponseEntity.ok(autor);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Livro com ID número: " + id + " não foi encontrado no sistema.");
        }
    }

    @Override
    public ResponseEntity updateByAutorId(AutorDto autorDto, Long id) {
        try {
            Optional<AutorEntity> optionalAutor = autorRepository.findById(id);
            if (optionalAutor.isEmpty()) {
                throw new BusinessException("Livro com ID número: " + id + " não foi encontrado no sistema.");
            }

            AutorEntity autorExistente = optionalAutor.get();
            autorExistente.setNome(autorDto.getNome());
            autorExistente.setEmail(autorDto.getEmail());
            Object autorAtualizado = autorRepository.save(autorExistente);

            if (autorAtualizado != null) {
                return ResponseEntity.ok("Autor atualizado com successo!");
            } else {
                throw new BusinessException("Erro ao atualizar autor: " + id);
            }
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar livro: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (autorRepository.existsById(id)) {
                autorRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(AUTOR_NAO_ENCONTRADO);
        }
    }

}


