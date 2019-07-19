package com.desafioftp.desafio.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    private Integer id;
    private String nome;
    private Integer idade;
    private String profissao;
}