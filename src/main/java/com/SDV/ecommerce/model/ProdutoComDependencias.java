package com.SDV.ecommerce.model;

import org.springframework.data.annotation.Id;

public record ProdutoComDependencias (
        Long id,
        String nome,
        String marca,
        int quantidade,
        float preco,
        String descricao,
        Categoria categoria,
        String nomeImagem
) {

    public static ProdutoComDependencias empty(){
        return new ProdutoComDependencias(null, "", "", 0, 0, "", null, "");
    }

}
