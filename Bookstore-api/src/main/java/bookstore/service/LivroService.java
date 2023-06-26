package bookstore.service;

import bookstore.dto.LivroDto;
import bookstore.entity.LivroEntity;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface LivroService {

    public List<LivroDto> listarLivros();

    ResponseEntity<LivroEntity> findLivroById(Long id);

    void adicionarLivro(LivroDto livroDto);

    ResponseEntity<String> updateByLivroId(LivroDto livroDto, Long id);

    public void deleteById(Long id);
}
