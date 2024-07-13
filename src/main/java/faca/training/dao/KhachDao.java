package faca.training.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import faca.training.bean.Khach;

@Repository
public class KhachDao {

	@PersistenceContext
	private EntityManager entity;
	
	public List<Khach> findpage(int page) {
		int fristresult = (page - 1) * 5;
		return entity.createQuery("FROM Khach", Khach.class).setFirstResult(fristresult).setMaxResults(5)
				.getResultList();
	}

	public int findtotal() {

		return entity.createQuery("FROM Khach", Khach.class).getResultList().size() + 4;
	}
	
	public List<Khach> findAll(){
		return entity.createQuery("FROM Khach", Khach.class).getResultList();
	}
	
	public void persist(Khach khach) {
		entity.persist(khach);
	}
	
	public void delete(Khach khach) {
		entity.remove(khach);
	}
	
	public Khach findById(String id) {
		return entity.find(Khach.class, id);
	}
	
	public void update(Khach Khach) {
		entity.merge(Khach);
	}
	
	public List<Object[]> findKhachDsd(int page){
		int fristresult = (page - 1) * 5;
	     @SuppressWarnings("unchecked")
		List<Object[]> listsudung = entity.createNativeQuery(" SELECT k.TEN_KH, k.makh, k.DIA_CHI, k.Email, k.PHONE, sdm.mamay, sdm.ngayBatDauSuDung, sdm.gioBatDauSuDung, sdm.thoigiansudung , sddv.madv, sddv.So_Luong\r\n" + 
	     		"FROM SuDungMay sdm left JOIN KhachHang k on k.makh = sdm.makh Left join SuDungDichVu sddv on k.makh = sddv.makh left JOIN May m on sdm.mamay = m.mamay WHERE m.TrangThai = 'Dang Su Dung'").setFirstResult(fristresult).setMaxResults(5).getResultList();
	     return listsudung;
	}
	public List<Object[]> findbill(String idkhach, String idmay, String ngay, String gio, String iddv){
	     String hql = " SELECT k.TEN_KH, k.makh, sdm.mamay, sdm.ngayBatDauSuDung, sdm.gioBatDauSuDung, sdm.thoigiansudung , sddv.madv, sddv.So_Luong, dv.DON_GIA\r\n" + 
	    		 "FROM SuDungMay sdm  LEFT JOIN KhachHang k on k.makh = sdm.makh LEFT JOIN SuDungDichVu sddv on k.makh = sddv.makh LEFT JOIN DichVu dv  on dv.MA_DV = sddv.madv "
	    		 + "WHERE  k.makh = :makh and sdm.mamay = :sdm.mamay and sdm.ngayBatDauSuDung = :ngay and sdm.gioBatDauSuDung = :gio ";
//	    		 + "OR sddv.madv = :sddv.madv or sddv.ngaysudung = :ngaydv ";
	     		@SuppressWarnings("unchecked")
				Query<Object[]> query  =  (Query<Object[]>) entity.createNativeQuery(hql);
	     		query.setParameter("makh", idkhach);
	     		query.setParameter("sdm.mamay", idmay);
	     		query.setParameter("ngay", ngay);
	     		query.setParameter("gio", gio);
//	     		query.setParameter("sddv.madv", iddv);
//	     		query.setParameter("ngaydv", ngay);
	     	List<Object[]> listbill = query.getResultList();
	     		
	     return listbill;
	}
	
	public List<Object[]> searchKhachDsd(String search){
		String hql = " SELECT k.TEN_KH, k.makh, k.DIA_CHI, k.Email, k.PHONE, sdm.mamay, sdm.ngayBatDauSuDung, sdm.gioBatDauSuDung, sdm.thoigiansudung , sddv.madv, sddv.So_Luong\r\n" +
	     		"FROM SuDungMay sdm left JOIN KhachHang k on k.makh = sdm.makh Left join SuDungDichVu sddv on k.makh = sddv.makh "
	     		+ " WHERE k.TEN_KH LIKE :k.TEN_KH OR k.makh LIKE :k.makh OR k.DIA_CHI LIKE :k.DIA_CHI OR k.Email LIKE :k.Email OR k.PHONE LIKE :k.PHONE OR sdm.mamay LIKE :sdm.mamay "
	     		+ " OR FORMAT(sdm.ngayBatDauSuDung,'yyyy-MM-dd') LIKE :sdm.ngayBatDauSuDung OR FORMAT(sdm.gioBatDauSuDung,'HH:mm') LIKE :sdm.gioBatDauSuDung OR sddv.madv LIKE :sddv.madv "
	     		+ " OR CAST(sddv.So_Luong as varchar(50)) LIKE :sddv.So_Luong OR CAST(sdm.thoigiansudung as varchar(50)) LIKE :sdm.thoigiansudung";

	     @SuppressWarnings("unchecked")
		Query<Object[]> query  =  (Query<Object[]>) entity.createNativeQuery(hql);
	     query.setParameter("k.TEN_KH","%"+ search+ "%");
	     query.setParameter("k.makh", "%"+ search+ "%");
	     query.setParameter("k.DIA_CHI", "%"+ search+ "%");
	     query.setParameter("k.Email", "%"+ search+ "%");
	     query.setParameter("k.PHONE", "%"+ search+ "%");
	     query.setParameter("sdm.mamay", "%"+ search+ "%");
	     query.setParameter("sdm.ngayBatDauSuDung", "%"+ search+ "%");
	     query.setParameter("sdm.gioBatDauSuDung", "%"+ search+ "%");
	     query.setParameter("sddv.madv", "%"+ search+ "%");
	     query.setParameter("sddv.So_Luong", "%"+ search+ "%");
	     query.setParameter("sdm.thoigiansudung", "%"+ search+ "%");
	     List<Object[]> listsudung  = query.setFirstResult(0).setMaxResults(5).getResultList();
	     return listsudung;
	}
	public int findtotaldanhsach() {

		return entity.createNativeQuery(" SELECT k.TEN_KH, k.makh, k.DIA_CHI, k.Email, k.PHONE, sdm.mamay, sdm.ngayBatDauSuDung, sdm.gioBatDauSuDung, sdm.thoigiansudung , sddv.madv, sddv.So_Luong\r\n" + 
	     		"FROM SuDungMay sdm left JOIN KhachHang k on k.makh = sdm.makh Left join SuDungDichVu sddv on k.makh = sddv.makh").getResultList().size() + 4;
	}
	public List<Khach> searchKhach(String search){
		String hql = "FROM Khach WHERE makh Like: makh or tenKH like: tenKH or diachi Like: diachi or phone Like:phone or email like: email ";
		 TypedQuery<Khach> query = entity.createQuery(hql, Khach.class);
		 query.setParameter("makh", "%"+search+"%");
		 query.setParameter("tenKH", "%" + search + "%");
		 query.setParameter("diachi", "%" + search + "%");
		 query.setParameter("phone", "%" + search + "%");
		 query.setParameter("email", "%" + search + "%");
		return query.setMaxResults(5).getResultList();
		}
	
}
