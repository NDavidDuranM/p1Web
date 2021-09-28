package com.pucmm.isc517;

import com.pucmm.isc517.Entidades.Estudiante;
import com.pucmm.isc517.Services.EstudianteService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Isc517Application {

	public static void main(String[] args) {
		SpringApplication.run(Isc517Application.class, args);
	}

}
