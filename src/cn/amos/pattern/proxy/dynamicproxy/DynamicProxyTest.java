package cn.amos.pattern.proxy.dynamicproxy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

import sun.misc.ProxyGenerator;

public class DynamicProxyTest {

	public static void main(String[] args) throws IOException {
		PlayerProxy proxy = new PlayerProxy();

		Player lin = (Player) proxy.getInstance(new Lin());
		System.out.println(lin.getClass());
		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[] {Player.class});
		FileOutputStream fos = new FileOutputStream("F:\\Desktop\\$Proxy0.class");
		fos.write(bytes);
		fos.close();
		lin.sign();


		Lin l = new Lin();
		Class<? extends Lin> clazz = l.getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
//		for(Class c:interfaces){
//
//		}
		Class<?> iclazz = interfaces[0];
		Method[] methods = iclazz.getMethods();
//		System.out.println(Arrays.asList(methods));
		for(Method m:methods){
/*
			Parameter[] parameters = m.getParameters();
			for(Parameter p:parameters){
				System.out.println();
			}
*/
			Class<?>[] parameterTypes = m.getParameterTypes();
			for(Class type:parameterTypes){
				System.out.println(type.getName());

			}

		}
	}

}
