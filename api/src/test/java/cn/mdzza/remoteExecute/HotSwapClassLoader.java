package cn.mdzza.remoteExecute;

/**
 * Created by ydt on 2017/3/7.
 */
public class HotSwapClassLoader extends ClassLoader {
	public HotSwapClassLoader() {
		super(HotSwapClassLoader.class.getClassLoader());
	}

	public Class loadByte(byte[] classByte) {
		return defineClass(null, classByte, 0, classByte.length);
	}
}
