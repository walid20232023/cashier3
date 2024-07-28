package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "stock_entrepot")
public class StockEntrepot implements Serializable {
	
	@EmbeddedId
	private StockEntrepotId id;
	
	@Column(name = "date_modification")
	private LocalDateTime localDateTime = LocalDateTime.now();
	
	@Column(name = "quantite_stock")
	private Integer quantiteStock;
	
	//Constructeurs
	
	public StockEntrepot() {
	}
	
	// Getters and Setters
	
	public StockEntrepotId getId() {
		return id;
	}
	
	public void setId(StockEntrepotId id) {
		this.id = id;
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	public Integer getQuantiteStock() {
		return quantiteStock;
	}
	
	public void setQuantiteStock(Integer quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	
	@Embeddable
	public static class StockEntrepotId implements Serializable {
		
		@Column(name = "entrepot_id")
		private Integer entrepotId;
		
		@Column(name = "my_drug_id")
		private Integer myDrugId;
		
		// Default constructor
		
		public StockEntrepotId() {
		}
		
		// Parameterized constructor
		
		public StockEntrepotId(Integer entrepotId, Integer myDrugId) {
			this.entrepotId = entrepotId;
			this.myDrugId = myDrugId;
		}
		
		// Getters and Setters
		
		public Integer getEntrepotId() {
			return entrepotId;
		}
		
		public void setEntrepotId(Integer entrepotId) {
			this.entrepotId = entrepotId;
		}
		
		public Integer getMyDrugId() {
			return myDrugId;
		}
		
		public void setMyDrugId(Integer myDrugId) {
			this.myDrugId = myDrugId;
		}
		
	}
}
