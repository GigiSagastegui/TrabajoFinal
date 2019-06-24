package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Area;
import pe.edu.upc.service.IAreaService;
import pe.edu.upc.service.IGerenteAreaService;

@Controller
@RequestMapping("/areas")
public class AreaController {
	@Autowired
	private IGerenteAreaService geService;
	@Autowired
	private IAreaService aService;



	@GetMapping("/nuevo")
	public String nuevaArea(Model model) {
		model.addAttribute("area", new Area());
		model.addAttribute("listaGerentes", geService.listar());
		return "area/area";
	}

	@PostMapping("/guardar")
	public String guardarArea(@Valid Area area, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaGerentes", geService.listar());
			return "/area/area";
		} else {
			aService.insertar(area);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/areas/listar";
		}
	}

	@GetMapping("/listar")
	public String listarAreas(Model model) {
		try {
			model.addAttribute("area", new Area());

			model.addAttribute("listaAreas", aService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/area/listaArea";
	}

	@GetMapping("/detalle/{id}")
	public String detailsArea(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Area> area = aService.listarId(id);

			if (!area.isPresent()) {
				model.addAttribute("info", "Area no existe");
				return "redirect:/areas/listar";
			} else {
				model.addAttribute("area", area.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/area/area";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Area area) throws ParseException {

		List<Area> listaAreas;

		area.setNombreArea(area.getNombreArea());
		listaAreas = aService.buscar(area.getNombreArea());

		if (listaAreas.isEmpty()) {
			listaAreas = aService.buscarNombreCaso(area.getNombreArea());
		}

		if (listaAreas.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAreas", listaAreas);
		return "area/listaArea";

	}

	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Area> objAr = aService.listarId(id);

		if (objAr == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/areas/listar";
		} else {
			model.addAttribute("listaGerentes", geService.listar());

			model.addAttribute("area", objAr.get());
			return "area/area";
		}
	}
}
