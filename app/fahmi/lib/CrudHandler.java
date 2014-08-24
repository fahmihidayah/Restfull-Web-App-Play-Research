package fahmi.lib;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import controllers.ApplicationSiswa;
import models.Siswa;
import models.User;
import play.api.libs.iteratee.Cont;
import play.data.Form;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class CrudHandler<T extends Model> {
	public static String AUTH_NOT_FOUND = "require auth key";
	public static String USER_NOT_FOUND = "You're not login yet";
	public static String SUCCESS = "OK";
	
	public boolean checkAuth = false;
	
	public CrudHandler(boolean checkAuth) {
		super();
		this.checkAuth = checkAuth;
	}

	
	public CrudHandler() {
		super();
	}


	public Result create(Form form){
		if(checkAuth){
			String authMessage = findAuth(form);
			if(!authMessage.equals(SUCCESS)){
				return Controller.badRequest(authMessage);
			}
		}
    	if(form.hasErrors()){
    		return Controller.badRequest(JsonHandler.getSuitableResponse(form, false));
    	}else {
    		T data = (T) form.get();
    		data.save();
    		return Controller.ok(JsonHandler.getSuitableResponse(data, true));
    	}
    }
	
	public Result read(Form form, Finder finder){
		if(checkAuth){
			String authMessage = findAuth(form);
			if(!authMessage.equals(SUCCESS)){
				return Controller.badRequest(authMessage);
			}
		}
		List<T> list = finder.all();
    	return Controller.ok(JsonHandler.getSuitableResponse(list, true));
		
	}
	
	public Result update(Form form){
		if(checkAuth){
			String authMessage = findAuth(form);
			if(!authMessage.equals(SUCCESS)){
				return Controller.badRequest(authMessage);
			}
		}
		if(form.hasErrors()){
    		return Controller.badRequest(JsonHandler.getSuitableResponse(form, false));
    	}else {
    		T data = (T) form.get();
    		data.update();
    		return Controller.ok(JsonHandler.getSuitableResponse(data, true));
    	}
	}
	
	public Result delete(Form form, String id, Finder finder){
		if(checkAuth){
			String authMessage = findAuth(form);
			if(!authMessage.equals(SUCCESS)){
				return Controller.badRequest(authMessage);
			}
		}
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
	
	public String findAuth(Form form){
		String auth_key = (String) form.data().get("auth_key");
		if(auth_key == null) {
			return AUTH_NOT_FOUND;
		}
		User user = User.findByAuthToken(auth_key);
		if(user == null){
			return USER_NOT_FOUND;
		}
		return SUCCESS;
	}
	
	public void setCheckAuth(boolean checkAuth) {
		this.checkAuth = checkAuth;
	}
	
	
	
}
