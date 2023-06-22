package bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class AutorEntity {
    @Id
    private Long id;
    private String nome;
    private String email;



    public AutorEntity(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}




