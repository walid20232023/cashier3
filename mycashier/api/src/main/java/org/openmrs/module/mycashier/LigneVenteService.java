package org.openmrs.module.mycashier;

import javax.persistence.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "ligne_vente_service", schema = "cashier")
public class LigneVenteService implements Serializable {
	
	@EmbeddedId
	private LigneVenteServiceId id;
	
	@ManyToOne
	@MapsId("venteServiceId")
	@JoinColumn(name = "vente_service_id")
	private VenteService venteService;
	
	@ManyToOne
	@MapsId("serviceId")
	@JoinColumn(name = "service_id")
	private Service service;

	//Construteurs

	public LigneVenteService() {
	}


	// Getters and Setters
	
	public LigneVenteServiceId getId() {
		return id;
	}
	
	public void setId(LigneVenteServiceId id) {
		this.id = id;
	}
	
	public VenteService getVenteService() {
		return venteService;
	}
	
	public void setVenteService(VenteService venteService) {
		this.venteService = venteService;
	}
	
	public Service getService() {
		return service;
	}
	
	public void setService(Service service) {
		this.service = service;
	}
	
	@Override
	public String toString() {
		return "LigneVenteService{" + "id=" + id + ", venteService=" + venteService + ", service=" + service + '}';
	}
	
	@Embeddable
	public static class LigneVenteServiceId implements Serializable {
		
		@Column(name = "vente_service_id")
		private Integer venteServiceId;
		
		@Column(name = "service_id")
		private Integer serviceId;
		
		// Default constructor
		
		public LigneVenteServiceId() {
		}
		
		// Parameterized constructor
		
		public LigneVenteServiceId(Integer venteServiceId, Integer serviceId) {
			this.venteServiceId = venteServiceId;
			this.serviceId = serviceId;
		}
		
		// Getters and Setters
		
		public Integer getVenteServiceId() {
			return venteServiceId;
		}
		
		public void setVenteServiceId(Integer venteServiceId) {
			this.venteServiceId = venteServiceId;
		}
		
		public Integer getServiceId() {
			return serviceId;
		}
		
		public void setServiceId(Integer serviceId) {
			this.serviceId = serviceId;
		}
		
	}
}
