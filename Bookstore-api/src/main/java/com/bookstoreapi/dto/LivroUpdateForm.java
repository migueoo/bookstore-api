package com.bookstoreapi.dto;

import lombok.Data;

@Data
public class LivroUpdateForm {

    private String titulo;
    private String autor;
    private String genero;
    private Integer anoPublicacao;

}