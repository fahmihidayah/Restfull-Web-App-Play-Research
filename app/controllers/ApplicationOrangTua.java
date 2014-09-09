package controllers;

import models.MataPelajaran;
import models.OrangTua;
import fahmi.lib.CrudHandler;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationOrangTua extends Controller{
	public static Form<OrangTua> frmMataPelajaran = Form.form(OrangTua.class);
	public static CrudHandler<OrangTua> crudHandler = new CrudHandler<OrangTua>(true);
	
	public static Result createOrangTua(){
		return crudHandler.create(frmMataPelajaran.bindFromRequest());
	}
	
	public static Result readOrangTua(){
		return crudHandler.read(frmMataPelajaran.bindFromRequest(), OrangTua.finder);
	}
	
	public static Result updateOrangTua(){
		return crudHandler.update(frmMataPelajaran.bindFromRequest());
	}
	
	public static Result deleteOrangTua(){
		return crudHandler.delete(frmMataPelajaran.bindFromRequest(), "idOrangTua", OrangTua.finder);
	}

	public static Result addSiswaToOrangTua(){
		return TODO;
	}
}
