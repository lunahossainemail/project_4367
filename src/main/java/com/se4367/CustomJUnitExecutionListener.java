package com.se4367;

import java.io.*;
import java.util.Arrays;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntSet;

/**
 * Register an instance of this class with RunNotifier to be notified of events
 * that occur during a test run. All of the methods in this class are abstract
 * and have no implementation; override one or more methods to receive events.
 * 
 * 
 * @author: Tania
 */

public class CustomJUnitExecutionListener extends RunListener {

	/**
	 * Called before any tests have been run.
	 * 
	 */
	public void testRunStarted(Description description) throws Exception {
		if (null == CodeCoverageCollect.testCases) {
			CodeCoverageCollect.testCases = new Object2ObjectOpenHashMap<String, Object2ObjectOpenHashMap<String, IntSet>>();
		}
		System.out.println();
		System.out.println();
		System.out.println("Testing Started!");
	}

	/**
	 * Called when an atomic test is about to be started.
	 * 
	 */
	public void testStarted(Description description) {
		// Note: Java is pass by value, so this works
		CodeCoverageCollect.Name_testCase = "[TEST] " + description.getClassName() + ":" + description.getMethodName();
		CodeCoverageCollect.testcase = new Object2ObjectOpenHashMap<String, IntSet>();
	}

	/**
	 * Called when an atomic test has finished, whether the test succeeds or fails.
	 * 
	 */
	public void testFinished(Description description) {
		CodeCoverageCollect.testCases.put(CodeCoverageCollect.Name_testCase,
				CodeCoverageCollect.testcase);
	}

	/**
	 * Called when all tests have finished.
	 * 
	 */
	public void testRunFinished(Result result) throws IOException {
		System.out.println("Testing Finished!");
		System.out.println();
		System.out.println();

		File file = new File("stmt-cov.txt");
		FileOutputStream fos = new FileOutputStream(file);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

		StringBuilder coverageResult = new StringBuilder();
		for (String name : CodeCoverageCollect.testCases.keySet()) {
			coverageResult.append(name + "\n");

			Object2ObjectOpenHashMap<String, IntSet> coverage = CodeCoverageCollect.testCases
					.get(name);

			for (String className : coverage.keySet()) {
				int[] lines = coverage.get(className).toIntArray();

				Arrays.sort(lines);
				
				for (int i = 0; i < lines.length; i++) {
					coverageResult.append(className + ":" + lines[i] + "\n");
				}
			}
		}

		writer.write(coverageResult.toString());
		writer.close();
	}
}
