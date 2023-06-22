package bookstore.service;

import bookstore.dto.LivroDto;
import bookstore.dto.LivroUpdateForm;
import bookstore.entity.LivroEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LivroService {

    public List<LivroEntity> listarLivros();

    public Optional<LivroEntity> findLivroById(Long id);

    public void adicionarLivro(LivroDto livroDto);

    LivroDto updateByLivroId(LivroUpdateForm form, Long id);

    public void deleteById(Long id);
}
