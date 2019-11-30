package com.se4367;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

class CustomClassFileTransform implements ClassFileTransformer {

	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		if (className.startsWith("org/apache/commons/dbutils") || className.startsWith("org/joda/time")) {
			ClassReader reader = new ClassReader(classfileBuffer);
			ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
			CustomJavaClassTransformVisitor visitor = new CustomJavaClassTransformVisitor(writer);
			reader.accept(visitor, 0);
			return writer.toByteArray();
		}
		return classfileBuffer;

	}
}
