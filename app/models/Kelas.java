package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
@Entity
public class Kelas extends Model{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long idKelas;

	@Required
	public String namaKelas;

	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Siswa> siswa = new ArrayList<Siswa>();

	public static Finder<Long,Kelas> finder = new Finder<Long,Kelas>(Long.class, Kelas.class);
}