package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Programa;
import pe.edu.upc.service.IProgramaService;

@Controller
@RequestMapping("/programas")
public class ProgramaController {

	@Autowired
	private IProgramaService pService;

	@GetMapping("/nuevo")
	public String nuevoPrograma(Model model) {
		model.addAttribute("programa", new Programa());
		return "programa/programa";
	}

	@RequestMapping("/guardar")
	public String guardarPrograma(@ModelAttribute @Valid Programa programa, BindingResult binRes, Model model,
			SessionStatus status) throws ParseException {
		if (binRes.hasErrors()) {

			return "/programa/programa";
		} else {
			pService.insertar(programa);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/programas/listar";
		}
	}

	@GetMapping("/listar")
	public String listarProgramas(Model model) {
		try {
			model.addAttribute("programa", new Programa());

			model.addAttribute("listaProgramas", pService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/programa/listaPrograma";
	}

	@GetMapping("/detalle/{id}")
	public String detailsPrograma(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Programa> programa = pService.listarId(id);

			if (!programa.isPresent()) {
				model.addAttribute("info", "Programa no existe");
				return "redirect:/programas/listar";
			} else {
				model.addAttribute("programa", programa.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/programa/programa";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Programa> programa = pService.listarId(id);
		if (programa == null) {
			flash.addFlashAttribute("error", "El Programa no existe en la base de datos");
			return "redirect:/programas/listar";
		}

		model.put("programa", programa.get());

		return "programa/verPrograma";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Programa> objPr = pService.listarId(id);

		if (objPr == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/programas/listar";
		} else {
			model.addAttribute("listaProgramas", pService.listar());

			model.addAttribute("programa", objPr.get());
			return "programa/programa";
		}
	}
	
}
