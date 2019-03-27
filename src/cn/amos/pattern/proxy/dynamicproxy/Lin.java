package cn.amos.pattern.proxy.dynamicproxy;

public class Lin implements Player {
	@Override
	public void sign() {
		System.out.println("lin sign");
	}

	@Override
	public Lin play(String str, int i,String s1) {
		System.out.println("playing...");
		return null;
	}
}
