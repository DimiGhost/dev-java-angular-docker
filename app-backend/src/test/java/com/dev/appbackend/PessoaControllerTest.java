package com.dev.appbackend;

import com.dev.appbackend.model.Pessoa;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testeListarPessoas() throws Exception {
        this.mockMvc.perform(get("/pessoa/")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testeAddPessoa() throws Exception {
        Pessoa pessoaTeste = new Pessoa();
        pessoaTeste.setNome("João");
        pessoaTeste.setDataNascimento(Calendar.getInstance().getTime());
        pessoaTeste.setCpf("12345659");

        this.mockMvc.perform(post("/pessoa/").content(objectMapper.writeValueAsString(pessoaTeste)).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void testeUpdatePessoa() throws Exception {
        Pessoa pessoaTeste = new Pessoa();
        pessoaTeste.setNome("Novo nome");
        pessoaTeste.setNaturalidade("Santa Catarina");

        this.mockMvc.perform(put("/pessoa/18").content(objectMapper.writeValueAsString(pessoaTeste)).contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(pessoaTeste.getNome()))
                .andExpect(jsonPath("$.naturalidade").value(pessoaTeste.getNaturalidade()));

    }

    @Test
    public void testeGetPessoa() throws Exception {

        this.mockMvc.perform(get("/pessoa/2")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testeDeletePessoa() throws Exception {

        this.mockMvc.perform(delete("/pessoa/2")).andDo(print()).andExpect(status().isOk());
    }

}
