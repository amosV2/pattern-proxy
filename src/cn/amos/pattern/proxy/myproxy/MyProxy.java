package cn.amos.pattern.proxy.myproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyProxy {

	private static final String ln = "\r\n";

	public static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
			throws IllegalArgumentException {


		return null;
	}

	private String generateSrc(Class<?>[] interfaces){
		StringBuffer sb = new StringBuffer();
		sb.append("package cn.amos.pattern.proxy.myproxy;"+ln);
		sb.append("import java.lang.reflect.*;"+ln);
		sb.append("public class $Proxy0 implements ");
		for(int i=0;i<interfaces.length;i++){
			Class<?> c = interfaces[i];
			sb.append(c.getName());
			if (i < interfaces.length - 1) {
				sb.append(",");
			}else {
				sb.append("{");
			}
		}

			sb.append("MyInvocationHandler h;" + ln);
			sb.append("public $Proxy0(MyInvocationHandler h) { "+ln);
				sb.append("this.h = h;"+ln);
			sb.append("}"+ln);

		for(int i=0;i<interfaces.length;i++){
			Class<?> c = interfaces[i];
			Method[] methods = c.getMethods();
			for(Method m:methods){
//				m.getClass().getMethod("sing",c);
				StringBuffer paramList = new StringBuffer();//参数表
				StringBuffer paramNames = new StringBuffer();//参数名
				StringBuffer paramTypes = new StringBuffer();//参数类型
				Class<?> returnType = m.getReturnType();
				Class<?>[] parameterTypes = m.getParameterTypes();
				for(int j=0;j<parameterTypes.length;j++){
					Class<?> t = parameterTypes[j];
					String paramType = t.getName();
					paramTypes.append(paramType + ".class");
					String paramName = "arg" + j;
					paramNames.append(paramName);
					paramList.append(paramType + " " + paramName);
					if (j > 0 && j < parameterTypes.length - 1) {
						paramList.append(",");
						paramNames.append(",");
						paramTypes.append(",");
					}
				}

				sb.append("public " + returnType.getName() + m.getName() + "(" + paramList.toString() + ") {" + ln);
					sb.append("try{" + ln);
						sb.append("Method method = " + c.getName() + ".class.getMethod(" + m.getName() + ",new Class[]{"+ paramTypes.toString() +"}" + ");" + ln);
						sb.append("return"+"this.h.invoke(this,method,new Object[]{"+ paramNames.toString() +"}" +");" + ln);
					sb.append("} catch (RuntimeException | Error var2) {" + ln);
							sb.append("throw var2;" + ln);
						sb.append("} catch (Throwable var3) {" + ln);
							sb.append(" throw new UndeclaredThrowableException(var3);"+ln);
						sb.append("}"+ln);

					sb.append("}"+ln);
				sb.append("}"+ln);
			}
		}


		sb.append("}");


		return null;
	}

	private boolean hasReturnValue(Class<?> returnType){
		return returnType!=void.class;
	}

}
