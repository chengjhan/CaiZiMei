package com.czmbeauty.common.editor;

import java.beans.PropertyEditorSupport;

import com.czmbeauty.model.entity.CityBean;

public class CityBeanPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			CityBean cityBean = new CityBean();
			cityBean.setCi_id(Integer.parseInt(text));
			setValue(cityBean);
		}
	}

}
