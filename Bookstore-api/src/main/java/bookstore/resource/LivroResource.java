package bookstore.resource;

import bookstore.dto.LivroDto;
import bookstore.dto.LivroUpdateForm;
import bookstore.entity.LivroEntity;
import bookstore.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroResource {

    private final LivroService livroService;

    @Autowired
    public LivroResource(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Void> adicionarLivro(@RequestBody LivroDto livroDto) {
        livroService.adicionarLivro(livroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroEntity> buscarLivroPorId(@PathVariable Long id) {
        return livroService.findLivroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LivroEntity>> listarLivros() {
        List<LivroEntity> livros = livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> atualizarLivro(
            @PathVariable Long id,
            @RequestBody LivroUpdateForm form) {
        LivroDto livroAtualizado = livroService.updateByLivroId(form, id);
        if (livroAtualizado != null) {
            return ResponseEntity.ok(livroAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
