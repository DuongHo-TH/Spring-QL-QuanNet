package faca.training.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import faca.training.bean.DichVu;
import faca.training.bean.Khach;
import faca.training.bean.SuDungDichVu;
import faca.training.bean.SuDungDichVuId;

@Repository
public class SuDungDichVuDao {

	@PersistenceContext
	private EntityManager entity;
	
	public List<SuDungDichVu> findpage(int page) {
		int fristresult = (page - 1) * 5;
		return entity.createQuery("FROM SuDungDichVu", SuDungDichVu.class).setFirstResult(fristresult).setMaxResults(5)
				.getResultList();
	}

	public int findtotal() {

		return entity.createQuery("FROM Khach", Khach.class).getResultList().size() + 4;
	}
	
	public List<SuDungDichVu> findAll(){
		return entity.createQuery("FROM SuDungDichVu", SuDungDichVu.class).getResultList();
	}
	
	public void persist(SuDungDichVu DichVu) {
		Khach khach = entity.find(Khach.class, DichVu.getId().getMakh());
		DichVu dv = entity.find(DichVu.class, DichVu.getId().getMadv());
		DichVu.setMakh(khach);
		DichVu.setMadv(dv);
		entity.persist(DichVu);
	}
	
	public void delete(SuDungDichVuId id) {
		SuDungDichVu dv = entity.find(SuDungDichVu.class, id);
		entity.remove(dv);
	}
	
	public SuDungDichVu findById(SuDungDichVuId id) {
		return entity.find(SuDungDichVu.class, id);
	}
	public void update(SuDungDichVu dv) {
		entity.merge(dv);
	}
	public List<SuDungDichVu> search(String search){
		String hql = "FROM SuDungDichVu  WHERE id.makh Like: makh or id.madv LIKE: madv OR  makh.tenKH LIKE :tenKH "
				+ "OR FORMAT(id.ngaysudung,'yyyy-MM-dd') LIKE :date "
				+ "OR FORMAT(id.giosudung,'HH:mm') LIKE :time ";
		 TypedQuery<SuDungDichVu> query = entity.createQuery(hql, SuDungDichVu.class);
		 query.setParameter("makh", "%"+search+"%");
		 query.setParameter("madv", "%" + search + "%");
		 query.setParameter("tenKH", "%" + search + "%");
		 query.setParameter("date", "%" + search + "%");
		 query.setParameter("time", "%" + search + "%");
		return query.setMaxResults(5).getResultList();
		}
}
