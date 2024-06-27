package com.SDV.ecommerce.service;

import com.SDV.ecommerce.model.Categoria;
import com.SDV.ecommerce.model.dao.CategoriaDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    private CategoriaDAO categoriaDAO;

    public List<Categoria> findAll(){
        return categoriaDAO.findAll();
    }

    public Categoria findById(long id){
        return categoriaDAO.findById(id).get();
    }
}
