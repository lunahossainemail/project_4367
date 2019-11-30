package com.se4367;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * A visitor to visit a Java class. The methods of this class must be called in
 * the following order: visit [ visitSource ] [ visitOuterClass ] (
 * visitAnnotation | visitTypeAnnotation | visitAttribute )* ( visitInnerClass |
 * visitField | visitMethod )* visitEnd.
 * 
 * @author: Tania
 */

class CustomJavaClassTransformVisitor extends ClassVisitor implements Opcodes {
	String classname;

	public CustomJavaClassTransformVisitor(final ClassVisitor codevisitor) {
		super(ASM5, codevisitor);
	}

	@Override
	/**
	 * Visits the header of the class.
	 */
	public void visit(int version, int access, String nameOfClass, String signature, String superName,
			String[] interfaces) {
		super.visit(version, access, nameOfClass, signature, superName, interfaces);
		this.classname = nameOfClass;

	}

	public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature,
			final String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);

		return mv == null ? null : new CustomMethodTransformVisitor(mv, classname);
	}
}
