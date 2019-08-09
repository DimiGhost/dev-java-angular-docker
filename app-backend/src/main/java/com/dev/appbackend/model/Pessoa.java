package com.dev.appbackend.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "pessoas")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pessoa {
    @Id
    @GeneratedValue
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataCadastro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataAtualizacao;

    @NotBlank(message = "Campo nome deve ser preenchido.")
    private String nome;

    @Pattern(regexp = "[M|F]{1}$", message = "Sexo inválido")
    private String sexo;

    @Size(max = 14)
    @NotBlank(message = "Campo CPF deve ser preenchido.")
    @Column(unique = true)
    @Pattern(regexp = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$", message = "CPF inválido")
    private String cpf;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "Campo data de nascimento deve ser preenchido.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Date dataNascimento;

    @Pattern(regexp = ".+@.+\\..+", message = "Email inválido")
    private String email;

    private String naturalidade;

    private String nacionalidade;

}
