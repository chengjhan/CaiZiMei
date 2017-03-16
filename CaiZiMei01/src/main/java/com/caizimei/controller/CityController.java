package com.caizimei.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caizimei.model.CityBean;
import com.caizimei.model.service.CityService;
import com.caizimei.model.service.CountryService;
import com.google.gson.Gson;

@Controller
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;

	@RequestMapping(path = "/city/select.controller", method = RequestMethod.GET)
	public void selectProcess(Model model) {
		model.addAttribute("select", cityService.select());
	}

	@RequestMapping(path = "/city/insert.controller", method = RequestMethod.POST)
	public String insertProcess(@RequestParam(name = "co_name") String co_name,
			@RequestParam(name = "ci_name") String ci_name) {
		CityBean cityBean = new CityBean();
		cityBean.setCi_name(ci_name);
		cityBean.setCi_CountryBean(countryService.selectByCo_name(co_name));
		cityService.insert(cityBean);
		return "city.insert";
	}

	@RequestMapping(path = "/city/update.controller", method = RequestMethod.POST)
	public String updateProcess(@RequestParam(name = "ci_id") String ci_id,
			@RequestParam(name = "co_name") String co_name, @RequestParam(name = "ci_name") String ci_name) {
		CityBean cityBean = new CityBean();
		cityBean.setCi_id(Integer.parseInt(ci_id));
		cityBean.setCi_CountryBean(countryService.selectByCo_name(co_name));
		cityBean.setCi_name(ci_name);
		cityService.update(cityBean);
		return "city.insert";
	}

	@RequestMapping(path = "/city/delete.controller", method = RequestMethod.GET)
	public String deleteProcess(CityBean cityBean) {
		cityService.delete(cityBean.getCi_id());
		return "city.insert";
	}

	@RequestMapping(path = "/city/select.ajax", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String selectByCountryAjaxProcess(String co_name) {
		List<CityBean> result = cityService.selectByCi_co_name(co_name);
		List<CityBean> jsonList = new ArrayList<CityBean>();
		for (CityBean bean : result) {
			CityBean jsonBean = new CityBean();
			jsonBean.setCi_name(bean.getCi_name());
			jsonList.add(jsonBean);
		}
		String json = new Gson().toJson(jsonList);
		System.out.println("JSON = " + json);
		return json;
	}

}
