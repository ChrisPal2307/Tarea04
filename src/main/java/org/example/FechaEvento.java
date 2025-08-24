package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaEvento {
	

	private LocalDate fecha;
	private Ubicacion ubi;
	
	public FechaEvento(LocalDate fecha, Ubicacion ubi) {
		this.fecha = fecha;
		this.ubi = ubi;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public Ubicacion getUbi() {
		return this.ubi;
	}
	
	public void setFecha (LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public void setUbi(Ubicacion ubi) {
		this.ubi = ubi;
	}
	
	@Override
	public String toString() {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLL, uuuu");
		
		return ("\n- " + ubi + "\n" + fecha.getDayOfWeek()+ ", "+fecha.format(formatter));
	}
	
	public int cuantoFalta() {
		int dias = fecha.compareTo(LocalDate.now());
		System.out.println("Faltan " + dias + "dias para esta fecha.");
		return dias;
		
	}
	
	
}
