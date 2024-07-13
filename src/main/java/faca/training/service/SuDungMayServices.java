package faca.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.bean.SuDungMay;
import faca.training.bean.SuDungMayId;
import faca.training.dao.SuDungMayDao;

@Service
@Transactional
public class SuDungMayServices {

	@Autowired
	private SuDungMayDao repository;
	
	public List<SuDungMay> findpage(int page) {
		return repository.findpage(page);
	}
	
	public int findtotal() {
		return repository.findtotal();
	}

	
	public List<SuDungMay> findAll(){
		return repository.findAll();
	}
	
	public void persitst(SuDungMay SuDungMay) {
		repository.persist(SuDungMay);
	}
	
	public void delete(SuDungMayId id) {
	 
		SuDungMay sudungmay = findById(id);
		repository.delete(sudungmay);
	}
	public SuDungMay findById(SuDungMayId id) {
		return repository.findById(id);
	}
	
	public List<SuDungMay> search(String search){
		return repository.search(search);
	}
}
