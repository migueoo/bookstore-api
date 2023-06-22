package bookstore.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
public class LivroEntity {
    @Id
    private Long id;
    private String titulo;
    private String autor;
    private int anoPublicacao;


    public LivroEntity(Long id, String titulo, String autor, int anoPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}