package cn.mdzza.remoteExecute;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by ydt on 2017/3/7.
 */
public class Test {
	public static void main(String[] args) throws Exception {
		InputStream is = new FileInputStream("d:/TestClass.class");
		byte[] b = new byte[is.available()];
		is.read(b);
		is.close();
		System.out.println(JavaClassExecuter.execute(b));
	}
}
