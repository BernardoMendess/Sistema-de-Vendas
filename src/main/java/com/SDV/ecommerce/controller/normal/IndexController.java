package com.SDV.ecommerce.controller.normal;

import com.SDV.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping
public class IndexController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ModelAndView getIndex(){
        return new ModelAndView("index")
                .addObject("produtos", produtoService.findAll());
    }

}
