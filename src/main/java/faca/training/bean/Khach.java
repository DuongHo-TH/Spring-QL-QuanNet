package faca.training.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "KhachHang")
public class Khach {
	@Id
	@Pattern(regexp = "DV\\d{5}", message = "ID phải có định dạng DVxxx")
	private String makh;
	@Column(name = "TEN_KH")
	@NotBlank(message = "Tên Khách Hàng không được để trống")
	private String tenKH;
	@Column(name = "DIA_CHI")
	@NotBlank(message = "Địa Chỉ không được bỏ trống")
	private String diachi;
	@Column(name = "PHONE")
	@Pattern(regexp = "(\\+84|0)[0-9]{9}" , message = "Số Điện Thoại Không Đúng Định Dạng")
	private String phone;
	@Column(name = "Email")
	@Email(message ="Email không đúng định dạng")
	private String email;
	@OneToMany(mappedBy="makh", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<SuDungMay> sudungmay;
	@OneToMany(mappedBy="makh", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<SuDungDichVu> sudungdv;
	
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMakh() {
		return makh;
	}
	public void setMakh(String makh) {
		this.makh = makh;
	}
	public List<SuDungMay> getSudungmay() {
		return sudungmay;
	}
	public void setSudungmay(List<SuDungMay> sudungmay) {
		this.sudungmay = sudungmay;
	}
	public List<SuDungDichVu> getSudungdv() {
		return sudungdv;
	}
	public void setSudungdv(List<SuDungDichVu> sudungdv) {
		this.sudungdv = sudungdv;
	}
	
	public Khach(String makh, String tenKH, String diachi, String phone, String email) {
		super();
		this.makh = makh;
		this.tenKH = tenKH;
		this.diachi = diachi;
		this.phone = phone;
		this.email = email;
		
	}
	public Khach() {
		super();
	}
	@Override
	public String toString() {
		return "Khach [getTenKH()=" + getTenKH() + ", getDiachi()=" + getDiachi() + ", getPhone()=" + getPhone()
				+ ", getEmail()=" + getEmail() + ", getMakh()=" + getMakh() +"]";
	}


}
