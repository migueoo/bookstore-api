package com.bookstoreapi.resource;


import com.bookstoreapi.dto.GeneroDto;
import com.bookstoreapi.entity.GeneroEntity;
import com.bookstoreapi.exception.BusinessException;
import com.bookstoreapi.service.GeneroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroResource {
    private final GeneroService generoService;

    public GeneroResource(GeneroService generoService) {
        this.generoService = generoService; }

    @PostMapping
    public ResponseEntity<Void> adicionarGenero(
            @RequestBody GeneroDto generoDto){
        generoService.adicionarGenero(generoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findByid(
            @PathVariable Long id) {
         try {
             return generoService.findGeneroById(id);
             //return ResponseEntity.ok(clienteService.findUserByid(id));
                }catch (BusinessException e) {
             return ResponseEntity.badRequest().body("Error finding genero: " + e.getMessage());
         }
    }
    @GetMapping
    public ResponseEntity<List<GeneroEntity>> listarGeneros(){
        List<GeneroEntity> generos = generoService.listarGeneros();
        return ResponseEntity.ok(generos);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody GeneroDto generoDto,
            @PathVariable ("id") Long id) {
        return generoService.updateByGeneroId(generoDto, id);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluirGenero(@PathVariable Long id){
        generoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

