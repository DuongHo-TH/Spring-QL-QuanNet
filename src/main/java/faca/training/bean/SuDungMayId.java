package faca.training.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
@Embeddable
public class SuDungMayId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "ID Khách Hàng không được để trống")
    private String makh;
	@NotNull(message = "ID Máy không được để trống")
    private String mamay;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "ID Máy không được để trống")
    private LocalDate ngayBatDauSuDung;
    @DateTimeFormat(pattern = "HH:mm")
    @NotNull(message = "ID Máy không được để trống")
    private LocalTime gioBatDauSuDung;
	public SuDungMayId() {
		super();
		
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getMamay() {
		return mamay;
	}

	public void setMamay(String mamay) {
		this.mamay = mamay;
	}

	public LocalDate getNgayBatDauSuDung() {
		return ngayBatDauSuDung;
	}
	public void setNgayBatDauSuDung(LocalDate ngayBatDauSuDung) {
		this.ngayBatDauSuDung = ngayBatDauSuDung;
	}
	public LocalTime getGioBatDauSuDung() {
		return gioBatDauSuDung;
	}
	public void setGioBatDauSuDung(LocalTime gioBatDauSuDung) {
		this.gioBatDauSuDung = gioBatDauSuDung;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public SuDungMayId(String makh, String mamay, LocalDate ngayBatDauSuDung, LocalTime gioBatDauSuDung) {
		super();
		this.makh = makh;
		this.mamay = mamay;
		this.ngayBatDauSuDung = ngayBatDauSuDung;
		this.gioBatDauSuDung = gioBatDauSuDung;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gioBatDauSuDung, makh, mamay, ngayBatDauSuDung);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SuDungMayId other = (SuDungMayId) obj;
		return Objects.equals(gioBatDauSuDung, other.gioBatDauSuDung) && makh == other.makh && mamay == other.mamay
				&& Objects.equals(ngayBatDauSuDung, other.ngayBatDauSuDung);
	}

	@Override
	public String toString() {
		return "SuDungMayId [getMakh()=" + getMakh() + ", getMamay()=" + getMamay() + ", getNgayBatDauSuDung()="
				+ getNgayBatDauSuDung() + ", getGioBatDauSuDung()=" + getGioBatDauSuDung() + ", hashCode()="
				+ hashCode() + "]";
	}

    
    
}
