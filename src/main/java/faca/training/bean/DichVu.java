package faca.training.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "DichVu")
public class DichVu {
	@Id
	@Column(name = "MA_DV")
	@Pattern(regexp = "DV\\d{3}", message = "ID phải có định dạng DVxxx")
	private String madv;
	@Column(name = "TEN_DV")
	@NotBlank(message = "Không được để trốngng tên dịch vụ")
	private String tendv;
	@Column(name = "DON_VI_TINH")
	@NotBlank(message = "Không được để trống đơn giá")
	private String donvi;
	@Column(name = "DON_GIA")
	@Min(value = 0, message = "Giá trị phải là số nguyên dương")
	private float dongia;
	@OneToMany(mappedBy = "madv", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<SuDungDichVu> sudungdv;

	public String getMadv() {
		return madv;
	}

	public void setMadv(String madv) {
		this.madv = madv;
	}

	public List<SuDungDichVu> getSudungdv() {
		return sudungdv;
	}

	public void setSudungdv(List<SuDungDichVu> sudungdv) {
		this.sudungdv = sudungdv;
	}

	public String getTendv() {
		return tendv;
	}

	public void setTendv(String tendv) {
		this.tendv = tendv;
	}

	public String getDonvi() {
		return donvi;
	}

	public void setDonvi(String soluong) {
		this.donvi = soluong;
	}

	public float getDongia() {
		return dongia;
	}

	public void setDongia(float dongia) {
		this.dongia = dongia;
	}

	public DichVu(String madv, String tendv, String donvi, float dongia, List<SuDungDichVu> sudungdv) {
		super();
		this.madv = madv;
		this.tendv = tendv;
		this.donvi = donvi;
		this.dongia = dongia;
		this.sudungdv = sudungdv;
	}

	public DichVu() {
		super();
	}

	@Override
	public String toString() {
		return "DichVu [getTendv()=" + getTendv() + ", getDonvi()=" + getDonvi() + ", getDongia()=" + getDongia() + "]";
	}


}
