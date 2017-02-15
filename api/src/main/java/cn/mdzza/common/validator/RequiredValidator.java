package cn.mdzza.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ydt on 2017/2/15.
 */
@Component
public class RequiredValidator implements Validator {
	private static Logger logger = LoggerFactory.getLogger(RequiredValidator.class);

	@Override
	public boolean validate(Object value, String rule) {
		return validate(value);
	}

	public static boolean validate(Object value) {
		try {
			return value != null && value.toString().length() > 0;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
}
