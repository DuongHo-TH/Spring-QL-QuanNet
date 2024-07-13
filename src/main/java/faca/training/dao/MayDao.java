package faca.training.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import faca.training.bean.May;

@Repository
public class MayDao {

	@PersistenceContext
	private EntityManager entity;
	public int findtotalMay(){
		return entity.createQuery("FROM May ", May.class).getResultList().size() + 4 ;
		}

	public List<May> findAll(int page) {
		int firstresult = (page - 1) * 5;
		return entity.createQuery("FROM May", May.class).setFirstResult(firstresult).setMaxResults(5).getResultList();
	}

	public void persist(May May) {
		entity.persist(May);
	}

	public void delete(May May) {
		entity.remove(May);
	}

	public May findById(String id) {
		return entity.find(May.class, id);
	}
	
	public void update(May may) {
		entity.merge(may);
	}
	public List<May> findMayss(int page){
		int firstresult = (page - 1) * 5;
	  return entity.createQuery("FROM May WHERE trangthai = 'San Sang'", May.class).setFirstResult(firstresult).setMaxResults(5).getResultList();
	}
	public List<May> findMaydss(){
	  return entity.createQuery("FROM May WHERE trangthai = 'San Sang'", May.class).getResultList();
	}
	
	public List<May> findMayDsd(int page){
		int firstresult = (page - 1) * 5;
		  return entity.createQuery("FROM May WHERE trangthai = 'Dang Su Dung'", May.class).setFirstResult(firstresult).setMaxResults(5).getResultList();
		}
	
	public List<May> searchMay(String search){
		String hql = "FROM May WHERE mamay Like : mamay or trangthai like : trangthai";
		 TypedQuery<May> query = entity.createQuery(hql, May.class);
		 query.setParameter("mamay", "%"+search+"%");
		 query.setParameter("trangthai", "%" + search + "%");
		return query.setMaxResults(5).getResultList();
		}
}
