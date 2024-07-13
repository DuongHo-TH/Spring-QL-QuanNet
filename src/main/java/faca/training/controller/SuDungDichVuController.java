package faca.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import faca.training.bean.DichVu;
import faca.training.bean.Khach;
import faca.training.bean.SuDungDichVu;
import faca.training.bean.SuDungDichVuId;
import faca.training.service.DichVuServices;
import faca.training.service.KhachServices;
import faca.training.service.SuDungDichVuServices;

@Controller
public class SuDungDichVuController {
	@Autowired
	private SuDungDichVuServices service;
	@Autowired
	DichVuServices dvservice;
	@Autowired
	KhachServices khachservice;

	@GetMapping("/addsudungdichvu")
	public String addSudungmay(Model model) {
		model.addAttribute("dvlist", dvservice.findAll());
		model.addAttribute("khachlist", khachservice.findAll());
		model.addAttribute("sudungdichvu", new SuDungDichVu());
		return "/addsddv";
	}

	@PostMapping("/savesudungdichvu")
	public String saveSudungmay(Model model, @ModelAttribute("sudungdichvu") @Valid SuDungDichVu SuDungDichVu, BindingResult result) {
        if(result.hasErrors()) {
        	model.addAttribute("dvlist", dvservice.findAll());
    		model.addAttribute("khachlist", khachservice.findAll());
        	return "/addsddv";
        }
		service.persitst(SuDungDichVu);
		return "redirect:/showsudungdichvu?page=1";
	}
	@GetMapping("/showsudungdichvu")
	public String showsudungdichvu(Model model, @ModelAttribute("page") int page) {
		model.addAttribute("page", page);
		model.addAttribute("total", (service.findtotal() /5));
		model.addAttribute("listsudungdichvu", service.findpage(page));
		return "/danhsachsudungdichvu";
	}
	@GetMapping("/deletesudungdichvu")
	public String delete(SuDungDichVuId id ) {
		service.delete(id);
		return "redirect:/showsudungdichvu?page=1";
	}
	
	@GetMapping("/editsudungdichvu")
	public String edit(Model model,  SuDungDichVuId id) {
		model.addAttribute("dvlist", dvservice.findAll());
		model.addAttribute("khachlist",khachservice.findAll());
		SuDungDichVu dichvu = service.findById(id);
		model.addAttribute("sudungdichvu", dichvu);
		return "updatesudungdichvu";
	}
	@PostMapping("/updatesudungdichvu")
	public String update(Model model, @ModelAttribute("sudungdichvu") SuDungDichVu dichvu) {
		DichVu dv = dvservice.findById(dichvu.getId().getMadv());
		Khach khach = khachservice.findById(dichvu.getId().getMakh());
		dichvu.setMadv(dv);
		dichvu.setMakh(khach);
		service.update(dichvu);
		return "redirect:/showsudungdichvu?page=1";
	}
	@GetMapping("/searchsudungdichvu")
	public String search(Model model, @ModelAttribute("search") String search) {
		model.addAttribute("page", 1);
		model.addAttribute("listsudungdichvu", service.search(search));
		return "/danhsachsudungdichvu";
	}
	
}
