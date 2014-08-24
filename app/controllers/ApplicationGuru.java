package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;

import fahmi.lib.CrudHandler;
import fahmi.lib.JsonHandler;
import models.Guru;
import models.Siswa;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.*;
public class ApplicationGuru extends Controller{
	public static Form<Guru> frmGuru = Form.form(Guru.class);
	public static CrudHandler<Guru> crudHandler = new CrudHandler<Guru>(true);
	public static Result insert(){
		return crudHandler.create(frmGuru.bindFromRequest());
	}
	
	public static Result delete(){
		return crudHandler.delete(frmGuru.bindFromRequest(), "nik", Guru.finder);
	}
	
	public static Result edit(){
		return crudHandler.update(frmGuru.bindFromRequest());
	}
	
	public static Result list(){
		return crudHandler.read(frmGuru.bindFromRequest(), Guru.finder);
	}

}
