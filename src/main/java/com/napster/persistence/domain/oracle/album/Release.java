package com.napster.persistence.domain.oracle.album;

import java.sql.Timestamp;
import java.util.Date;

public class Release {
	private Long id;
	private Long album_id;
	private String upc;
	private String dupc;
	private Long imprint_id;
	private String catalog_id;
	private Long inventory_id;
	private Long data_source;
	private Date dateadded_to_rhapsody;
	private String status_code;
	private Timestamp dateadded;
	private String addedby;
	private Timestamp dateupdated;
	private String updatedby;
	private String ishires;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
	}

	public String getUpc() {
		return upc;
	}

	public void setUpc(String upc) {
		this.upc = upc;
	}

	public String getDupc() {
		return dupc;
	}

	public void setDupc(String dupc) {
		this.dupc = dupc;
	}

	public Long getImprint_id() {
		return imprint_id;
	}

	public void setImprint_id(Long imprint_id) {
		this.imprint_id = imprint_id;
	}

	public String getCatalog_id() {
		return catalog_id;
	}

	public void setCatalog_id(String catalog_id) {
		this.catalog_id = catalog_id;
	}

	public Long getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(Long inventory_id) {
		this.inventory_id = inventory_id;
	}

	public Long getData_source() {
		return data_source;
	}

	public void setData_source(Long data_source) {
		this.data_source = data_source;
	}

	public Date getDateadded_to_rhapsody() {
		return dateadded_to_rhapsody;
	}

	public void setDateadded_to_rhapsody(Date dateadded_to_rhapsody) {
		this.dateadded_to_rhapsody = dateadded_to_rhapsody;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}

	public Timestamp getDateadded() {
		return dateadded;
	}

	public void setDateadded(Timestamp dateadded) {
		this.dateadded = dateadded;
	}

	public String getAddedby() {
		return addedby;
	}

	public void setAddedby(String addedby) {
		this.addedby = addedby;
	}

	public Timestamp getDateupdated() {
		return dateupdated;
	}

	public void setDateupdated(Timestamp dateupdated) {
		this.dateupdated = dateupdated;
	}

	public String getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}

	public String getIshires() {
		return ishires;
	}

	public void setIshires(String ishires) {
		this.ishires = ishires;
	}
}
