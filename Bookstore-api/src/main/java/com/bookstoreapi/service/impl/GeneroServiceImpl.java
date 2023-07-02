package com.bookstoreapi.service.impl;


import com.bookstoreapi.dto.GeneroDto;
import com.bookstoreapi.entity.GeneroEntity;
import com.bookstoreapi.exception.BusinessException;
import com.bookstoreapi.repository.GeneroRepository;
import com.bookstoreapi.service.AutorService;
import com.bookstoreapi.service.GeneroService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.bookstoreapi.util.ConstantUtils.AUTOR_NAO_ENCONTRADO;

@Service
public class GeneroServiceImpl implements GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroServiceImpl(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Override
    public void adicionarGenero(GeneroDto generoDto) {

        GeneroEntity generoEntity= new GeneroEntity();
        generoEntity.setNome(generoDto.getNome());
        generoEntity.setDescricao(generoDto.getDescricao());
        generoRepository.save(generoEntity);
    }

    @Override
    public List<GeneroEntity> listarGeneros() {
        return generoRepository.findAll();
    }

    @Override
    public ResponseEntity<GeneroEntity> findGeneroById(Long id) {
        try {
            Optional<GeneroEntity> generoOptional = generoRepository.findById(id);
            if (!generoOptional.isPresent()) {
                throw new BusinessException("Genero com ID número: " + id + " não foi encontrado no sistema.");
            }
            GeneroEntity genero = generoOptional.get();
            return ResponseEntity.ok(genero);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("Genero com ID número: " + id + " não foi encontrado no sistema.");
        }
    }

    @Override
    public ResponseEntity updateByGeneroId(GeneroDto generoDto, Long id) {
        try {
            Optional<GeneroEntity> optionalGenero = generoRepository.findById(id);
            if (optionalGenero.isEmpty()) {
                throw new BusinessException("Genero com ID número: " + id + " não foi encontrado no sistema.");
            }

            GeneroEntity generoExistente = optionalGenero.get();
            generoExistente.setNome(generoDto.getNome());
            generoExistente.setDescricao(generoDto.getDescricao());
            Object generoAtualizado = generoRepository.save(generoExistente);

            if (generoAtualizado != null) {
                return ResponseEntity.ok("Genero atualizado com successo!");
            } else {
                throw new BusinessException("Erro ao atualizar genero: " + id);
            }
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar genero: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            if (generoRepository.existsById(id)) {
                generoRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(AUTOR_NAO_ENCONTRADO);
        }
    }

}


