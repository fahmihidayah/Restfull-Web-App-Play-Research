package fahmi.lib;

import play.data.Form;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonHandler {

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
