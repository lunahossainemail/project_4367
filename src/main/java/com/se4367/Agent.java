package com.se4367;

import java.lang.instrument.Instrumentation;

/**
 * 
 * @author: Tania
 */
public class Agent {
	/**
	 * The premain method has one of two possible signatures. The JVM first attempts
	 * to invoke the following method on the agent class
	 * 
	 * @param agentArgs Each agent is passed its agent options via the agentArgs
	 *                  parameter. The agent options are passed as a single string,
	 *                  any additional parsing should be performed by the agent
	 *                  itself.
	 * 
	 * 
	 * @param inst      This object provides services needed to instrument Java
	 *                  programming language code.
	 * 
	 */
	public static void premain(String agentArgs, Instrumentation inst) {

		System.out.println("ASM Java Agent is running");

		inst.addTransformer(new CustomClassFileTransform());

	}
}
