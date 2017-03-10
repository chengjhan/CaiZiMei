package com.caizimei.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caizimei.model.CountryBean;
import com.caizimei.model.service.CountryService;

@Controller
public class CountryController {

	@Autowired
	private CountryService countryService;

	@RequestMapping(path = "/country/insert.controller", method = RequestMethod.POST)
	public String insertProcess(CountryBean countryBean) {
		countryService.insert(countryBean);
		return "country.insert";
	}

	@RequestMapping(path = "/country/update.controller", method = RequestMethod.POST)
	public String updateProcess(CountryBean countryBean) {
		countryService.update(countryBean);
		return "country.insert";
	}

	@RequestMapping(path = "/country/delete.controller", method = RequestMethod.GET)
	public String deleteProcess(CountryBean countryBean) {
		countryService.delete(countryBean.getCo_id());
		return "country.insert";
	}

}
