package faca.training.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "May")
public class May {
	@Id
	@Column(name = "MaMay", unique = true)
	@NotBlank(message = "ID Máy không được bỏ trống")
	private String mamay;
	@Column(name = "ViTri")
	@NotBlank(message = "Vị Trí Máy không được bỏ trống")
	private String vitri;
	@Column(name = "TrangThai")
	@NotBlank(message = " Trạng Thái Máy không được bỏ trống")
	private String trangthai;
	@OneToMany(mappedBy = "mamay", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<SuDungMay> sudungmay;

	public List<SuDungMay> getSudungmay() {
		return sudungmay;
	}

	public void setSudungmay(List<SuDungMay> sudungmay) {
		this.sudungmay = sudungmay;
	}

	public String getMamay() {
		return mamay;
	}

	public void setMamay(String mamay) {
		this.mamay = mamay;
	}


	public String getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}


	public String getVitri() {
		return vitri;
	}

	public void setVitri(String vitri) {
		this.vitri = vitri;
	}

	public May() {
		super();
	}

	public May(String mamay, String vitri, String trangthai, List<SuDungMay> sudungmay) {
		super();
		this.mamay = mamay;
		this.vitri = vitri;
		this.trangthai = trangthai;
		this.sudungmay = sudungmay;
	}

	@Override
	public String toString() {
		return "May [" + getVitri() + "]";
	}

	

}
