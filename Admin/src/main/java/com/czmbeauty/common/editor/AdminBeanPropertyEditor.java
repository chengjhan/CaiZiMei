package com.czmbeauty.common.editor;

import java.beans.PropertyEditorSupport;

import com.czmbeauty.model.entity.AdminBean;

public class AdminBeanPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			AdminBean adminBean = new AdminBean();
			adminBean.setAd_id(Integer.parseInt(text));
			setValue(adminBean);
		}
	}

}
