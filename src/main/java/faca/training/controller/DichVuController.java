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

import faca.training.bean.DichVu;
import faca.training.service.DichVuServices;
@Controller
public class DichVuController {
	@Autowired
	private DichVuServices service;
	
	@RequestMapping(value= {"/adddichvu"})
	public String addDichVu(Model model) {
		if(!model.containsAttribute("dichvu")) {
			model.addAttribute("dichvu", new DichVu());
		}
		return "/adddichvu";
	}
	@PostMapping("/savedichvu")
	public String saveDichVu(Model model, @ModelAttribute("dichvu") @Valid DichVu DichVu, BindingResult result) {
		if(result.hasErrors()) {
			return "/adddichvu";
		}
		service.persitst(DichVu);
		return "redirect:/";
	}
	@GetMapping("/showdichvu")
	public String showMay(Model model, @ModelAttribute("page") int page) {
		model.addAttribute("page", page);
		model.addAttribute("total", (service.findtotal()/5));
		model.addAttribute("dichvulist", service.findpage(page));
		return "/danhsachdichvu";
	}
	@GetMapping("/deletedichvu")
	public String delete(Model model, @RequestParam("dichvuId") String id) {
		service.delete(id);
		return "/Home";
	}
	
	@GetMapping("/editdichvu")
	public String editmay(Model model, @RequestParam("dichvuId") String id) {
		DichVu DichVu = service.findById(id);
		model.addAttribute("dichvu", DichVu);
		return"/updatedichvu";
	}
	@PostMapping("/updatedichvu")
	public String updatemay(Model model, @ModelAttribute("dichvu") DichVu DichVu) {
		service.update(DichVu);
		return "/Home";
		
	}
	@GetMapping("/searchdichvu")
	public String searchDichvu(Model model, @RequestParam("search") String search) {
		model.addAttribute("page", 1);
	    model.addAttribute("dichvulist", service.searchDichvu(search));
	    return "/danhsachdichvu";
	}
}
