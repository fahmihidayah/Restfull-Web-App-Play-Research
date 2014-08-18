package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Account extends Model {
	
	@Id
	public Long id;
	
	@Required
	@Column(unique = true)
	public String userName;
	
	@Required
	public String password;
	
	public static Finder<Long, Account> finder = new Finder<Long,Account>(Long.class, Account.class);
}
