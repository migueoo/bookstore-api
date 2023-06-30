package com.bookstoreapi.resource;


import com.bookstoreapi.dto.AutorDto;
import com.bookstoreapi.entity.AutorEntity;
import com.bookstoreapi.exception.BusinessException;
import com.bookstoreapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorResource {
    private final AutorService autorService;

    public AutorResource (AutorService autorService) {
        this.autorService = autorService; }

    @PostMapping
    public ResponseEntity<Void> adicionarAutor(
            @RequestBody AutorDto autorDto){
        autorService.adicionarAutor(autorDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findByid(
            @PathVariable Long id) {
         try {
             return autorService.findAutorById(id);
             //return ResponseEntity.ok(clienteService.findUserByid(id));
                }catch (BusinessException e) {
             return ResponseEntity.badRequest().body("Error finding client: " + e.getMessage());
         }
    }
    @GetMapping
    public ResponseEntity<List<AutorEntity>> listarAutores(){
        List<AutorEntity> autores = autorService.listarAutores();
        return ResponseEntity.ok(autores);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody AutorDto autorDto,
            @PathVariable ("id") Long id) {
        return autorService.updateByAutorId(autorDto, id);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id){
        autorService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

