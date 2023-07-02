package com.bookstoreapi.resource;


import com.bookstoreapi.dto.EditoraDto;
import com.bookstoreapi.entity.EditoraEntity;
import com.bookstoreapi.exception.BusinessException;
import com.bookstoreapi.service.EditoraService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class EditoraResource {
    private final EditoraService editoraService;

    public EditoraResource(EditoraService editoraService) {
        this.editoraService = editoraService; }

    @PostMapping
    public ResponseEntity<Void> adicionarEditora(
            @RequestBody EditoraDto editoraDto){
        editoraService.adicionarEditora(editoraDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findByid(
            @PathVariable Long id) {
         try {
             return editoraService.findEditoraById(id);
             //return ResponseEntity.ok(clienteService.findUserByid(id));
                }catch (BusinessException e) {
             return ResponseEntity.badRequest().body("Error finding editora: " + e.getMessage());
         }
    }
    @GetMapping
    public ResponseEntity<List<EditoraEntity>> listarEditoras(){
        List<EditoraEntity> editoras = editoraService.listarEditoras();
        return ResponseEntity.ok(editoras);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody EditoraDto editoraDto,
            @PathVariable ("id") Long id) {
        return editoraService.updateByEditoraId(editoraDto, id);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluirEditora(@PathVariable Long id){
        editoraService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

