package com.dev.appbackend.handler;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErroResponse {
    private List<String> erros;

    public ErroResponse(String erro)
    {
        erros = new ArrayList<>();
        erros.add(erro);
    }
}
