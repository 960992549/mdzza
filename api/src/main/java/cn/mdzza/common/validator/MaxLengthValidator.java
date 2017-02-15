package cn.mdzza.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by ydt on 2017/2/15.
 */
@Component
public class MaxLengthValidator implements Validator {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean validate(Object value, String rule) {
		try {
			if(!RequiredValidator.validate(value)) {
				return true;
			}
			int limit = Integer.parseInt(rule.substring(rule.indexOf("=") + 1));
			return value.toString().length() <= limit;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
}
