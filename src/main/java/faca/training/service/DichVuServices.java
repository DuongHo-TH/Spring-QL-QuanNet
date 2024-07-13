package faca.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.bean.DichVu;
import faca.training.dao.DichVuDao;

@Service
@Transactional
public class DichVuServices {
	@Autowired
	private DichVuDao repository;

	public List<DichVu> findAll() {
		return repository.findAll();
	}
	public List<DichVu> findpage(int page) {
		return repository.findpage(page);
	}
	
	public int findtotal() {
		return repository.findtotal();
	}

	public void persitst(DichVu DichVu) {
		repository.persist(DichVu);
	}

	public void delete(String id) {

		DichVu DichVu = findById(id);
		repository.delete(DichVu);
	}

	public DichVu findById(String id) {
		return repository.findById(id);
	}
	
	public void update(DichVu DichVu) {
		repository.update(DichVu);
	}
	
	public List<DichVu> searchDichvu(String search) {
		return repository.searchDichVu(search);
	}
}
