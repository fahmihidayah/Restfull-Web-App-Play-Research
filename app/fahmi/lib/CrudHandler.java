package fahmi.lib;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import controllers.Application;
import models.Siswa;
import play.data.Form;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CrudHandler<T extends Model> {

	public Result create(Form form){
    	if(form.hasErrors()){
    		return Controller.badRequest(JsonHandler.getSuitableResponse(form, false));
    	}else {
    		T data = (T) form.get();
    		data.save();
    		return Controller.ok(JsonHandler.getSuitableResponse(data, true));
    	}
    }
	
	public Result read(Finder finder){
		List<T> list = finder.all();
    	return Controller.ok(JsonHandler.getSuitableResponse(list, true));
		
	}
	
	public Result update(Form form){
		if(form.hasErrors()){
    		return Controller.badRequest(JsonHandler.getSuitableResponse(form, false));
    	}else {
    		T data = (T) form.get();
    		data.update();
    		return Controller.ok(JsonHandler.getSuitableResponse(data, true));
    	}
	}
	
	public Result delete(Form form, String id, Finder finder){
		System.out.print(form.data());
    	String idValue = (String) form.data().get(id);
    	if(idValue == null){
    		return Controller.badRequest(JsonHandler.getSuitableResponse("require " + id, false));
    	}else {
    		T siswa = (T) finder.byId(idValue);
    		if(siswa == null){
    			return Controller.badRequest(JsonHandler.getSuitableResponse("data not found", false));
    		}
    		siswa.delete();
    		return Controller.ok(JsonHandler.getSuitableResponse("success delete data", true));
    	}
	}
	
	
}
