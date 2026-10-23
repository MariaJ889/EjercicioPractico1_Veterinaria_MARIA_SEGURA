package com.caso01.veterinaria.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/consultas")
    public String consultas() {
        return "consultas/formulario";
    }

    @PostMapping("/consultas")
    public String enviarConsulta(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("todoOk", "Gracias por contactarnos. Pronto atenderemos su consulta.");
        return "redirect:/consultas#consultas";
    }
}
