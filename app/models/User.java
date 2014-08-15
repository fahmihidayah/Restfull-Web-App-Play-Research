package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class User extends Model {
	
	@Id
	public Long id;
	
	@Required
	public String userName;
	
	@Required
	public String password;
	
	public static Finder<Long, User> finder = new Finder<Long,User>(Long.class, User.class);
}
