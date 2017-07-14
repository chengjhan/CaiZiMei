package misc;

import java.beans.PropertyEditorSupport;

import com.czmbeauty.model.entity.CountryBean;

public class CountryBeanPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		CountryBean countryBean = new CountryBean();
		countryBean.setCo_id(Integer.parseInt(text));
		setValue(countryBean);
	}

}
