package com.desafioftp.desafio.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.net.ftp.FTPFile;
import java.io.File;

@Data
@NoArgsConstructor
public class Arquivos {
    private String donoArquivo;
    private String nomeArquivo;


    public Arquivos(FTPFile ftpFile) {
    }

    public Arquivos(File arquivo, String dono) {
        nomeArquivo = arquivo.getName();
        this.donoArquivo = dono;
    }

    public static Arquivos recebeArquivo(File file) {
        Arquivos arquivos = new Arquivos();
        arquivos.nomeArquivo = file.getName();
        return arquivos;
    }
}
