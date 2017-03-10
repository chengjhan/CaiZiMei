package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.caizimei.model.ClinicBean;
import com.caizimei.model.service.CityService;
import com.caizimei.model.service.ClinicService;

@Controller
public class ClinicCintroller {

	@Autowired
	private ClinicService clinicService;
	@Autowired
	private CityService cityService;

	@RequestMapping(path = "/clinic/select.controller", method = RequestMethod.GET)
	public void selectProcess(Model model) {
		model.addAttribute("select", clinicService.select());
	}

	@RequestMapping(path = "/clinic/search.controller", method = RequestMethod.GET)
	public String selectByConditionsProcess(ClinicBean clinicBean, Model model) {
		model.addAttribute("selectByConditions",
				clinicService.selectByConditions(clinicBean.getC_name().trim(), clinicBean.getC_telephone().trim()));
		return "clinic.search";
	}

	@RequestMapping(path = "/clinic/insert.controller", method = RequestMethod.POST)
	public String insertProcess(ClinicBean clinicBean,
			@RequestParam(name = "c_telephone_front") String c_telephone_front,
			@RequestParam(name = "c_telephone_back") String c_telephone_back,
			@RequestParam(name = "ci_name") String ci_name) {
		clinicBean.setC_telephone(c_telephone_front + "-" + c_telephone_back);
		clinicBean.setC_CityBean(cityService.selectByCi_name(ci_name));
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(clinicBean.getC_address());
		} catch (Exception e) {
			e.printStackTrace();
		}
		clinicBean.setC_latitude(LatLng[0]);
		clinicBean.setC_longitude(LatLng[1]);
		clinicService.insert(clinicBean);
		return "clinic.insert";
	}

	@RequestMapping(path = "/clinic/update.controller", method = RequestMethod.POST)
	public String updateProcess(@RequestParam(name = "c_id") String c_id, @RequestParam(name = "c_name") String c_name,
			@RequestParam(name = "c_eng_name") String c_eng_name,
			@RequestParam(name = "c_telephone") String c_telephone, @RequestParam(name = "ci_name") String ci_name,
			@RequestParam(name = "c_address") String c_address, @RequestParam(name = "c_url") String c_url) {
		ClinicBean clinicBean = new ClinicBean();
		clinicBean.setC_id(Integer.parseInt(c_id));
		clinicBean.setC_name(c_name);
		clinicBean.setC_eng_name(c_eng_name);
		clinicBean.setC_telephone(c_telephone);
		clinicBean.setC_CityBean(cityService.selectByCi_name(ci_name));
		clinicBean.setC_address(c_address);
		Double[] LatLng = new Double[2];
		try {
			LatLng = clinicService.addressToLatLng(c_address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clinicBean.setC_latitude(LatLng[0]);
		clinicBean.setC_longitude(LatLng[1]);
		clinicBean.setC_url(c_url);
		clinicService.update(clinicBean);
		return "clinic.insert";
	}

	@RequestMapping(path = "/clinic/delete.controller", method = RequestMethod.GET)
	public String deleteProcess(@RequestParam(name = "c_id") String c_id) {
		clinicService.delete(Integer.parseInt(c_id));
		return "clinic.insert";
	}

}
