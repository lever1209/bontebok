package pkg.deepCurse.bontebok.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import pkg.deepCurse.bontebok.functions.Reply;

public class BontebokInterpreter {

	private static HashMap<String, BontebokFunction> functionMap = new HashMap<String, BontebokFunction>();

	static {

		functionMap.put("reply", new Reply());

	}

	public static void InterpretString(String input, BontebokSettings settings) {

		List<BontebokInstruction> instructions = new ArrayList<BontebokInstruction>();
		char[] chars = input.toCharArray();
//		HashMap<String, BontebokInstruction> instructionMap = new HashMap<String, BontebokInstruction>();
//		instructionMap.put("reply", new Reply());
		// List<Character> charBuffer = new ArrayList<Character>();
		StringBuilder word = new StringBuilder();
		StringBuilder arg = new StringBuilder();
//		boolean isActive = false;
		List<String> args = new ArrayList<String>();
		boolean argumentMode = false;
		boolean stringMode = false;
		for (int i = 0; i < input.length(); i++) {
//			isActive = true;
			// if (BontebokInstruction.instructionChars.contains(chars[i])) { // on char
			// find acceptable word, if fail,
			// throw InterpretationException, check line
			// on ; or new line
			// shit like brackets and "."
			// charBuffer.clear();
//			System.out.println(chars[i]);

			switch (chars[i]) {
//			case ';':
//				functionMap.get(word.toString()).run(args);
//				break;
			case '(':
				if (!stringMode) {
					argumentMode = true;
				} else {arg.append(chars[i]);}
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
				} else {arg.append(chars[i]);}
				break;
			case ' ':
				if (!stringMode) {
					args.add(arg.toString());
					arg.delete(0, arg.length());
				} else {arg.append(chars[i]);}
				break;
			case ',':
				if (!stringMode) {
					args.add(arg.toString());
					arg.delete(0, arg.length());
				} else {arg.append(chars[i]);}
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

		functionMap.get(word.toString()).run(args);

//		System.out.println(word);

//		if (isActive) {
//			throw new IllegalArgumentException("isActive");
//		}

	}

}
