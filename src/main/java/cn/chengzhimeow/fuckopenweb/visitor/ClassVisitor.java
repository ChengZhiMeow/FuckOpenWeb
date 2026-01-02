package cn.chengzhimeow.fuckopenweb.visitor;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassVisitor extends org.objectweb.asm.ClassVisitor {
    private final String className;

    public ClassVisitor(ClassWriter classWriter, String className) {
        super(Opcodes.ASM9, classWriter);
        this.className = className;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);

        return new MethodVisitor(Opcodes.ASM9, mv) {
            @Override
            public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
                if (owner.equals("java/awt/Desktop") && name.equals("browse")) {
                    System.out.println("[FuckOpenWeb] 在 " + ClassVisitor.this.className + " 找到 打开浏览器访问网站 操作, 已拦截!");

                    super.visitMethodInsn(
                            Opcodes.INVOKESTATIC,
                            "cn/chengzhimeow/fuckopenweb/magic/FakeMethods",
                            "fakeBrowse",
                            "(Ljava/awt/Desktop;Ljava/net/URI;)V",
                            false
                    );
                    return;
                }

                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
            }
        };
    }
}
