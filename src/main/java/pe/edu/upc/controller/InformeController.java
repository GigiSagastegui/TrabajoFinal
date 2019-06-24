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

import pe.edu.upc.entity.Informe;
import pe.edu.upc.service.IAuditorService;
import pe.edu.upc.service.IAuditoriaService;
import pe.edu.upc.service.IInformeService;
import pe.edu.upc.service.IProcesoService;
import pe.edu.upc.service.ITareaService;

@Controller
@RequestMapping("/informes")
public class InformeController {

	@Autowired
	private IInformeService iService;
	@Autowired
	private IAuditorService auService;

	@Autowired
	private IAuditoriaService aService;
	
	@Autowired
	private IProcesoService pService;
	
	@Autowired
	private ITareaService tService;

	

	@GetMapping("/nuevo")
	public String nuevoInforme(Model model) {
		model.addAttribute("informe", new Informe());
		model.addAttribute("listaAuditores", auService.listar());
		model.addAttribute("listaProcesos", pService.listar());
		model.addAttribute("listaAuditorias", aService.listar());
		
		return "informe/informe";
	}

	@PostMapping("/guardar")
	public String guardarInforme(@Valid Informe informe, RedirectAttributes flash, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaAuditores", auService.listar());
			model.addAttribute("listaTareas", tService.listar());
			model.addAttribute("listaProcesos", pService.listar());
			model.addAttribute("listaAuditorias", aService.listar());
			return "/informe/informe";
		} else {
			iService.insertar(informe);
			flash.addFlashAttribute("mensaje", "Se guardó correctamente");
			status.setComplete();
			return "redirect:/informes/listar";
		}

	}

	@GetMapping("/listar")
	public String listarInformes(Model model) {
		try {
			model.addAttribute("informe", new Informe());

			model.addAttribute("listaInformes", iService.listar());

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/informe/listaInforme";
	}

	@GetMapping("/detalle/{id}")
	public String detailsInforme(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Informe> informe = iService.listarId(id);

			if (!informe.isPresent()) {
				model.addAttribute("info", "informe no existe");
				return "redirect:/informes/listar";
			} else {
				model.addAttribute("informe", informe.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/informe/informe";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Informe> informe = iService.listarId(id);
		if (informe == null) {
			flash.addFlashAttribute("error", "El informe no existe en la base de datos");
			return "redirect:/informes/listar";
		}

		model.put("informe", informe.get());

		return "informe/verInforme";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Informe informe) throws ParseException {

		List<Informe> listaInformes;

		informe.setDescripcionInforme(informe.getDescripcionInforme());

		listaInformes = iService.buscarAuditor(informe.getDescripcionInforme());

		//verificar :"v
		
		if (listaInformes.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaInformes", listaInformes);
		return "informe/listaInforme";

	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Informe> objPro = iService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/informes/listar";
		} else {
			model.addAttribute("listaAuditores", auService.listar());
		    model.addAttribute("listaTareas", tService.listar());
		    model.addAttribute("listaProcesos", pService.listar());
			model.addAttribute("listaAuditorias", aService.listar());

			model.addAttribute("informe", objPro.get());
			return "informe/informe";
		}
	}
	
	
	
	

}
