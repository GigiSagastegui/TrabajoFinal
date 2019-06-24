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

import pe.edu.upc.entity.Detalle;
import pe.edu.upc.service.IAuditoriaService;
import pe.edu.upc.service.IDetalleService;
import pe.edu.upc.service.IProcesoService;

@Controller
@RequestMapping("/detalles")
public class DetalleController {

	@Autowired
	private IDetalleService dService;
	@Autowired
	private IProcesoService pService;
	@Autowired
	private IAuditoriaService aService;
	
	

	@GetMapping("/nuevo")
	public String nuevoDetalle(Model model) {
		model.addAttribute("detalle", new Detalle());
		model.addAttribute("listaProcesos", pService.listar());
		model.addAttribute("listaAuditorias", aService.listar());
		return "detalle/detalle";
	}

	@PostMapping("/guardar")
	public String guardarDetalle(@Valid Detalle detalle, RedirectAttributes flash, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaProcesos", pService.listar());
			model.addAttribute("listaAuditorias", aService.listar());
			return "/detalle/detalle";
		} else {
			dService.insertar(detalle);
			flash.addFlashAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/detalles/listar";
		}

	}

	@GetMapping("/listar")
	public String listarDetalles(Model model) {
		try {
			model.addAttribute("detalle", new Detalle());

			model.addAttribute("listaDetalles", dService.listar());
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/detalle/listaDetalle";
	}

	@GetMapping("/detalle/{id}")
	public String detailsDetalle(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Detalle> detalle = dService.listarId(id);

			if (!detalle.isPresent()) {
				model.addAttribute("info", "Detalle no existe");
				return "redirect:/detalles/listar";
			} else {
				model.addAttribute("detalle", detalle.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/detalle/detalle";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Detalle detalle) throws ParseException {

		List<Detalle> listaDetalles;

		detalle.setResultadoAuditoria(detalle.getResultadoAuditoria());
		listaDetalles = dService.buscarResultado(detalle.getResultadoAuditoria());

		

		if (listaDetalles.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaDetalles", listaDetalles);
		return "detalle/listaDetalle";

	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Detalle> detalle = dService.listarId(id);
		if (detalle == null) {
			flash.addFlashAttribute("error", "El Detalle no existe en la base de datos.");
			return "redirect:/detalles/listar";
		}

		model.put("detalle", detalle.get());

		return "detalle/ver";
	}

}
