package com.desafioftp.desafio.resource;

import com.desafioftp.desafio.model.Usuario;
import com.desafioftp.desafio.service.ServicoFtp;
import com.desafioftp.desafio.service.ServicoUsuario;
import io.swagger.annotations.*;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

@RestController
@RequestMapping("v1/arquivos")
@Api(value = "arquivos")
public class ControleFtp {

     private ServicoFtp servicoFtp;
     private ServicoUsuario servicoUsuario;

     @Autowired
    public ControleFtp(ServicoFtp servicoFtp, ServicoUsuario servicoUsuario) {
        this.servicoFtp = servicoFtp;
        this.servicoUsuario = servicoUsuario;
    }

    @PostMapping(value = "/{id}")
    @ApiOperation(value="Envia arquivos")
    @ApiResponses(value= {
            @ApiResponse(code=201, message="Enviou arquivos com sucesso"),
            @ApiResponse(code=404, message = "Não encontrou arquivos"),
            @ApiResponse(code=500, message="Erro interno")
    })
    public void uploadArquivo(@PathVariable String id, @RequestBody MultipartFile arquivo)  {
        this.servicoFtp.salvaArquivo(servicoUsuario.findById(id).get().getId(), arquivo);
    }


//    @GetMapping
//    @ApiOperation(value="Busca Arquivos")
//    public FTPFile[] listaUpload() {
//        return this.servicoFtp.listaTodosArquivos();
//    }


    @GetMapping(value = "/{id}/{paginas}/{quantidade}")
    @ApiOperation(value="Busca arquivos com filtros do usuario")
    @ApiResponses(value= {
            @ApiResponse(code=201, message="Buscou arquivos com sucesso"),
            @ApiResponse(code=404, message = "Não encontrou arquivos"),
            @ApiResponse(code=500, message="Erro interno")
    })
    public Page<FTPFile> arquivosPaginados(@PathVariable String id,
                                            @PathVariable Integer paginas,
                                            @PathVariable Integer quantidade ) {
         return servicoFtp.listaArquivosPaginados(servicoUsuario.findById(id).get().getId(), paginas, quantidade);
    }

    @GetMapping(value = "/compartilha/{idUsuarioEnvia}/{arquivo}/{idUsuarioRecebe}")
    @ApiOperation(value="compartilha arquivos")
    @ApiResponses(value= {
            @ApiResponse(code=201, message="Buscou arquivos com sucesso"),
            @ApiResponse(code=404, message = "Não encontrou arquivos"),
            @ApiResponse(code=500, message="Erro interno")
    })
    public void compartilhaArquivos(@PathVariable String idUsuarioEnvia,
                                             @PathVariable String arquivo,
                                             @PathVariable String idUsuarioRecebe
                                             ) {
         servicoFtp.arquivosCompartilhados(servicoUsuario.findById(idUsuarioEnvia).get().getId(),
                                                servicoUsuario.findById(idUsuarioRecebe).get().getId(),
                                                arquivo);
    }


    @DeleteMapping(value = "/{id}")
    @ApiParam(name = "id", required = true)
    @ApiOperation(value = "Deleta arquivos do usuário")
    public void deletar(@PathVariable String id, @RequestParam String nomeArquivo) {
        Optional<Usuario> usuario = servicoUsuario.findById(id);
        servicoFtp.excluirArquivos(usuario, nomeArquivo);
    }


}
