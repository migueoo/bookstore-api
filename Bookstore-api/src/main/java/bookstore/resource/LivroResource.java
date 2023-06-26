package bookstore.resource;

import bookstore.dto.LivroDto;
import bookstore.exception.BusinessException;
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
    public ResponseEntity<?> findById(
            @PathVariable Long id) {
        try {
            return livroService.findLivroById(id);
            //return ResponseEntity.ok(clientService.findUserById(id)); -> Displays headers, body, status code...
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Error finding client: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> listarLivros() {
        List<LivroDto> livros = livroService.listarLivros();
        return ResponseEntity.ok(livros);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> update(
            @RequestBody LivroDto livroDto,
            @PathVariable("id") Long id) {
        return livroService.updateByLivroId(livroDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Long id) {
        livroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
