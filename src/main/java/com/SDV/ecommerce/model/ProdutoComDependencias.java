package com.SDV.ecommerce.model;

import org.springframework.data.annotation.Id;

public record ProdutoComDependencias (
        Long id,
        String nome,
        int quantidade,
        float preco,
        String descricao,
        Categoria categoria
) {
}
