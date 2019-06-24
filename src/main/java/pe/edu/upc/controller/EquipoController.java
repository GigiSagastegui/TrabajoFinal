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

import pe.edu.upc.entity.Equipo;
import pe.edu.upc.service.IAuditorService;
import pe.edu.upc.service.IAuditoriaService;
import pe.edu.upc.service.IEquipoService;

@Controller
@RequestMapping("/equipos")
public class EquipoController {
	@Autowired
	private IEquipoService eService;
	@Autowired
	private IAuditorService auService;
	@Autowired
	private IAuditoriaService aService;

	
	@GetMapping("/nuevo")
	public String nuevoEquipo(Model model) {
		model.addAttribute("equipo", new Equipo());
		model.addAttribute("listaAuditores", auService.listar());
		model.addAttribute("listaAuditorias", aService.listar());
		return "equipo/equipo";
	}

	@PostMapping("/guardar")
	public String guardarEquipo(@Valid Equipo equipo, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAuditores", auService.listar());
			model.addAttribute("listaAuditorias", aService.listar());
			return "/equipo/equipo";
		} else {
			eService.insertar(equipo);
			model.addAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/equipos/listar";
		}

	}

	@GetMapping("/listar")
	public String listarEquipos(Model model) {
		try {
			model.addAttribute("equipo", new Equipo());

			model.addAttribute("listaEquipos", eService.listar());
			
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/equipo/listaEquipo";
	}

	@GetMapping("/detalle/{id}")
	public String detailsEquipo(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Equipo> equipo = eService.listarId(id);

			if (!equipo.isPresent()) {
				model.addAttribute("info", "Equipo no existe");
				return "redirect:/equipos/listar";
			} else {
				model.addAttribute("equipo", equipo.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/equipo/equipo";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Equipo equipo) throws ParseException {

		List<Equipo> listaEquipos;

		equipo.setCargo(equipo.getCargo());
		listaEquipos = eService.buscarCargo(equipo.getCargo());

		if (listaEquipos.isEmpty()) {
			listaEquipos = eService.buscarLikeIgnoreCase(equipo.getCargo());

		}
		if (listaEquipos.isEmpty()) {

			listaEquipos = eService.buscarAuditoria(equipo.getCargo());

		}

		if (listaEquipos.isEmpty()) {
			model.put("mensaje", "No se encontrÃ³");
		}
		model.put("listaEquipos", listaEquipos);
		return "equipo/listaEquipo";

	}

	

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Equipo> objPro = eService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/equipos/listar";
		} else {
			model.addAttribute("listaAuditores", auService.listar());
			model.addAttribute("listaAuditorias", aService.listar());

			model.addAttribute("equipo", objPro.get());
			return "equipo/equipo";
		}
	}

}
