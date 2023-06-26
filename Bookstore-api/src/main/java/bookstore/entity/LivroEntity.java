package bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class LivroEntity {

    @Id
    private Long id;
    private String titulo;
    private String autor;
    private int anoPublicacao;
}