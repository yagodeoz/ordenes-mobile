package com.alimesa.ordenpedido.vista.seguridad.mantenimiento;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.omnifaces.cdi.GraphicImageBean;

@GraphicImageBean
public class BeanImagen {

	public byte[] get(String ruta) {

		File file = new File(ruta);
		try {
			return Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

}
