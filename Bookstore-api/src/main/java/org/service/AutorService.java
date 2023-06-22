package org.service;

import org.entity.Autor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorService {
    private List<Autor> autores;

    public AutorService() {
        autores = new ArrayList<>();
    }

    public List<Autor> listarAutores() {
        return autores;
    }

    public Optional<Autor> buscarAutorPorId(Long id) {
        return autores.stream()
                .filter(autor -> autor.getId().equals(id))
                .findFirst();
    }

    public void adicionarAutor(Autor autor) {
        autores.add(autor);
    }

    public void atualizarAutor(Autor autor) {
        Optional<Autor> autorExistente = buscarAutorPorId(autor.getId());
        autorExistente.ifPresent(a -> {
            a.setNome(autor.getNome());
            a.setEmail(autor.getEmail());
        });
    }

    public void removerAutor(Long id) {
        autores.removeIf(autor -> autor.getId().equals(id));
    }
}
