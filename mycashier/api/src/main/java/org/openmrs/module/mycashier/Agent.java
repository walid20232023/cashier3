package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "agent")
public class Agent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "caisse")
	private Long caisse;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "system_id", nullable = false, length = 50)
	private String systemId;
	
	@Column(name = "username", length = 50)
	private String username;
	
	@Column(name = "secret_question", length = 255)
	private String secretQuestion;
	
	@Column(name = "creator", nullable = false)
	private Integer creator;
	
	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	@Column(name = "changed_by")
	private Integer changedBy;
	
	@Column(name = "date_changed")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateChanged;
	
	@Column(name = "person_id")
	private Integer personId;
	
	@Column(name = "retired", nullable = false)
	private Boolean retired = false;
	
	@Column(name = "retired_by")
	private Integer retiredBy;
	
	@Column(name = "date_retired")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateRetired;
	
	@Column(name = "retire_reason", length = 255)
	private String retireReason;
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
	private String uuid = UUID.randomUUID().toString();
	
	// Default constructor
	public Agent() {
	}
	
	// Getters and setters
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getCaisse() {
		return caisse;
	}
	
	public void setCaisse(Long caisse) {
		this.caisse = caisse;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getSystemId() {
		return systemId;
	}
	
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getSecretQuestion() {
		return secretQuestion;
	}
	
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	
	public Integer getCreator() {
		return creator;
	}
	
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	public Date getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Integer getChangedBy() {
		return changedBy;
	}
	
	public void setChangedBy(Integer changedBy) {
		this.changedBy = changedBy;
	}
	
	public Date getDateChanged() {
		return dateChanged;
	}
	
	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}
	
	public Integer getPersonId() {
		return personId;
	}
	
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	
	public Boolean getRetired() {
		return retired;
	}
	
	public void setRetired(Boolean retired) {
		this.retired = retired;
	}
	
	public Integer getRetiredBy() {
		return retiredBy;
	}
	
	public void setRetiredBy(Integer retiredBy) {
		this.retiredBy = retiredBy;
	}
	
	public Date getDateRetired() {
		return dateRetired;
	}
	
	public void setDateRetired(Date dateRetired) {
		this.dateRetired = dateRetired;
	}
	
	public String getRetireReason() {
		return retireReason;
	}
	
	public void setRetireReason(String retireReason) {
		this.retireReason = retireReason;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
