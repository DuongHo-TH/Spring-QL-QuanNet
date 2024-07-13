package faca.training.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import faca.training.bean.Khach;
import faca.training.dao.KhachDao;

@Service
@Transactional
public class KhachServices {
	@Autowired
	private KhachDao repository;

	public List<Khach> findAll() {
		return repository.findAll();
	}
	public List<Khach> findpage(int page) {
		return repository.findpage(page);
	}
	
	public int findtotal() {
		return repository.findtotal();
	}

	public void persitst(Khach Khach) {
		repository.persist(Khach);
	}

	public void delete(String id) {

		Khach Khach = findById(id);
		repository.delete(Khach);
	}

	public Khach findById(String id) {
		return repository.findById(id);
	}
	
	public void update(Khach Khach) {
		repository.update(Khach);
	}
	
	public List<Object[]> findKhachDsd(int page){
		return repository.findKhachDsd(page);
	}
	public int findtotaldanhsach() {
		return repository.findtotaldanhsach();
	}
	
	public List<Khach> searchKhach(String search){
		return repository.searchKhach(search);
	}
	public List<Object[]> searchKhachDsd(String search){
		return repository.searchKhachDsd(search);
	}
	public List<Object[]> findbill(String idkhach, String idmay, String ngay, String gio, String iddv){
		List<Object[]> listbill = repository.findbill(idkhach,idmay,ngay,gio,iddv);
//		for (Object[] objects : listbill) {
//			Double tienmay = (Double)objects[5] * 5000;
//		    int soluong = (Integer)objects[7];
//			Double Gia = (Double)objects[8];
//			Double tiendichvu = soluong * Gia;
//			Double tongtien = tienmay + tiendichvu;
//			
//		}
		return listbill;
	}
}
