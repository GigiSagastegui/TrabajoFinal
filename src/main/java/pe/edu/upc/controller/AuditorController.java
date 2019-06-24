package pe.edu.upc.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Auditor;
import pe.edu.upc.service.IAuditorService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/auditores")
public class AuditorController {

	@Autowired
	private IAuditorService auService;

	@Autowired
	private IUploadFileService uploadFileService;
	
	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;

		try {
			recurso = uploadFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}



	@GetMapping("/nuevo")
	public String nuevoAuditor(Model model) {
		model.addAttribute("auditor", new Auditor());
		return "auditor/auditor";
	}

	@RequestMapping("/guardar")
	public String guardarAdministrador(@ModelAttribute @Valid Auditor auditor, BindingResult binRes,
			Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			
			return "/auditor/auditor";
		} else {
			if (!foto.isEmpty()) {

				if (auditor.getIdPersona() > 0 && auditor.getFoto() != null
						&& auditor.getFoto().length() > 0) {

					uploadFileService.delete(auditor.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				auditor.setFoto(uniqueFilename);
			}

		}
		auService.insertar(auditor);
		model.addAttribute("mensaje", "Se guardó correctamente");
		status.setComplete();
		return "redirect:/auditores/listar";
	}



	@GetMapping("/listar")
	public String listarAuditores(Model model) {
		try {
			model.addAttribute("auditor", new Auditor());
			model.addAttribute("listaAuditores", auService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/auditor/listaAuditor";
	}
	
	@GetMapping("/detalle/{id}")
	public String detailsAudior(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Auditor> auditor = auService.listarId(id);
			if (!auditor.isPresent()) {
				model.addAttribute("info", "Auditor no existe");
				return "redirect:/auditores/listar";
			} else {
				model.addAttribute("auditor", auditor.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/auditor/auditor";
	}
	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Auditor auditor) throws ParseException {

		List<Auditor> listaAuditores;

		auditor.setNamePersona(auditor.getNamePersona());
		listaAuditores = auService.buscarName(auditor.getNamePersona());

		if (listaAuditores.isEmpty()) {
			listaAuditores = auService.buscarDni(auditor.getNamePersona());
		}
		
		
		if (listaAuditores.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAuditores", listaAuditores);
		return "auditor/listaAuditor";

	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Auditor> auditor = auService.listarId(id);
		if (auditor == null) {
			flash.addFlashAttribute("error", "El Auditor no existe en la base de datos");
			return "redirect:/auditores/listar";
		}

		model.put("auditor", auditor.get());

		return "auditor/verAuditor";
	}
}
