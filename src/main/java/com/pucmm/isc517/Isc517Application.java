package com.pucmm.isc517;

import com.pucmm.isc517.Entidades.Estudiante;
import com.pucmm.isc517.Services.EstudianteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Isc517Application {

	public static void main(String[] args) {
		SpringApplication.run(Isc517Application.class, args);
	}

	@RestController
	@RequestMapping(path="/estudiante")
	static class Controlador
	{
		private EstudianteService estudianteService;


		@GetMapping(path ="/")
		public String getListaEstudiantes(Model model){
			model.addAttribute("listaEstudiante",estudianteService.getListaEstudiantes());
			model.addAttribute("titulo","Listar estudiante");
			return "/ListarEstudiante";
		}



		@GetMapping(path ="/nuevo")
		public String getNuevoEstudiante(Model model){
			model.addAttribute("titulo","Crear estudiante");
			return "/Estudiante";
		}


		@PostMapping(path ="/nuevo/save")
		public String postEstudiante(Model model,
									 @RequestParam("matricula") int matricula,
									 @RequestParam("nombre") String nombre,
									 @RequestParam("apellido") String apellido,
									 @RequestParam("telefono") String telefono)
		{
			if(!estudianteService.crearEstudiante(nombre,apellido,telefono,matricula))
			{
				Estudiante act= new Estudiante(matricula,nombre,apellido,telefono);
				return "Error matricula en uso";
			}

			model.addAttribute("listaEstudiante",estudianteService.getListaEstudiantes());
			model.addAttribute("action","New");
			return "/ListarEstudiante";
		}



		@PostMapping (path = "/update/{matricula}")
		public String putEstudiante(Model model, @PathVariable int matricula,
									@RequestParam("nombre") String nombre,
									@RequestParam("apellido") String apellido,
									@RequestParam("telefono") String telefono) {
			if (!estudianteService.updateEstudiante(nombre,apellido,telefono,matricula)) {
				return "Error 404, Estudiante no encontrado";
			}

			model.addAttribute("listaEstudiante",estudianteService.getListaEstudiantes());
			model.addAttribute("titulo","Listar estudiante");

			return "/ListarEstudiante";
		}


		@PostMapping(path ="/delete/{matricula}")
		public String deleteEstudiante(Model model,
									   @PathVariable int matricula)
		{
			if(!estudianteService.borrarEstudiante(matricula))
			{
				return "Error 404, Estudiante no encontrado";
			}

			model.addAttribute("listaEstudiante",estudianteService.getListaEstudiantes());
			model.addAttribute("titulo","Listar estudiante");
			return "/ListarEstudiante";
		}


		@GetMapping(path ="/ver/{matricula}")
		public String getEstudiante(Model model,@PathVariable int matricula){
			Estudiante act = estudianteService.buscarEstudiante(matricula);
			if(act==null)
			{
				return "Error 404, Estudiante no encontrado";
			}
			model.addAttribute("titulo","Ver estudiante: "+matricula);
			model.addAttribute("estudiante",act);
			model.addAttribute("action","Edit");
			return "/Estudiante";
		}


	}

}
