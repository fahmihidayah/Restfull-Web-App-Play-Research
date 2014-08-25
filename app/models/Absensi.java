package models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Absensi extends Model{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long idAbsensi;
	
	@Required
    @Column(nullable = false)
    public Date date;
    
	@Required
    @Column(nullable = false)
    public Boolean hadir;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
    @JoinColumn(name = "GURU_NIK")
    public Guru guru;
    
	@Required
    @OneToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
	@JoinColumn(name = "MATAPELAJARAN_ID_MATAPELAJARAN")
    public MataPelajaran mataPelajaran;
    
	@Required
    @OneToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
	@JoinColumn(name = "SISWA_NIM")
    public Siswa siswa;
	
	@Column(name = "KETERANGAN")
	public String keterangan;
    
    public static Finder<String, Absensi> finder = new Finder<String, Absensi>(String.class, Absensi.class);
}
