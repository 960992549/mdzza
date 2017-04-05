package cn.mdzza.dubbo;

/**
 * Created by ydt on 2017/3/13.
 */
public class TestServiceImpl implements TestService {
	@Override
	public String sayHello(String name) {
		return "Hello " + name + "!";
	}
}
