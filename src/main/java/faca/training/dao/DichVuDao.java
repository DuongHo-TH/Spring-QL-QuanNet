package faca.training.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import faca.training.bean.DichVu;

@Repository
public class DichVuDao {

	@PersistenceContext
	private EntityManager entity;

	public List<DichVu> findpage(int page) {
		int fristresult = (page - 1) * 5;
		return entity.createQuery("FROM DichVu", DichVu.class).setFirstResult(fristresult).setMaxResults(5)
				.getResultList();
	}

	public int findtotal() {

		return entity.createQuery("FROM DichVu", DichVu.class).getResultList().size() + 4;
	}
	public List<DichVu> findAll() {
		
		return entity.createQuery("FROM DichVu", DichVu.class).getResultList();
	}

	public void persist(DichVu DichVu) {
		entity.persist(DichVu);
	}

	public void delete(DichVu DichVu) {
		entity.remove(DichVu);
	}

	public DichVu findById(String id) {
		return entity.find(DichVu.class, id);
	}

	public void update(DichVu DichVu) {
		entity.merge(DichVu);
	}

	public List<DichVu> searchDichVu(String search) {
		String hql = "FROM DichVu WHERE madv Like: madv or tendv like: tendv or donvi Like: donvi";
		TypedQuery<DichVu> query = entity.createQuery(hql, DichVu.class);
		query.setParameter("madv", "%" + search + "%");
		query.setParameter("tendv", "%" + search + "%");
		query.setParameter("donvi", "%" + search + "%");
		return query.setMaxResults(5).getResultList();
	}
}
