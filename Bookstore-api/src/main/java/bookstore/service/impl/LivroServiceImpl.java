package bookstore.service.impl;

import bookstore.dto.LivroDto;
import bookstore.entity.LivroEntity;
import bookstore.exception.BusinessException;
import bookstore.repository.LivroRepository;
import bookstore.service.LivroService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static bookstore.util.ConstantUtils.LIVRO_NAO_ENCONTRADO;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository livroRepository;

    public LivroServiceImpl(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public void adicionarLivro(LivroDto livroDto) {

        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setTitulo(livroDto.getTitulo());
        livroEntity.setAutor(livroDto.getAutor());
        livroEntity.setAnoPublicacao(livroDto.getAnoPublicacao());
        livroRepository.save(livroEntity);

    }

    @Override
    public List<LivroDto> listarLivros() {
        return livroRepository.findAll();
    }

    @Override
    public ResponseEntity<LivroEntity> findLivroById(Long id) {
        try {
            Optional<LivroEntity> livroOptional = livroRepository.findById(id);
            if (!livroOptional.isPresent()) {
                throw new BusinessException("User id number: " + id + " not found in system!");
            }
            LivroEntity livro = livroOptional.get();
            return ResponseEntity.ok(livro);
        } catch (EmptyResultDataAccessException ex) {
            throw new BusinessException("User id number: " + id + " not found in system!");
        }
    }

    @Override
    public ResponseEntity<String> updateByLivroId(LivroDto livroDto, Long id) {
        try {
            Optional<LivroEntity> optionalLivro = livroRepository.findById(id);
            if (optionalLivro.isEmpty()) {
                throw new BusinessException("Livro id number: " + id + " not found in the system!");
            }

            LivroEntity livroExistente = optionalLivro.get();
            livroExistente.setTitulo(livroDto.getTitulo());
            livroExistente.setAutor(livroDto.getAutor());
            livroExistente.setAnoPublicacao(livroDto.getAnoPublicacao());
            Object livroAtualizado = livroRepository.save(livroExistente);

            if (livroAtualizado != null) {
                return ResponseEntity.ok("Livro atualizado com successo!");
            } else {
                throw new BusinessException("Erro ao atualizar livro: " +id);
            }
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar livro: " + e.getMessage());
        }
    }


    @Override
    public void deleteById(Long id) {
        try {
            if (livroRepository.existsById(id)) {
                livroRepository.deleteById(id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(LIVRO_NAO_ENCONTRADO);
        }
    }


}
