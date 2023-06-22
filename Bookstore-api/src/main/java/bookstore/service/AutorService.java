package bookstore.service;

import bookstore.entity.AutorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AutorService {
    private List<AutorEntity> autores;

    public AutorService() {
        autores = new ArrayList<>();
    }

    public List<AutorEntity> listarAutores() {
        return autores;
    }

    public Optional<AutorEntity> buscarAutorPorId(Long id) {
        return autores.stream()
                .filter(autorEntity -> autorEntity.getId().equals(id))
                .findFirst();
    }

    public void adicionarAutor(AutorEntity autorEntity) {
        autores.add(autorEntity);
    }

    public void atualizarAutor(AutorEntity autorEntity) {
        Optional<AutorEntity> autorExistente = buscarAutorPorId(autorEntity.getId());
        autorExistente.ifPresent(a -> {
            a.setNome(autorEntity.getNome());
            a.setEmail(autorEntity.getEmail());
        });
    }

    public void removerAutor(Long id) {
        autores.removeIf(autorEntity -> autorEntity.getId().equals(id));
    }
}
