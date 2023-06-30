package com.bookstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {

    private String titulo;

    private String autor;

    private Integer anoPublicacao;

}
