package pe.edu.upc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Tarea;
import pe.edu.upc.service.IProcesoService;
import pe.edu.upc.service.ITareaService;

@Controller
@RequestMapping("/tareas")
public class TareaController {

	@Autowired
	private ITareaService tService;
	@Autowired
	private IProcesoService pService;
	


	@GetMapping("/nuevo")
	public String nuevoTarea(Model model) {
		model.addAttribute("tarea", new Tarea());
		model.addAttribute("listaProcesos", pService.listar());
		return "tarea/tarea";
	}

	@PostMapping("/guardar")
	public String guardarTarea(@Valid Tarea tarea, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaProcesos", pService.listar());
			return "/tarea/tarea";
		} else {
			tService.insertar(tarea);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/tareas/listar";
		}

	}
	
	@GetMapping("/listar")
	public String listarTareas(Model model) {
		try {
			model.addAttribute("tarea", new Tarea());

			model.addAttribute("listaTareas", tService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/tarea/listaTarea";
	}

	@GetMapping("/detalle/{id}")
	public String detailsProducto(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Tarea> tarea = tService.listarId(id);

			if (!tarea.isPresent()) {
				model.addAttribute("info", "Producto no existe");
				return "redirect:/tareas/listar";
			} else {
				model.addAttribute("tarea", tarea.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/tarea/tarea";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Tarea> objTar =tService.listarId(id);

		if (objTar == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/tareas/listar";
		} else {
			model.addAttribute("listaProcesos", pService.listar());

			model.addAttribute("tarea", objTar.get());
			return "tarea/tarea";
		}
	}
}
