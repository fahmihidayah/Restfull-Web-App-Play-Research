package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class OrangTua extends Model {
	@Id
	public Long idOrangTua;
	@Column(nullable = false)
	public String name;
	@Column(nullable = false)
	public String address;
	@Column(nullable = false)
	public String phoneNumber;

	public static Finder<Long, OrangTua> finder = new Finder<Long, OrangTua>(
			Long.class, OrangTua.class);

}
