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

import pe.edu.upc.entity.Proceso;
import pe.edu.upc.service.IAreaService;
import pe.edu.upc.service.IProcesoService;
import pe.edu.upc.service.IUploadFileService;

@Controller
@RequestMapping("/procesos")
public class ProcesoController {
	@Autowired
	private IProcesoService prService;
	@Autowired
	private IAreaService aService;
	@Autowired
	private IUploadFileService uploadFileService;


	@GetMapping("/nuevo")
	public String nuevoProceso(Model model) {
		model.addAttribute("proceso", new Proceso());
		model.addAttribute("listaAreas", aService.listar());
		return "proceso/proceso";
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
	public String guardarAdministrador(@ModelAttribute @Valid Proceso proceso, BindingResult binRes,
			Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status)
			throws ParseException {
		if (binRes.hasErrors()) {
			
			return "/proceso/proceso";
		} else {
			if (!foto.isEmpty()) {

				if (proceso.getIdProceso() > 0 && proceso.getFoto() != null
						&& proceso.getFoto().length() > 0) {

					uploadFileService.delete(proceso.getFoto());
				}

				String uniqueFilename = null;
				try {
					uniqueFilename = uploadFileService.copy(foto);
				} catch (IOException e) {
					e.printStackTrace();
				}

				flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename + "'");
				proceso.setFoto(uniqueFilename);
			}

		}
		prService.insertar(proceso);
		model.addAttribute("mensaje", "Se guardó correctamente");
		status.setComplete();
		return "redirect:/procesos/listar";
	}

	@GetMapping("/listar")
	public String listarProcesos(Model model) {
		try {
			model.addAttribute("proceso", new Proceso());

			model.addAttribute("listaProcesos", prService.listar());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/proceso/listaProceso";
	}

	@GetMapping("/detalle/{id}")
	public String detailsProceso(@PathVariable(value = "id") int id, Model model) {
		try {
			Optional<Proceso> proceso = prService.listarId(id);

			if (!proceso.isPresent()) {
				model.addAttribute("info", "Proceso no existe");
				return "redirect:/procesos/listar";
			} else {
				model.addAttribute("proceso", proceso.get());

			}

		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}

		return "/proceso/proceso";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Map<String, Object> model, RedirectAttributes flash) {

		Optional<Proceso> proceso = prService.listarId(id);
		if (proceso == null) {
			flash.addFlashAttribute("error", "El proceso no existe en la base de datos");
			return "redirect:/procesos/listar";
		}

		model.put("proceso", proceso.get());

		return "proceso/verProceso";
	}

	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Proceso proceso) throws ParseException {
		List<Proceso> listaProcesos;
		proceso.setDescripcionProceso(proceso.getDescripcionProceso());
		listaProcesos = prService.buscarArea(proceso.getDescripcionProceso());
		if (listaProcesos.isEmpty()) {
			model.put("mensaje", "No se encontró");
		}
		model.put("listaProcesos", listaProcesos);
		return "proceso/listaProceso";

	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Proceso> objPro = prService.listarId(id);

		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³Â³ un error");
			return "redirect:/procesos/listar";
		} else {
			model.addAttribute("listaAreas", aService.listar());

			model.addAttribute("proceso", objPro.get());
			return "proceso/proceso";
		}
	}
}
