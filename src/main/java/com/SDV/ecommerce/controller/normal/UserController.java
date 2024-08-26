package com.SDV.ecommerce.controller.normal;

import com.SDV.ecommerce.model.UserModel;
import com.SDV.ecommerce.service.usuario.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping()
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/usuario/cadastro")
    public ModelAndView novo(){
        return new ModelAndView("usuario/cadastro");
    }

    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @PostMapping("/usuario")
    public String saveUser(UserModel user){
        userService.saveNewUser(user);
        return "redirect:/login";
    }
}
