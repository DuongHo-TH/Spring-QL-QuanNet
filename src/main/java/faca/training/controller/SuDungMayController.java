package faca.training.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import faca.training.bean.SuDungMay;
import faca.training.service.KhachServices;
import faca.training.service.MayServices;
import faca.training.service.SuDungMayServices;

@Controller
public class SuDungMayController {

	@Autowired
	private SuDungMayServices service;
	@Autowired
	MayServices mayservice;
	@Autowired
	KhachServices khachservice;
	@GetMapping("/addsudungmay")
	public String addSudungmay(Model model) {
		model.addAttribute("maylist", mayservice.findMaydss());
		model.addAttribute("khachlist", khachservice.findAll());
		model.addAttribute("sudungmay", new SuDungMay());

		return "/addsdmay";
	}
	@PostMapping("/savesudungmay")
	public String saveSudungmay(Model model, @ModelAttribute("sudungmay") @Valid SuDungMay SuDungMay, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("maylist", mayservice.findMaydss());
			model.addAttribute("khachlist", khachservice.findAll());
			return "/addsdmay";
		}
		service.persitst(SuDungMay);
		return "redirect:showsudungmay?page=1";
	}
	
	@GetMapping("/showsudungmay")
	public String showSudungmay(Model model, @ModelAttribute("page") int page) {
		model.addAttribute("page", page);
		model.addAttribute("total", (service.findtotal() /5));
		model.addAttribute("listsudungmay", service.findpage(page));
		return "/danhsachsudungmay";
	}
	@GetMapping("/searchsudungmay")
	public String search(Model model, @ModelAttribute("search") String search) {
		model.addAttribute("page", 1);
		model.addAttribute("listsudungmay", service.search(search));
		return "/danhsachsudungmay";
	}
}
