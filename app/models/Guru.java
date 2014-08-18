package models;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

public class Guru extends Model{
	@Id
	@Required
	public String nik;
	@Required
	public String name;
	@Required
	public String address;
	@Required
	public String phone;
	public String email;
	@OneToOne
	public Account account;
	
	public static Finder<String, Guru> finder = new Finder<String, Guru>(String.class, Guru.class);
}
