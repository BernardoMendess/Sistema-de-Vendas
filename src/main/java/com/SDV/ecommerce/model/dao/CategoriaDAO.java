package com.SDV.ecommerce.model.dao;

import com.SDV.ecommerce.model.Categoria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoriaDAO extends CrudRepository<Categoria, Long> {

    List<Categoria> findAll();

}
