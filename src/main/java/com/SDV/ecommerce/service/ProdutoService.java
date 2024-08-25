package com.SDV.ecommerce.service;

import com.SDV.ecommerce.model.Produto;
import com.SDV.ecommerce.model.dao.ProdutoDAO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public Produto save(Produto produto){
        return produtoDAO.save(produto);
    }

    public Produto findById(long id){
        return produtoDAO.findById(id).get();
    }

    public List<Produto> findAll(){
        val p = produtoDAO.findAll();
        return produtoDAO.findAll();
    }
}
