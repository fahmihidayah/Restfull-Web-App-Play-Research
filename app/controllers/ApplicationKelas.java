package controllers;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.fasterxml.jackson.databind.JsonNode;

import fahmi.lib.CrudHandler;
import fahmi.lib.FormErrorHandler;
import fahmi.lib.JsonHandler;
import models.Kelas;
import models.Siswa;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.*;

public class ApplicationKelas extends Controller {
	public static Form<Kelas> frmKelas = Form.form(Kelas.class);
	public static CrudHandler<Kelas> crudHandler = new CrudHandler<Kelas>();

	public static Result insert() {
		return crudHandler.create(frmKelas.bindFromRequest());
	}

	public static Result delete() {
		return crudHandler.delete(frmKelas.bindFromRequest(), "idKelas",
				Kelas.finder);
	}

	public static Result edit() {
		return crudHandler.update(frmKelas.bindFromRequest());
	}

	public static Result list() {
		return crudHandler.read(Kelas.finder);
	}

	/**
	 * FormErrorHandler still in research
	 * @return
	 */
	public static Result addSiswa() {
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
		FormErrorHandler<Kelas, Siswa> formErrorHandler = new FormErrorHandler<Kelas, Siswa>(
				Kelas.finder, Siswa.finder) {

			@Override
			public String execute(Kelas owner, Siswa inverseOwner) {
				inverseOwner.kelas = owner;
				Ebean.save(inverseOwner);
				owner.listSiswa.add(inverseOwner);
				Ebean.save(owner);
				return "success add siswa to kelas";
			}
		};
		return formErrorHandler.checkAndExecute(frmKelas, "idKelas", "nim");
	}

	// public static Result addSiswa(){
	// Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
	// String idKelas = frmKelas.data().get("idKelas");
	// String nim = frmKelas.data().get("nim");
	// if(idKelas == null || nim == null){
	// return badRequest(JsonHandler.getSuitableResponse("require data",
	// false));
	// }
	// else {
	// Kelas kelas = Kelas.finder.byId(Long.parseLong(idKelas));
	// Siswa siswa = Siswa.finder.byId(nim);
	// if(kelas == null || siswa == null){
	// return badRequest(JsonHandler.getSuitableResponse("data not found",
	// false));
	// }
	// siswa.kelas = kelas;
	// Ebean.save(siswa);
	// kelas.listSiswa.add(siswa);
	// Ebean.save(kelas);
	// return ok(JsonHandler.getSuitableResponse("success insert siswa", true));
	// }
	// }
	public static Result removeSiswa() {
		Form<Kelas> frmKelas = Form.form(Kelas.class).bindFromRequest();
		String idKelas = frmKelas.data().get("idKelas");
		String nim = frmKelas.data().get("nim");
		if (idKelas == null || nim == null) {
			return badRequest(JsonHandler.getSuitableResponse("require data",
					false));
		} else {
			Kelas kelas = Kelas.finder.byId(Long.parseLong(idKelas));
			Siswa siswa = Siswa.finder.byId(nim);
			if (kelas == null || siswa == null) {
				return badRequest(JsonHandler.getSuitableResponse(
						"data not found", false));
			}
			siswa.kelas = null;
			Ebean.save(siswa);
			kelas.listSiswa.remove(siswa);
			Ebean.save(kelas);
			return ok(JsonHandler.getSuitableResponse("success insert siswa",
					true));
		}
	}
}
