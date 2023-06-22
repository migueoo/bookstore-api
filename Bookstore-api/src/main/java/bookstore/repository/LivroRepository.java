package bookstore.repository;

import bookstore.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LivroRepository extends JpaRepository {
    List<LivroEntity> findAll();
}
