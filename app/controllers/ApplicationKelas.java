package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import fahmi.lib.JsonHandler;
import models.Kelas;
import models.Siswa;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.*;
public class ApplicationKelas extends Controller{
	public static Result insert(){
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
		if(frmKelas.hasErrors()){
			return badRequest(JsonHandler.getSuitableResponse(JsonHandler.getErrorMessage(frmKelas), false));
		}
		else {
			Kelas kelas = frmKelas.get();
			kelas.save();
			return ok(JsonHandler.getSuitableResponse(Json.toJson(kelas), true));
		}
	}
	
	public static Result delete(){
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
    	String idKelas = frmKelas.data().get("idKelas");
    	if(idKelas == null){
    		return badRequest(JsonHandler.getSuitableResponse("require nim", false));
    	}else {
    		Kelas kelas = Kelas.finder.byId(Long.parseLong(idKelas));
    		if(kelas == null){
    			return badRequest(JsonHandler.getSuitableResponse("data not found", false));
    		}
    		kelas.delete();
    		return ok(JsonHandler.getSuitableResponse("success delete data", true));
    	}
	}
	
	public static Result edit(){
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
		if(frmKelas.hasErrors()){
			return badRequest(JsonHandler.getSuitableResponse(JsonHandler.getErrorMessage(frmKelas), false));
		}
		else {
			Kelas kelas = frmKelas.get();
			kelas.update();
			return ok(JsonHandler.getSuitableResponse(Json.toJson(kelas), true));
		}
	}
	
	public static Result list(){
		List<Kelas> listKelas = Kelas.finder.all();
		return ok(JsonHandler.getSuitableResponse(Json.toJson(listKelas), true));
	}

}
