package com.se4367;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * 
 * @author: Tania
 */

class CustomMethodTransformVisitor extends MethodVisitor implements Opcodes {

	protected int lastVisitedLine;
	protected String className;

	public CustomMethodTransformVisitor(final MethodVisitor mv, String nameOfclass) {

		super(ASM5, mv);
		this.className = nameOfclass;

	}

	@Override
	public void visitLineNumber(int line, Label start) {
		if (0 != line) {
			lastVisitedLine = line;

			mv.visitLdcInsn(className);
			mv.visitLdcInsn(line);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
			mv.visitMethodInsn(INVOKESTATIC, "com/se4367/CodeCoverageCollect", "addMethodLine",
					"(Ljava/lang/String;Ljava/lang/Integer;)V", false);
		}

		super.visitLineNumber(line, start);
	}
}
