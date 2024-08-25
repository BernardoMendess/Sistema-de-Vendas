package com.SDV.ecommerce.service;

import com.SDV.ecommerce.model.ProdutoComDependencias;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoComDependenciasService {

    private ProdutoService produtoService;
    private CategoriaService categoriaService;

    public ProdutoComDependencias findComDependenciasObjById(long id){
        val produto = produtoService.findById(id);
        return new ProdutoComDependencias(
                produto.getId(),
                produto.getNome(),
                produto.getMarca(),
                produto.getQuantidade(),
                produto.getPreco(),
                produto.getDescricao(),
                categoriaService.findById(produto.getCategoriaId()),
                produto.getNomeImagem()
        );
    }
}
