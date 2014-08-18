package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;

import fahmi.lib.CrudHandler;
import fahmi.lib.JsonHandler;
import models.Account;
import models.Siswa;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.*;
public class ApplicationAccount extends Controller{
	public static Form<Account> frmAccount = Form.form(Account.class);
	public static CrudHandler<Account> crudHandler = new CrudHandler<Account>();
	public static Result insert(){
		return crudHandler.create(frmAccount.bindFromRequest());
	}
	
	public static Result delete(){
		return crudHandler.delete(frmAccount.bindFromRequest(), "id", Account.finder);
	}
	
	public static Result edit(){
		return crudHandler.update(frmAccount.bindFromRequest());
	}
	
	public static Result list(){
		return crudHandler.read(Account.finder);
	}
	
	public static Result addAccountToGuru(){
		return TODO;
	}

}
