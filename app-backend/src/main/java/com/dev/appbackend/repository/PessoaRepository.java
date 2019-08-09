package com.dev.appbackend.repository;

import com.dev.appbackend.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    //Consulta se existe uma pessoa com o CPF
    Boolean existsByCpf(String cpf);
}
