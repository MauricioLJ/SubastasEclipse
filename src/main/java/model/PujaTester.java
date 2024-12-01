package model;

import java.util.List;

import servicio.ServicioPuja;
import servicio.ServicioSubasta;
import servicio.ServicioUsuario;

public class PujaTester {

	public static void main(String[] args) {
		try {
			
			// Create services
			ServicioUsuario servicioUsuario = new ServicioUsuario();
			ServicioSubasta servicioSubasta = new ServicioSubasta();
			ServicioPuja servicioPuja = new ServicioPuja();

			Usuario usuario = servicioUsuario.obtenerUsuarioPorId(2);

			Subasta subasta = servicioSubasta.leerSubasta(17); // Replace with actual auction ID
			/**
			// 3. Create a new bid
			Puja nuevaPuja = new Puja();
			nuevaPuja.setMonto(160.50); // Set the bid amount
			nuevaPuja.setUsuario(usuario);
			nuevaPuja.setSubasta(subasta);

			// 4. Persist the bid
			servicioPuja.crearPuja(nuevaPuja);

			// 5. Verify the bid was created
			System.out.println("Puja creada exitosamente:");
			System.out.println("ID Puja: " + nuevaPuja.getIdPuja());
			System.out.println("Monto: " + nuevaPuja.getMonto());
			System.out.println("Fecha Puja: " + nuevaPuja.getFechaPuja());
			System.out.println("Usuario: " + nuevaPuja.getUsuario().getNombre());
			System.out.println("Subasta: " + nuevaPuja.getSubasta().getNombre());
			*/
			
			List<Puja> pujas = servicioPuja.listaPujasPorSubasta(17);
			
			if (pujas.isEmpty()) {
	            System.out.println("No hay pujas para la subasta con ID: 17");
	        } else {
	            System.out.println("Pujas para la subasta con ID: 17");
	            for (Puja puja : pujas) {
	                System.out.println("-----------------------------");
	                System.out.println("ID Puja: " + puja.getIdPuja());
	                System.out.println("Monto: " + puja.getMonto());
	                System.out.println("Fecha Puja: " + puja.getFechaPuja());
	                System.out.println("Usuario: " + (puja.getUsuario() != null ? puja.getUsuario().getNombre() : "Desconocido"));
	                System.out.println("Subasta: " + (puja.getSubasta() != null ? puja.getSubasta().getNombre() : "Desconocida"));
	            }
	        }

		} catch (Exception e) {
			System.err.println("Error al crear la puja: " + e.getMessage());
			e.printStackTrace();
		}

	}

}
