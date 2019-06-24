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

import pe.edu.upc.entity.Gerente;
import pe.edu.upc.service.IGerenteAreaService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/gerentes")
public class GerenteAreaController {

	@Autowired
	private IGerenteAreaService gService;

	@Autowired
	private PersonaController personaEncryp;

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
	public String nuevoGerente(Model model) {
		model.addAttribute("gerente", new Gerente());
		return "gerente/gerente";
	}

	@RequestMapping("/guardar")
	public String guardarAdministrador(@ModelAttribute @Valid Gerente gerente, BindingResult binRes, Model model,
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {

			return "/gerente/gerente";
		} else {
			if (!foto.isEmpty()) {

				if (gerente.getIdPersona() > 0 && gerente.getFoto() != null && gerente.getFoto().length() > 0) {

					uploadFileService.delete(gerente.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				gerente.setFoto(uniqueFilename);
			}

		}
		gerente.setPasswordUsuario(personaEncryp.getPasswordEncoder2().encode(gerente.getPasswordUsuario()));
		boolean flag = gService.insertar(gerente);
		if (flag) {
			return "redirect:/gerentes/listar";
		} else {
			model.addAttribute("mensaje", "Ocurrió un error");
			return "redirect:/gerentes/nuevo";
		}
	}

	@GetMapping("/listar")
	public String listarGerentes(Model model) {
		try {
			model.addAttribute("gerente", new Gerente());
			model.addAttribute("listaGerentes", gService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/gerente/listaGerente";
	}

	@GetMapping("/detalle/{id}")
	public String detailsGerente(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Gerente> gerente = gService.listarId(id);
			if (!gerente.isPresent()) {
				model.addAttribute("info", "Gerente no existe");
				return "redirect:/gerentes/listar";
			} else {
				model.addAttribute("gerente", gerente.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/gerente/gerente";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Gerente gerente) throws ParseException {

		List<Gerente> listaGerentes;

		gerente.setNamePersona(gerente.getNamePersona());
		listaGerentes = gService.buscarName(gerente.getNamePersona());

		if (listaGerentes.isEmpty()) {
			listaGerentes = gService.buscarDni(gerente.getNamePersona());
		}

		if (listaGerentes.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaGerentes", listaGerentes);
		return "gerente/listaGerente";

	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Gerente> gerente = gService.listarId(id);
		if (gerente == null) {
			flash.addFlashAttribute("error", "El Gerente no existe en la base de datos");
			return "redirect:/gerentes/listar";
		}

		model.put("gerente", gerente.get());

		return "gerente/verGerente";
	}

}
