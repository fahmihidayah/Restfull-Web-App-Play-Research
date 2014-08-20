package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.User;
import fahmi.lib.JsonHandler;
import play.data.Form;
import play.data.validation.Constraints.Required;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class SecurityController extends Controller{
	public final static String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String AUTH_TOKEN = "authToken";
    
    public static User getUser(){
    	return (User) Http.Context.current().args.get("user");
    }
    
    public static Result login(){
    	Form<Login> formLogin = Form.form(Login.class).bindFromRequest();
    	if(formLogin.hasErrors()){
    		return badRequest(JsonHandler.getSuitableResponse(formLogin, false));
    	}
    	
    	Login login = formLogin.get();
    	User user = User.findByEmailAddressAndPassword(login.userName, login.password);
    	if(user == null){
    		return badRequest(JsonHandler.getSuitableResponse("user not found", false));
    	}
    	else if(user.authToken != null){
    		return badRequest(JsonHandler.getSuitableResponse("user in used", false));
    	}
    	else {
    		String authToken = user.createToken();
    		response().setCookie(AUTH_TOKEN, authToken);
    		ObjectNode data = Json.newObject();
    		data.put(AUTH_TOKEN, authToken);
    		return ok(JsonHandler.getSuitableResponse(data, true));
    	}
    }
    
    public static Result logout(){
    	Form<Login> formLogin = Form.form(Login.class).bindFromRequest();
    	String authToken = formLogin.data().get("authToken");
    	if(authToken == null){
    		return badRequest(JsonHandler.getSuitableResponse("data not found", false));
    	}
    	
    	User user = User.findByAuthToken(authToken);
    	user.deleteAuthToken();
    	return ok(JsonHandler.getSuitableResponse("log out", true));
    }
    
    public static class Login {
    	@Required
    	public String userName;
    	@Required
    	public String password;
    	
    	public String authToken;
    	
    }
}
