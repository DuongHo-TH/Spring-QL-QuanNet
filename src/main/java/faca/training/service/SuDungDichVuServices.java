package faca.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.bean.SuDungDichVu;
import faca.training.bean.SuDungDichVuId;
import faca.training.dao.SuDungDichVuDao;

@Service
@Transactional
public class SuDungDichVuServices {

	@Autowired
	private SuDungDichVuDao repository;
	
	public List<SuDungDichVu> findpage(int page) {
		return repository.findpage(page);
	}
	
	public int findtotal() {
		return repository.findtotal();
	}


	public List<SuDungDichVu> findAll() {
		return repository.findAll();
	}

	public void persitst(SuDungDichVu SuDungDichVu) {
		repository.persist(SuDungDichVu);
	}

	public void delete(SuDungDichVuId id) {
               
		repository.delete(id);
	}

	public SuDungDichVu findById(SuDungDichVuId id) {
		return repository.findById(id);
	}
	public void update(SuDungDichVu dichvu) {
		repository.update(dichvu);
	}
	public List<SuDungDichVu> search(String search){
		return repository.search(search);
	}
}
