package com.example.application;

import java.util.Objects;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Exercise01 {

	public static void main(String[] args) {
		ScriptEngineManager sem = new ScriptEngineManager();
		var factories= sem.getEngineFactories();
		for (ScriptEngineFactory sef: factories)
		     System.err.print("%s".formatted(sef.getEngineName()));
		ScriptEngine jsEngine = sem.getEngineByName("JavaScript");
		Objects.requireNonNull(jsEngine);
		try {
			jsEngine.eval("load('nashorn:mozilla_compat.js');");
			jsEngine.eval("importPackage(javax.swing); var optionPane = JOptionPane.showMessageDialog(null, 'Hello, world! ');");
		} catch (ScriptException ex) {
			ex.printStackTrace();
		}

	}

}
