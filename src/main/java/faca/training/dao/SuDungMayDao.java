package faca.training.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import faca.training.bean.Khach;
import faca.training.bean.May;
import faca.training.bean.SuDungMay;
import faca.training.bean.SuDungMayId;

@Repository
public class SuDungMayDao {

	@PersistenceContext
	private EntityManager entity;
	
	public List<SuDungMay> findAll(){
		return entity.createQuery("FROM SuDungMay", SuDungMay.class).getResultList();
	}
	public List<SuDungMay> findpage(int page) {
		int fristresult = (page - 1) * 5;
		return entity.createQuery("FROM SuDungMay", SuDungMay.class).setFirstResult(fristresult).setMaxResults(5)
				.getResultList();
	}

	public int findtotal() {

		return entity.createQuery("FROM Khach", Khach.class).getResultList().size() +4;
	}
	
	public void persist(SuDungMay SuDungMay) {
		Khach khach = entity.find(Khach.class, SuDungMay.getId().getMakh());
		May may = entity.find(May.class, SuDungMay.getId().getMamay());
		may.setTrangthai("Dang Su Dung");
		SuDungMay.setMakh(khach);
		SuDungMay.setMamay(may);
		entity.merge(may);
		entity.persist(SuDungMay);
	}
	
	public void delete(SuDungMay SuDungMay) {
		entity.remove(SuDungMay);
	}
	
	public SuDungMay findById(SuDungMayId id) {
		return entity.find(SuDungMay.class, id);
	}
	
	public List<SuDungMay> search(String search){
		String hql = "FROM SuDungMay  WHERE id.makh Like: makh OR id.mamay LIKE: mamay OR  makh.tenKH LIKE :tenKH "
				+ "OR FORMAT(id.ngayBatDauSuDung,'yyyy-MM-dd') LIKE :date "
				+ "OR FORMAT(id.gioBatDauSuDung,'HH:mm') LIKE :time "
				+ "OR CAST(thoigiansudung as string) LIKE :thoigian "
				+ " OR CAST(mamay.vitri as string) LIKE :vitri";
		 TypedQuery<SuDungMay> query = entity.createQuery(hql, SuDungMay.class);
		 query.setParameter("makh", "%"+search+"%");
		 query.setParameter("mamay", "%" + search + "%");
		 query.setParameter("thoigian", "%" + search + "%");
		 query.setParameter("tenKH", "%" + search + "%");
		 query.setParameter("vitri", "%" + search + "%");
		 query.setParameter("date", "%" + search + "%");
		 query.setParameter("time", "%" + search + "%");
		return query.setMaxResults(5).getResultList();
		}
}
