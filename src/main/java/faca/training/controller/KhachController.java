package faca.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import faca.training.bean.Khach;
import faca.training.service.KhachServices;
@Controller
public class KhachController {
	@Autowired
	private KhachServices service;

	@RequestMapping(value= {"/addkhach"})
	public String addKhach(Model model) {
		model.addAttribute("khach", new Khach());
		return "/addkhach";
	}
	@PostMapping("/savekhach")
	public String saveMay(Model model, @ModelAttribute("khach") @Valid Khach Khach, BindingResult reult) {
		if(reult.hasErrors()) {
			return "/addkhach";
		}
		service.persitst(Khach);
		return "redirect:/";
	}
	@GetMapping("/showkhach")
	public String showMay(Model model, @ModelAttribute("page") int page) {
		model.addAttribute("page", page);
		model.addAttribute("total", (service.findtotal()/5));
		model.addAttribute("khachlist", service.findpage(page) );
		return "/danhsachkhach";
	}
	@GetMapping("/deletekhach")
	public String delete(Model model, @RequestParam("khachId") String id) {
		service.delete(id);
		return "/Home";
	}
	
	@GetMapping("/editkhach")
	public String editmay(Model model, @RequestParam("khachId") String id) {
		Khach Khach = service.findById(id);
		model.addAttribute("khach", Khach);
		return"/updatekhach";
	}
	@PostMapping("/updatekhach")
	public String updatemay(Model model, @ModelAttribute("khach") @Valid Khach Khach, BindingResult reult) {
		if(reult.hasErrors()) {
			return "/updatekhach";
		}
		service.update(Khach);
		return "/Home";
		
	}
	@GetMapping("/searchkhach")
	public String searchKhach(Model model, @RequestParam("search") String search) {
		model.addAttribute("page", 1);
	    model.addAttribute("khachlist", service.searchKhach(search));
	    return "/danhsachkhach";
	}
}
