package bookstore.service;

import bookstore.dto.AutorDto;
import bookstore.dto.AutorUpdateForm;
import bookstore.entity.AutorEntity;
import bookstore.entity.LivroEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AutorService {

    List<AutorDto> listarAutores();

    ResponseEntity<AutorEntity> findAutorById(Long id);

    void adicionarAutor(AutorDto autorDto);

    ResponseEntity updateByAutorId(AutorDto autorDto, Long id);

    void deleteById(Long id);
}
