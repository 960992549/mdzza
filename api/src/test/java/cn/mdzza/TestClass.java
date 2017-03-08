package cn.mdzza;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ydt on 2017/3/7.
 */
public class TestClass {
	interface IHello {
		public void sayHello();
	}

	static class Hello implements IHello {
		public void sayHello() {
			System.out.println("say hello");
		}
	}

	static class HelloProxy implements InvocationHandler {
		Object original;
		public Object bind(Object original) {
			this.original = original;
			return Proxy.newProxyInstance(original.getClass().getClassLoader(),
					original.getClass().getInterfaces(), this);
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("proxy");
			return method.invoke(original, args);
		}
	}

	public static void main(String[] args) throws Exception {
		Class clazz = TestClass.class;


		IHello hello = (IHello)new HelloProxy().bind(new Hello());
		hello.sayHello();
	}
}
