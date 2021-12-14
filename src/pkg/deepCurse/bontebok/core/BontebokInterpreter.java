package pkg.deepCurse.bontebok.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BontebokInterpreter {

	public volatile static HashMap<String, BontebokFunctionInterface> functionMap = new HashMap<String, BontebokFunctionInterface>();

	public static String InterpretString(String input, BontebokSettings settings) {

		char[] chars = input.toCharArray();
		StringBuilder word = new StringBuilder();
		StringBuilder arg = new StringBuilder();
		List<String> args = new ArrayList<String>();
		boolean argumentMode = false;
		boolean stringMode = false;
		for (int i = 0; i < input.length(); i++) {

			switch (chars[i]) {
			case ';':
				if (!stringMode) {
				} else {
					arg.append(chars[i]);
				}
				break;
			case '(':
				if (!stringMode) {
					argumentMode = true;
				} else {
					arg.append(chars[i]);
				}
				break;
			case '"':
				if (stringMode) {
					stringMode = false;
				} else
					stringMode = true;
				break;
			case ')':
				if (!stringMode) {
					args.add(arg.toString());
					arg.delete(0, arg.length());
					argumentMode = false;
				} else {
					arg.append(chars[i]);
				}
				break;
			case ' ':
				if (!stringMode) {
					args.add(arg.toString());
					arg.delete(0, arg.length());
				} else {
					arg.append(chars[i]);
				}
				break;
			case ',':
				if (!stringMode) {
					args.add(arg.toString());
					arg.delete(0, arg.length());
				} else {
					arg.append(chars[i]);
				}
				break;
			default:
				if (!argumentMode) {
					word.append(chars[i]);
				} else {
					arg.append(chars[i]);
				}
				break;

			}
		}

		if (functionMap.get(word.toString()) == null) {
			return "1Function: \"" + word.toString() + "\" does not exist. . .";
		}

		if (functionMap.get(word.toString()).getRequiredArgs() >= 0) {
			if (functionMap.get(word.toString()).getRequiredArgs() != args.size()) {
				return "1Function: " + word.toString() + " requires exactly "
						+ functionMap.get(word.toString()).getRequiredArgs() + " arguments. . .";
			}
		}

		try {
			functionMap.get(word.toString()).run(args);
		} catch (Exception e) {
			return "1" + e;
		}

		return "0";
	}
}
