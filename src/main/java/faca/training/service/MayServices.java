package faca.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.bean.May;
import faca.training.dao.MayDao;

@Service
@Transactional
public class MayServices {
	@Autowired
	private MayDao repository;

	public int findtotal() {
		return repository.findtotalMay();
	}
	public List<May> findAll(int page) {
		return repository.findAll(page);
	}

	public void persitst(May May) {
		repository.persist(May);
	}

	public void delete(String id) {

		May May = findById(id);
		repository.delete(May);
	}

	public May findById(String id) {
		return repository.findById(id);
	}
	
	public void update(May may) {
		repository.update(may);
	}
	
	public List<May> findMayss(int page) {
		return repository.findMayss(page);
	}
	public List<May> findMaydss() {
		return repository.findMaydss();
	}
	
	public List<May> findMayDsd(int page) {
		return repository.findMayDsd(page);
	}
	public List<May> searchMay(String search){
		return repository.searchMay(search);
		
	}
	
}