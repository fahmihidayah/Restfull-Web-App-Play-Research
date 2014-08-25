package controllers;

import java.util.Date;
import java.util.Map;

import fahmi.lib.Constants;
import fahmi.lib.CrudHandler;
import fahmi.lib.JsonHandler;
import models.Absensi;
import models.Guru;
import models.MataPelajaran;
import models.Siswa;
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
		String message = crudHandler.findAuth(map);
		if(!message.equals(SUCCESS)){
			return badRequest(JsonHandler.getSuitableResponse(message, false));
		}
		
		Guru guru = Guru.finder.byId((String)map.get("nik"));
		if(guru == null){
			return badRequest(JsonHandler.getSuitableResponse("guru not found", false));
		}
		
		MataPelajaran mataPelajaran = MataPelajaran.finder.byId(Long.parseLong((String)map.get("idMataPelajaran")));
		if(mataPelajaran == null){
			return badRequest(JsonHandler.getSuitableResponse("matapelajaran not found", false));
		}
		
		Siswa siswa = Siswa.finder.byId((String)map.get("nim"));
		if(siswa == null){
			return badRequest(JsonHandler.getSuitableResponse("siswa not found", false));
		}
		
		Absensi absensi = new Absensi();
		Boolean hadir = (((String) map.get("hadir")).equalsIgnoreCase("1")? true: false);
		absensi.hadir = hadir;
		absensi.date = new Date();
		absensi.guru = guru;
		absensi.siswa = siswa;
		absensi.keterangan = (String) map.get("keterangan");
		absensi.mataPelajaran = mataPelajaran;
		absensi.save();
		return ok(JsonHandler.getSuitableResponse("success insert absensi", true));
	}

	public static Result bulkAbsenSiswa(){
		return TODO;
	}
}
