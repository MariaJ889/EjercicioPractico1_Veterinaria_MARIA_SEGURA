package com.caso01.veterinaria.Controller;

import com.caso01.veterinaria.Domain.Servicio;
import com.caso01.veterinaria.Domain.TipoServicio;
import com.caso01.veterinaria.Service.ServicioService;
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
@RequestMapping("/servicios")
public class ServicioController {

    private final ServicioService servicioService;
    private final TipoServicioService tipoServicioService;

    //listado de todos
    @GetMapping
    public String listado(Model model) {
        cargarDatosCatalogo(model);
        return "catalogo/listado";
    }

    private void cargarDatosCatalogo(Model model) {
        var servicios = servicioService.getServicios();
        var tiposServicio = tipoServicioService.getTiposServicio();
        model.addAttribute("servicios", servicios);
        model.addAttribute("totalServicios", servicios.size());
        Servicio nuevoServicio = new Servicio();
        nuevoServicio.setTipoServicio(new TipoServicio());
        model.addAttribute("servicio", nuevoServicio);
        model.addAttribute("tiposServicio", tiposServicio);
        model.addAttribute("totalTiposServicio", tiposServicio.size());
        model.addAttribute("tipoServicio", new TipoServicio());
    }

    //Guardar
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        cargarDatosCatalogo(model);
        return "catalogo/listado";
    }

    //Editar
    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model,
                         RedirectAttributes redirectAttributes) {
        var servicio = servicioService.getServicio(id);
        if (servicio.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "No se encontró el servicio.");
            return "redirect:/servicios";
        }
        cargarDatosCatalogo(model);
        model.addAttribute("servicio", servicio.get());
        return "catalogo/listado";
    }

    //Guardar o editar segun corresponda
    @PostMapping("/guardar")
    public String guardar(@Valid Servicio servicio, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el servicio.");
            return "redirect:/servicios";
        }

        try {
            servicioService.save(servicio);
            redirectAttributes.addFlashAttribute("todoOk", "El servicio se guardó correctamente.");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el servicio.");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo guardar el servicio.");
        }
        return "redirect:/servicios";
    }

    //Eliminar
    @PostMapping("/eliminar")
    public String eliminar(@RequestParam(value = "id", required = false) Integer id,
                           RedirectAttributes redirectAttributes) {
        if (id == null) {
            redirectAttributes.addFlashAttribute("error", "No se recibió el servicio que desea eliminar.");
            return "redirect:/servicios";
        }

        try {
            servicioService.delete(id);
            redirectAttributes.addFlashAttribute("todoOk", "El servicio se eliminó correctamente.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/servicios";
    }
}
