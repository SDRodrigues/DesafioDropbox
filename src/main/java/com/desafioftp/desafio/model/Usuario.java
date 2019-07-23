package com.desafioftp.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    private Integer id;
    private String nome;
    private Integer idade;
    private String profissao;
    private String senha;
    private List<Arquivos> arquivos;
}
