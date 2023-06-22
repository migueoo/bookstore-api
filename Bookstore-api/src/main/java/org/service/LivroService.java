package org.service;

import org.entity.Livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LivroService {
    private List<Livro> livros;

    public LivroService() {
        livros = new ArrayList<>();
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    public Optional<Livro> buscarLivroPorId(Long id) {
        return livros.stream()
                .filter(livro -> livro.getId().equals(id))
                .findFirst();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void atualizarLivro(Livro livro) {
        Optional<Livro> livroExistente = buscarLivroPorId(livro.getId());
        livroExistente.ifPresent(l -> {
            l.setTitulo(livro.getTitulo());
            l.setAutor(livro.getAutor());
            l.setAnoPublicacao(livro.getAnoPublicacao());
        });
    }

    public void removerLivro(Long id) {
        livros.removeIf(livro -> livro.getId().equals(id));
    }
}
