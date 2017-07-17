package misc;

import java.beans.PropertyEditorSupport;

import com.czmbeauty.model.entity.StateBean;

public class StateBeanPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) {
		if (!"0".equals(text)) {
			StateBean stateBean = new StateBean();
			stateBean.setSt_id(Integer.parseInt(text));
			setValue(stateBean);
		}
	}

}
