package com.caso01.veterinaria.Controller;

import com.caso01.veterinaria.Domain.TipoServicio;
import com.caso01.veterinaria.Service.TipoServicioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tipos-servicio")
public class TipoServicioController {

    private final TipoServicioService tipoServicioService;

    @GetMapping
    public String listado(Model model) {
        cargarDatosTipoServicio(model);
        return "tiposServicio/listado";
    }

    //Formulario para guardar un tipo de servicio
    @GetMapping("/guardar")
    public String guardar(Model model) {
        cargarDatosTipoServicio(model);
        return "tiposServicio/listado";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model,
                         RedirectAttributes redirectAttributes) {
        var tipoServicio = tipoServicioService.getTipoServicio(id);
        if (tipoServicio.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el tipo de servicio.");
            return "redirect:/tipos-servicio";
        }
        cargarDatosTipoServicio(model);
        model.addAttribute("tipoServicio", tipoServicio.get());
        return "tiposServicio/listado";
    }

    //Guardar o editar segun corresponda
    @PostMapping("/guardar")
    public String guardar(@Valid TipoServicio tipoServicio, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el tipo de servicio.");
            return "redirect:/tipos-servicio";
        }

        try {
            tipoServicioService.save(tipoServicio);
            redirectAttributes.addFlashAttribute("todoOk", "El tipo de servicio se guardó correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el tipo de servicio.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el tipo de servicio.");
        }
        return "redirect:/tipos-servicio";
    }

    //Eliminar Tipo de Servicio
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam(value = "id", required = false) Integer id,
                           RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("error", "No se recibió el tipo de servicio que desea eliminar.");
            return "redirect:/tipos-servicio";
        }

        try {
            tipoServicioService.delete(id);
            redirectAttributes.addFlashAttribute("todoOk", "El tipo de servicio se eliminó correctamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tipos-servicio";
    }

    private void cargarDatosTipoServicio(Model model) {
        var tiposServicio = tipoServicioService.getTiposServicio();
        model.addAttribute("tiposServicio", tiposServicio);
        model.addAttribute("totalTiposServicio", tiposServicio.size());
        model.addAttribute("tipoServicio", new TipoServicio());
    }
}
