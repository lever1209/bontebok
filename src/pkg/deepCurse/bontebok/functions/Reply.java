package pkg.deepCurse.bontebok.functions;

import java.util.List;

import pkg.deepCurse.bontebok.core.BontebokFunction;

public class Reply implements BontebokFunction {

	@Override
	public void run(List<String> args) {
		
		for (String i : args) {
			System.out.print(i);
		}
		
	}

}
