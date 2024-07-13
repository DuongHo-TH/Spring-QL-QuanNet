package faca.training.bean;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity

@Table(name = "SuDungDichVu")
public class SuDungDichVu {
	@Valid
	@EmbeddedId
	private SuDungDichVuId id;
	@MapsId("makh")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "makh")
	private Khach makh;
	@MapsId("madv")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "madv")
	private DichVu madv;
	@Column(name = "So_Luong")
	@Min(value = 0, message = "Giá trị phải là số nguyên dương")
	private int soluong;
	
	public SuDungDichVu() {
		super();
	}
	
	public SuDungDichVu(SuDungDichVuId id, Khach makh, DichVu madv, int soluong) {
		super();
		this.id = id;
		this.makh = makh;
		this.madv = madv;
		this.soluong = soluong;
	}
	public SuDungDichVuId getId() {
		return id;
	}
	public void setId(SuDungDichVuId id) {
		this.id = id;
	}
	public Khach getMakh() {
		return makh;
	}
	public void setMakh(Khach makh) {
		this.makh = makh;
	}
	public DichVu getMadv() {
		return madv;
	}
	public void setMadv(DichVu madv) {
		this.madv = madv;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	@Override
	public String toString() {
		return "SuDungDichVu [getSoluong()=" + getSoluong() + "]";
	}
	


}
