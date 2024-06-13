package com.SDV.ecommerce.service;

import com.SDV.ecommerce.model.Produto;
import com.SDV.ecommerce.model.dao.ProdutoDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public void save(Produto produto){
        produtoDAO.save(produto);
    }

    public Produto findById(long id){
        return produtoDAO.findById(id).get();
    }
}
