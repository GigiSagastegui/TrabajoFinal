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

import pe.edu.upc.entity.Administrador;
import pe.edu.upc.service.IAdministradorService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/administradores")
public class AdministradorController {

	@Autowired
	private IAdministradorService aService;

	@Autowired
	private IUploadFileService uploadFileService;

	

	@GetMapping("/nuevo")
	public String nuevoAdministrador(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "administrador/administrador";
	}

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

	@RequestMapping("/guardar")
	public String guardarAdministrador(@ModelAttribute @Valid Administrador administrador, BindingResult binRes,
			Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			
			return "/administrador/administrador";
		} else {
			if (!foto.isEmpty()) {

				if (administrador.getIdPersona() > 0 && administrador.getFoto() != null
						&& administrador.getFoto().length() > 0) {

					uploadFileService.delete(administrador.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				administrador.setFoto(uniqueFilename);
			}

		}
		aService.insertar(administrador);
		model.addAttribute("mensaje", "Se guardó correctamente");
		status.setComplete();
		return "redirect:/administradores/listar";
	}

	@GetMapping("/listar")
	public String listarAdministradores(Model model) {
		try {
			model.addAttribute("administrador", new Administrador());
			model.addAttribute("listaAdministradores", aService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/administrador/listaAdministrador";
	}

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Administrador> administrador = aService.listarId(id);
		if (administrador == null) {
			flash.addFlashAttribute("error", "El Administrador no existe en la base de datos");
			return "redirect:/administradores/listar";
		}

		model.put("administrador", administrador.get());

		return "administrador/verAdministrador";
	}

	@GetMapping("/detalle/{id}")
	public String detailsAdministrador(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Administrador> administrador = aService.listarId(id);
			if (!administrador.isPresent()) {
				model.addAttribute("info", "Administrador no existe");
				return "redirect:/administradores/listar";
			} else {
				model.addAttribute("administrador", administrador.get());
			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/administrador/administrador";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Administrador administrador) throws ParseException {

		List<Administrador> listaAdministradores;

		administrador.setNamePersona(administrador.getNamePersona());
		listaAdministradores = aService.buscarName(administrador.getNamePersona());

		if (listaAdministradores.isEmpty()) {
			listaAdministradores = aService.buscarDni(administrador.getNamePersona());
		}

		if (listaAdministradores.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaAdministradores", listaAdministradores);
		return "administrador/listaAdministrador";

	}

}
