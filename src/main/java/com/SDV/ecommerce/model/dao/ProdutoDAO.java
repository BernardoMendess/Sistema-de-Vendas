package com.SDV.ecommerce.model.dao;

import com.SDV.ecommerce.model.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoDAO extends CrudRepository<Produto, Long> {
}
