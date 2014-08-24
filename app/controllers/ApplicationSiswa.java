package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.From;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fahmi.lib.CrudHandler;
import fahmi.lib.JsonHandler;
import models.Siswa;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.index;

public class ApplicationSiswa extends Controller {
	
	public static Form<Siswa> frmSiswa = Form.form(Siswa.class);
	public static CrudHandler<Siswa> crudHandler = new CrudHandler<Siswa>(true);
	
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result insert(){
    	return crudHandler.create(frmSiswa.bindFromRequest());	
    }
    
    public static Result list(){
    	return crudHandler.read(frmSiswa.bindFromRequest(), Siswa.finder);
    }
    
    public static Result edit(){
    	return crudHandler.update(frmSiswa.bindFromRequest());
    }
    public static Result delete(){
    	return crudHandler.delete(frmSiswa.bindFromRequest(), "nim", Siswa.finder);
    }
}
