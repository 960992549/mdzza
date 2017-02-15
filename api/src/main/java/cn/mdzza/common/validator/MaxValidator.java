package cn.mdzza.common.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by ydt on 2017/2/15.
 */
@Component
public class MaxValidator implements Validator {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean validate(Object value, String rule) {
		try {
			if(!RequiredValidator.validate(value)) {
				return true;
			}
			BigDecimal decimal = new BigDecimal(value.toString());
			BigDecimal limit = new BigDecimal(rule.substring(rule.indexOf("=") + 1));
			return decimal.compareTo(limit) <= 0;
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
}
