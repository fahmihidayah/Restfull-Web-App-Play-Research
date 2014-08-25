package controllers;

import java.util.Map;

import fahmi.lib.Constants;
import fahmi.lib.CrudHandler;
import fahmi.lib.JsonHandler;
import models.Absensi;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ApplicationAbsensi extends Controller implements Constants{
	
	public static Form<Absensi> frmAbsensi = Form.form(Absensi.class);
	public static CrudHandler<Absensi> crudHandler = new CrudHandler<Absensi>();
	public static Result insertAbsenSiswa(){
		Form<Absensi> frmAbsensiBnd = frmAbsensi.bindFromRequest();
		String [] listKey = {"auth_key", "hadir", "nik", "idMataPelajaran", "nim", "keterangan"};
		Map<String, Object> map = crudHandler.findKey(frmAbsensiBnd, listKey);
		if(map.containsKey(ERROR)){
			return badRequest(JsonHandler.getSuitableResponse(map.get(ERROR), false));
		}
		return ok("ok");
	}

	public static Result bulkAbsenSiswa(){
		return TODO;
	}
}
