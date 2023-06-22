package bookstore.repository;

import bookstore.entity.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AutorRepository extends JpaRepository {
    List<AutorEntity> findAll();

}
