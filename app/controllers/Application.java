package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.From;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fahmi.lib.JsonHandler;
import models.Siswa;
import play.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public static Result insert(){
    	Form<Siswa> frmSiswa = Form.form(Siswa.class).bindFromRequest();
    	if(frmSiswa.hasErrors()){
    		return ok(JsonHandler.getSuitableResponse(JsonHandler.getErrorMessage(frmSiswa), false));
    	}else {
    		Siswa siswa = frmSiswa.get();
    		siswa.save();
    		return ok(JsonHandler.getSuitableResponse(Json.toJson(siswa), true));
    	}
    }
    
    public static Result list(){
    	List<Siswa> listSiswa = Siswa.finder.all();
    	return ok(JsonHandler.getSuitableResponse(Json.toJson(listSiswa), true));
    }
    
    
    
    public static Result edit(){
    	Form<Siswa> frmSiswa = Form.form(Siswa.class).bindFromRequest();
    	if(frmSiswa.hasErrors()){
    		return ok(JsonHandler.getSuitableResponse(JsonHandler.getErrorMessage(frmSiswa), false));
    	}else {
    		Siswa siswa = frmSiswa.get();
    		siswa.update();
    		return ok(JsonHandler.getSuitableResponse(Json.toJson(siswa), true));
    	}
    }
    public static Result delete(){
    	Form<Siswa> frmSiswa = Form.form(Siswa.class).bindFromRequest();
    	String nim = frmSiswa.data().get("nim");
    	if(nim == null){
    		return badRequest(JsonHandler.getSuitableResponse("require nim", false));
    	}else {
    		Siswa siswa = Siswa.finder.byId(nim);
    		if(siswa == null){
    			return badRequest(JsonHandler.getSuitableResponse("data not found", false));
    		}
    		siswa.delete();
    		return ok(JsonHandler.getSuitableResponse("success delete data", true));
    	}
    }
    
}
