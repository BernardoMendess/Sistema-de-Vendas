package com.SDV.ecommerce.controller.normal;

import com.SDV.ecommerce.model.Produto;
import com.SDV.ecommerce.service.ProdutoComDependenciasService;
import com.SDV.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoComDependenciasService produtoComDependenciasService;

    @GetMapping("/new")
    public ModelAndView newProduto(){
        return new ModelAndView("produto/edit")
                .addObject("produto", Produto.empty());
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editProduto(@PathVariable long id){
        return new ModelAndView("produto/edit")
                .addObject("produto", produtoComDependenciasService.findComDependenciasObjById(id));
    }

    @PostMapping
    public String saveProduto(Produto produto){
        produtoService.save(produto);
        return "redirect:/produto/%d/edit".formatted(produto.getId());
    }
}
