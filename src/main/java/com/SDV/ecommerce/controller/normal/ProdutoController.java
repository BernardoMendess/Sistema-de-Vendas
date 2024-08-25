package com.SDV.ecommerce.controller.normal;

import com.SDV.ecommerce.model.Produto;
import com.SDV.ecommerce.model.ProdutoComDependencias;
import com.SDV.ecommerce.service.CategoriaService;
import com.SDV.ecommerce.service.ProdutoComDependenciasService;
import com.SDV.ecommerce.service.ProdutoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    private static String caminhoImagem = "C:\\Users\\berna\\IntellijProjects\\img\\";

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoComDependenciasService produtoComDependenciasService;

    @GetMapping("/new")
    public ModelAndView novo(){
        return telaEdit(ProdutoComDependencias.empty());

    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable long id){
        return telaEdit(produtoComDependenciasService.findComDependenciasObjById(id));
    }

    public ModelAndView telaEdit(ProdutoComDependencias produto){
        val categorias = categoriaService.findAll();

        return new ModelAndView("produto/edit")
                .addObject("produto", produto)
                .addObject("categorias", categoriaService.findAll());
    }

    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable long id){
        return new ModelAndView("produto/get")
                .addObject("produto", produtoComDependenciasService.findComDependenciasObjById(id));
    }

    @PostMapping
    public String save(Produto produto, @RequestParam("file")MultipartFile arquivo){
        val produtoSalvo = produtoService.save(produto);

        try{
            if(!arquivo.isEmpty()){
                byte[] bytes = arquivo.getBytes();
                Path caminho = Paths.get(caminhoImagem + String.valueOf(produtoSalvo.getId()) + arquivo.getOriginalFilename());
                Files.write(caminho, bytes);

                produto.setNomeImagem(String.valueOf(produtoSalvo.getId()) + arquivo.getOriginalFilename());
                produtoService.save(produto);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/produto/%d".formatted(produto.getId());
    }

    @GetMapping("/imagem/{imagem}")
    @ResponseBody
    public byte[] mostrarImagem(@PathVariable("imagem") String imagem) throws IOException {
        File imagemProduto = new File(caminhoImagem + imagem);
        return Files.readAllBytes(imagemProduto.toPath());
    }
}
