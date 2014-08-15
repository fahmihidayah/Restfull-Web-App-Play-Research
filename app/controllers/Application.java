package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.From;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
    		return ok(getSuitableResponse(getErrorMessage(frmSiswa), false));
    	}else {
    		Siswa siswa = frmSiswa.get();
    		siswa.save();
    		return ok(getSuitableResponse(Json.toJson(siswa), true));
    	}
    }
    
    public static Result list(){
    	List<Siswa> listSiswa = Siswa.finder.all();
    	return ok(getSuitableResponse(Json.toJson(listSiswa), true));
    }
    
    
    
    public static Result edit(){
    	Form<Siswa> frmSiswa = Form.form(Siswa.class).bindFromRequest();
    	if(frmSiswa.hasErrors()){
    		return ok(getSuitableResponse(getErrorMessage(frmSiswa), false));
    	}else {
    		Siswa siswa = frmSiswa.get();
    		siswa.update();
    		return ok(getSuitableResponse(Json.toJson(siswa), true));
    	}
    }
    public static Result delete(){
    	Form<Siswa> frmSiswa = Form.form(Siswa.class).bindFromRequest();
    	String nim = frmSiswa.data().get("nim");
    	if(nim == null){
    		return badRequest(getSuitableResponse("require nim", false));
    	}else {
    		Siswa siswa = Siswa.finder.byId(nim);
    		if(siswa == null){
    			return badRequest(getSuitableResponse("data not found", false));
    		}
    		siswa.delete();
    		return ok(getSuitableResponse("success delete data", true));
    	}
    }
    
    public static ObjectNode getSuitableResponse(Object data, boolean success){
    	ObjectNode node = (success? getSuccessObjectNode(): getFailureObjectNode());
    	if(data instanceof JsonNode){
    		node.put("data", (JsonNode) data);
    	}
    	else {
    		node.put("data", (String) data);
    	}
    	return node;
    }
    
    public static ObjectNode getSuccessObjectNode(){
    	ObjectNode node = Json.newObject();
    	node.put("status", "200");
    	node.put("message", "success");
    	return node;
    }
    public static ObjectNode getFailureObjectNode(){
    	ObjectNode node = Json.newObject();
    	node.put("status", "404");
    	node.put("message", "error");
    	return node;
    }
    public static String getErrorMessage(Form errorForm){
    	String error = errorForm.errorsAsJson().toString();
    	error = error.replace("{", "");
    	error = error.replace("]", "");
    	error = error.replace("[", "");
    	error = error.replace("}", "");
    	error = error.replace("\"", "");
    	return error;
    }
}
