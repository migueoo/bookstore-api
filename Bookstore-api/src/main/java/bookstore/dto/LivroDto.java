package bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {
    private Long id;
    private String titulo;
    private String autor;
    private Integer anoPublicacao;

}
