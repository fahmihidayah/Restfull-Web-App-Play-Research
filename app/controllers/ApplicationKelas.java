package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;
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
		List<Kelas> listKelas = Kelas.finder.findList();
		return ok(JsonHandler.getSuitableResponse(Json.toJson(listKelas), true));
	}

	public static Result addSiswa(){
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
		String idKelas = frmKelas.data().get("idKelas");
		String nim = frmKelas.data().get("nim");
		if(idKelas == null || nim == null){
			return badRequest(JsonHandler.getSuitableResponse("require data", false));
		}
		else {
			Kelas kelas = Kelas.finder.byId(Long.parseLong(idKelas));
			Siswa siswa = Siswa.finder.byId(nim);
			if(kelas == null || siswa == null){
				return badRequest(JsonHandler.getSuitableResponse("data not found", false));
			}
			siswa.kelas = kelas;
			Ebean.save(siswa);
			kelas.listSiswa.add(siswa);
			Ebean.save(kelas);
			return ok(JsonHandler.getSuitableResponse("success insert siswa", true));
		}
	}
	public static Result removeSiswa(){
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
		String idKelas = frmKelas.data().get("idKelas");
		String nim = frmKelas.data().get("nim");
		if(idKelas == null || nim == null){
			return badRequest(JsonHandler.getSuitableResponse("require data", false));
		}
		else {
			Kelas kelas = Kelas.finder.byId(Long.parseLong(idKelas));
			Siswa siswa = Siswa.finder.byId(nim);
			if(kelas == null || siswa == null){
				return badRequest(JsonHandler.getSuitableResponse("data not found", false));
			}
			siswa.kelas = null;
			Ebean.save(siswa);
			kelas.listSiswa.remove(siswa);
			Ebean.save(kelas);
			return ok(JsonHandler.getSuitableResponse("success insert siswa", true));
		}
	}
}
