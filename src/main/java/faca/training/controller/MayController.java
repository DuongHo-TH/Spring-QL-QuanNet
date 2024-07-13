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

import faca.training.bean.May;
import faca.training.service.MayServices;

@Controller
public class MayController {
	@Autowired
	private MayServices service;
	@RequestMapping(value= {"/addmay"})
	public String addMay(Model model) {
		model.addAttribute("may", new May());
		return "/addmay";
	}
	@PostMapping("/savemay")
	public String saveMay(Model model, @ModelAttribute("may") @Valid May may, BindingResult result) {
		if(result.hasErrors()) {
			return "/addmay";
		}
		service.persitst(may);
		return "redirect:/";
	}
	@GetMapping("/showmay")
	public String showMay(Model model, @ModelAttribute("page") int page ,@ModelAttribute("pagemay") int pagemay) {
	    model.addAttribute("total", service.findtotal()/5);
		model.addAttribute("page", page);
		model.addAttribute("pagemay", pagemay);
		model.addAttribute("maylist", service.findAll(page));
		return "/danhsachmay";
	}
	@GetMapping("/deletemay")
	public String delete(Model model, @RequestParam("mayId") String id) {
		service.delete(id);
		return "/Home";
	}
	
	@GetMapping("/editmay")
	public String editmay(Model model, @RequestParam("mayId") String id) {
		May may = service.findById(id);
		model.addAttribute("may", may);
		return"/updatemay";
	}
	@PostMapping("/updatemay")
	public String updatemay(Model model, @ModelAttribute("may") @Valid May may, BindingResult result) {
		if(result.hasErrors()) {
			return "/updatemay";
		}
		service.update(may);
		return "redirect:/";
	}
	
	@GetMapping("/showmaysudung")
	public String showMaysudung(Model model, @ModelAttribute("page") int page ,@ModelAttribute("pagemay") int pagemay) {
	    model.addAttribute("total", (service.findtotal()/5));
		model.addAttribute("page", page);
		model.addAttribute("pagemay", pagemay);
		model.addAttribute("maylist", service.findMayDsd(page));
		return "/danhsachmay";
	}
	@GetMapping("/showmaysansang")
	public String showMaysansang(Model model, @ModelAttribute("page") int page ,@ModelAttribute("pagemay") int pagemay) {
	    model.addAttribute("total", (service.findtotal() /5));
		model.addAttribute("page", page);
		model.addAttribute("pagemay", pagemay);
		model.addAttribute("maylist", service.findMayss(page));
		return "/danhsachmay";
	}
	@GetMapping("/searchmay")
	public String searchMay(Model model, @RequestParam("search") String search) {
		model.addAttribute("page", 1);
	    model.addAttribute("maylist", service.searchMay(search));
	    return "/danhsachmay";
	}
	
	@GetMapping("/thanhtoanmay")
	public String thanhtoanmay(Model model, @RequestParam("idmay") String idmay) {
		May may = service.findById(idmay);
		may.setTrangthai("San Sang");
		service.update(may);
		return "redirect:/";
	}
	
}
