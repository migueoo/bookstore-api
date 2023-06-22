package bookstore.service.impl;

import bookstore.dto.LivroDto;
import bookstore.dto.LivroUpdateForm;
import bookstore.entity.LivroEntity;
import bookstore.repository.LivroRepository;
import bookstore.service.LivroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }


    public void salvarLivro(LivroDto livroDto) {

        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setTitulo(livroDto.getTitulo());
        livroEntity.setAutor(livroDto.getAutor());
        livroEntity.setAnoPublicacao(livroDto.getAnoPublicacao());
        livroRepository.save(livroEntity);

    }

    public List<LivroEntity> listarLivros() {
        return livroEntities;
    }

    @Override
    public Optional<LivroEntity> findLivroById(Long id) {
        return livroEntities.stream()
                .filter(livroEntity -> livroEntity.getId().equals(id))
                .findFirst();
    }

    @Override
    public LivroDto updateByLivroId(LivroUpdateForm form, Long id) {
        Optional<LivroEntity> livroExistente = findLivroById(id);
        livroExistente.ifPresent(l -> {
            l.setTitulo(form.getTitulo());
            l.setAutor(form.getAutor());
            l.setAnoPublicacao(form.getAnoPublicacao());

        });
        return null;
    }



}
