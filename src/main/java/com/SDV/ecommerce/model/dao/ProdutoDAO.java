package com.SDV.ecommerce.model.dao;

import com.SDV.ecommerce.model.Produto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProdutoDAO extends CrudRepository<Produto, Long> {

    List<Produto> findAll();

}
