package com.czmbeauty.common.editor;

import java.beans.PropertyEditorSupport;

import com.czmbeauty.model.entity.AdminPathBean;

public class AdminPathBeanPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			AdminPathBean adminPathBean = new AdminPathBean();
			adminPathBean.setAp_id(Integer.parseInt(text));
			setValue(adminPathBean);
		}
	}

}
