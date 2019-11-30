package com.se4367;

/**
 * 
 * @author: Tania
 */

import it.unimi.dsi.fastutil.ints.IntSet;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

public class CodeCoverageCollect {
	public static Object2ObjectOpenHashMap<String, Object2ObjectOpenHashMap<String, IntSet>> testCases;
	public static Object2ObjectOpenHashMap<String, IntSet> testcase;
	public static String Name_testCase;

	// Called whenever executing a line
	public static void addMethodLine(String className, Integer line) {
		if (testcase == null) {
			return;
		}

		IntSet lines = testcase.get(className);
		if (lines != null) {
			lines.add(line);
		} else {
			lines = new IntOpenHashSet(new int[] { line });
			testcase.put(className, lines);
		}
	}
}
