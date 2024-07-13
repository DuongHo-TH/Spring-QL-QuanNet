package faca.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import faca.training.service.KhachServices;

@Controller
public class HomeController {
	@Autowired
	private KhachServices khachservice;

	@RequestMapping(value = { "/", "/trang-chu" })
	public String Index(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		List<Object[]> listkhach = khachservice.findKhachDsd(page);
		model.addAttribute("page", page);
		model.addAttribute("totaldanhsach", khachservice.findtotaldanhsach() / 5);
		model.addAttribute("listkhach", listkhach);
		return "/Home";
	}

	@GetMapping("/searchtrangchu")
	public String searchtrangchu(Model model, @ModelAttribute("search") String search) {
		int page =1;
		List<Object[]> listkhach = khachservice.searchKhachDsd(search);
		model.addAttribute("page", page);
		model.addAttribute("totaldanhsach", khachservice.findtotaldanhsach()/5);
		model.addAttribute("listkhach", listkhach);
		return "/Home";
	}

	@GetMapping("/bill")
   public String bill(Model model,@RequestParam("idkhach") String idkhach ,@RequestParam("idmay") String idmay,@RequestParam("ngay") String ngay,@RequestParam("gio") String gio,@RequestParam(value ="iddv" , defaultValue = "1") String iddv ) {
		model.addAttribute("listbill", khachservice.findbill(idkhach,idmay,ngay,gio,iddv));
	   return "/thanhtoan";
   }
	
}
