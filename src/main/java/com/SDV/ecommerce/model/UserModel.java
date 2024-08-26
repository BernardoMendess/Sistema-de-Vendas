package com.SDV.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class UserModel {
    @Id
    private Long id;
    @NonNull private String nome;
    private LocalDate dataNascimento;
    @NonNull private String sexo;
    @NonNull private String cpf;
    @NonNull private String endereco;
    @NonNull private String telefone;
    @NonNull private String email;
    @NonNull private String username;
    @NonNull private String password;
    @NonNull private Role role;
    @NonNull private LocalDate dataCadastro;

    public static UserModel empty() {
        return new UserModel(null,"", null, "", "", "", "", "", "", "", Role.USER, LocalDate.now());
    }

    public void limpaFormatacao() {
        if (cpf != null) {
            cpf = cpf.replaceAll("[^0-9]", "");
        }

        if (telefone != null) {
            telefone = telefone.replaceAll("[^0-9]", "");
        }
    }

    public String formataCPF() {
        String numCPF = cpf.replaceAll("[^0-9]", "");

        return String.format("%s.%s.%s-%s",
                numCPF.substring(0, 3),
                numCPF.substring(3, 6),
                numCPF.substring(6, 9),
                numCPF.substring(9));
    }

    public String formataTelefone() {
        String numTelefone = telefone.replaceAll("[^0-9]", "");

        return String.format("(%s) %s-%s",
                numTelefone.substring(0, 2),
                numTelefone.substring(2, 7),
                numTelefone.substring(7));
    }
}