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

import pe.edu.upc.entity.Auditoria;
import pe.edu.upc.service.IAuditoriaService;
import pe.edu.upc.service.IProcesoService;

@Controller
@RequestMapping("/auditorias")
public class AuditoriaController {
	
	@Autowired
	private IAuditoriaService aService;
	
	@Autowired
	private IProcesoService prService;
	
	@GetMapping("/nuevo")
	public String nuevoAuditoria(Model model) {
		model.addAttribute("auditoria", new Auditoria());
		model.addAttribute("listaProcesos", prService.listar());
		return "auditoria/auditoria";
	}
	
	@PostMapping("/guardar")
	public String guardarAuditoria(@Valid Auditoria auditoria, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "/auditoria/auditoria";
		} else {
			aService.insertar(auditoria);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/auditorias/listar";
		}
	}
	
	@GetMapping("/listar")
	public String listarAuditorias(Model model) {
		try {
			model.addAttribute("auditoria", new Auditoria());
			model.addAttribute("listaAuditorias", aService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/auditoria/listaAuditoria";
	}
	
	@GetMapping("/detalle/{id}")
	public String detailsAuditoria(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Auditoria> auditoria = aService.listarId(id);
			if (!auditoria.isPresent()) {
				model.addAttribute("info", "Auditoria no existe");
				return "redirect:/auditorias/listar";
			} else {
				model.addAttribute("auditoria", auditoria.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/auditoria/auditoria";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Auditoria auditoria) throws ParseException {

		List<Auditoria> listaAuditorias;

		auditoria.setEstado(auditoria.getEstado());
		listaAuditorias = aService.buscarEstado(auditoria.getEstado());

		if (listaAuditorias.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAuditorias", listaAuditorias);
		return "auditoria/listaAuditoria";

	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Auditoria> auditoria = aService.listarId(id);
		if (auditoria == null) {
			flash.addFlashAttribute("error", "Auditoría no existe en la base de datos");
			return "redirect:/auditorias/listar";
		}

		model.put("auditoria", auditoria.get());

		return "auditoria/ver";
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Auditoria> objAudi = aService.listarId(id);

		if (objAudi == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/auditorias/listar";
		} else {
			model.addAttribute("listaProcesos", prService.listar());

			model.addAttribute("auditoria", objAudi.get());
			return "auditoria/auditoria";
		}
	}
}
