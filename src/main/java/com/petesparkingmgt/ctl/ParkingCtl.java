package com.petesparkingmgt.ctl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petesparkingmgt.dao.ParkingDAO;
import com.petesparkingmgt.exception.RecordNotFoundException;
import com.petesparkingmgt.form.ParkingForm;
import com.petesparkingmgt.service.ParkingService;
import com.petesparkingmgt.dto.ParkingDTO;
import com.petesparkingmgt.dto.SlotDTO;
import com.petesparkingmgt.dao.SlotDAO;


@Controller
public class ParkingCtl {
	
	@Autowired
	public ParkingService service;
	
	@Autowired
	public SlotDAO slotDao;
	
	
	@GetMapping("/parking")
	public String parking(@ModelAttribute("form")ParkingForm form, Model model) {
		return "parking";
	}
	

	@GetMapping("/parkinglist")
	public String list(@ModelAttribute("form")ParkingForm form, Model model){
	List<ParkingDTO> list = service.list();
	model.addAttribute("list", list);
	return "parkinglist";
		
	}
	

	
	long parkingId = 0;
	@GetMapping("/viewparkingSlot")	
	public String viewSlot(@ModelAttribute("form")ParkingForm form, Model model, @RequestParam("id") long id ) throws Exception{
		parkingId = id;
		List<SlotDTO> slots = slotDao.findByParkingId(id);
        model.addAttribute("list", slots);
		return "viewslots";
	}
	

	
	
	
	
}
