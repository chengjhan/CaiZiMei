package misc;

import java.text.NumberFormat;

import org.springframework.beans.propertyeditors.CustomNumberEditor;

public class PrimitiveNumberEditor extends CustomNumberEditor {

	private final boolean allowEmpty;

	public PrimitiveNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty)
			throws IllegalArgumentException {
		super(numberClass, numberFormat, allowEmpty);
		this.allowEmpty = allowEmpty;
	}

	public PrimitiveNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty)
			throws IllegalArgumentException {
		super(numberClass, null, allowEmpty);
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.trim().length() != 0) {
			super.setAsText(text);
			return;
		}
		if (allowEmpty) {
			setValue(0);
		} else {
			throw new IllegalArgumentException("text can not be empty");
		}
	}

}
