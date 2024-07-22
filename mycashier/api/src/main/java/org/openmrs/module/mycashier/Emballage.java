package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "emballage", schema = "cashier")
public class Emballage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Emballage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreation=" + dateCreation +
                ", userId=" + userId +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
