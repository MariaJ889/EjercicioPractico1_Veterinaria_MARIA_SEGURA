package com.caso01.veterinaria.Controller;

import com.caso01.veterinaria.Service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/citas")
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public String listado(Model model) {
        var citas = citaService.getCitas();
        model.addAttribute("citas", citas);
        model.addAttribute("totalCitas", citas.size());
        return "citas/listado";
    }
}
