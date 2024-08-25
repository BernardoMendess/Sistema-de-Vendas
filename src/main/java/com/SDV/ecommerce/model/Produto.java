package com.SDV.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@With
@Table(name = "produto")
public class Produto {

    @Id
    private Long id;
    private String nome;
    private String marca;
    private int quantidade;
    private float preco;
    private String descricao;
    private Long categoriaId;
    private String nomeImagem;

    public static Produto empty(){
        return new Produto(null, "", "", 0, 0, "", null, "");
    }
}
