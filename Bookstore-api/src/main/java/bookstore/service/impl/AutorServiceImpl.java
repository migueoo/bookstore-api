package bookstore.service.impl;

import bookstore.dto.LivroDto;
import bookstore.dto.LivroUpdateForm;
import bookstore.entity.AutorEntity;
import bookstore.service.AutorService;

import java.util.List;
import java.util.Optional;


public class AutorServiceImpl extends AutorService {

        private List<AutorEntity> autoresEntities;


    public void adicionarAutor(AutorEntity autorEntity) {
            autorEntities.add(autorEntity);
        }

        public List<AutorEntity> listarAutores() {
            return autorEntities;
        }

        @Override
        public Optional<AutorEntity> findAutorById(Long id) {
            return livroEntities.stream()
                    .filter(autorEntity -> autorEntity.getId().equals(id))
                    .findFirst();
        }

        @Override
        public LivroDto updateByAutorId(LivroUpdateForm form, Long id) {
            Optional<AutorEntity> autorExistente = findAutorById(id);
            autorExistente.ifPresent(a -> {
                a.setNome(form.getNome());
                a.setEmail(form.getEmail());
            });
            return null;
        }



    }

}
