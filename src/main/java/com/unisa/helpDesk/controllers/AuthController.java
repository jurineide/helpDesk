package com.unisa.helpDesk.controllers;

import com.unisa.helpDesk.dtos.AuthDto;
import com.unisa.helpDesk.dtos.UsuarioDto;
import com.unisa.helpDesk.enumerator.TipoUsuario;
import com.unisa.helpDesk.services.UsuarioService;
import com.unisa.helpDesk.shared.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastra-usuario")
    public String register(@ModelAttribute UsuarioDto dto, Model model) {
        ApiResponse<UsuarioDto> usuarioCadastrado = this.usuarioService.save(dto);

        if(usuarioCadastrado.getData() == null) {
            model.addAttribute("erro", usuarioCadastrado.getMessage());
            return "cadastro";
        }

        return "redirect:/login";
    }

    @PostMapping("/login-usuario")
    public String login(@ModelAttribute AuthDto dto, Model model) {
        ApiResponse<UsuarioDto> usuarioLogado = this.usuarioService.login(dto);

        if (usuarioLogado.getData() == null) {
            model.addAttribute("erro", usuarioLogado.getMessage());
            return "login";
        }

        if (usuarioLogado.getData().getTipoUsuario().equals(TipoUsuario.ADMIN)) {
            return "redirect:/tela-admin";
        } else if (usuarioLogado.getData().getTipoUsuario().equals(TipoUsuario.TECNICO)) {
            return "redirect:/tela-tecnico";
        } else if (usuarioLogado.getData().getTipoUsuario().equals(TipoUsuario.FUNCIONARIO)) {
            return "redirect:/tela-usuario";
        } else {
            model.addAttribute("erro", usuarioLogado.getMessage());
            return "login";
        }
    }

}
