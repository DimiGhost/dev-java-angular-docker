package com.dev.appbackend.controller;

import com.dev.appbackend.handler.ErroResponse;
import com.dev.appbackend.model.Pessoa;
import com.dev.appbackend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.TimeZone;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/{pessoaId}")
    public ResponseEntity getPessoa(@PathVariable Long pessoaId) {
        //Antes de buscar a pessoa é verificado se a mesma existe para tratamento da resposta
        if (!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroResponse("Pessoa não encontrada."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findById(pessoaId).get());
    }

    @GetMapping("/")
    public ResponseEntity getPessoas() {
        //Lista todas as pessoas cadastras armazenadas no banco de dados
        return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.findAll());
    }

    @PostMapping("/")
    public ResponseEntity addPessoa(@Valid @RequestBody Pessoa pessoa) {
        //Verifica se o CPF já está cadastrado
        if (pessoaRepository.existsByCpf(pessoa.getCpf())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponse("CPF já cadastrado."));
        }

        try {
            pessoa.setDataCadastro(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime());
            pessoa.setDataAtualizacao(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime());

            //Tenta persistir os dados da pessoa já validados e o retorna caso criado
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoaRepository.save(pessoa));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponse("Ocorreu um erro ao persistir os dados."));
        }
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity removePessoa(@PathVariable Long pessoaId) {
        //Antes de buscar a pessoa é verificado se a mesma existe para garantir que a mesma será deletada
        if (!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroResponse("Pessoa não encontrada."));
        }

        try {
            //Caso não ocorrer nenhuma exceção, a pessoa é deletada
            pessoaRepository.deleteById(pessoaId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponse("Ocorreu um erro ao persistir os dados."));
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity updatePessoa(@PathVariable Long pessoaId, @Valid @RequestBody Pessoa pessoaRequest) {
        //Verifica se a pessoa existe para que possa atualizar os dados
        if (!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErroResponse("Pessoa não encontrada."));
        }

        try {
            //Atualiza todos os dados da pessoa, exceto o CPF
            return pessoaRepository.findById(pessoaId).map(pessoa -> {
                pessoa.setNome(pessoaRequest.getNome());
                pessoa.setDataNascimento(pessoaRequest.getDataNascimento());
                pessoa.setEmail(pessoaRequest.getEmail());
                pessoa.setNacionalidade(pessoaRequest.getNacionalidade());
                pessoa.setNaturalidade(pessoaRequest.getNaturalidade());
                pessoa.setSexo(pessoaRequest.getSexo());
                pessoa.setDataAtualizacao(Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo")).getTime());

                return ResponseEntity.status(HttpStatus.OK).body(pessoaRepository.save(pessoa));
            }).get();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErroResponse("Ocorreu um erro ao persistir os dados."));
        }


    }
}
