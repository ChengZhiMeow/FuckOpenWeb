package cn.chengzhimeow.fuckopenweb.transformer;

import cn.chengzhimeow.fuckopenweb.visitor.ClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassFormer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        if (className.startsWith("cn/chengzhimeow/fuckopenweb/") || className.startsWith("java/") || className.startsWith("sun/") || className.startsWith("com/sun/") || className.startsWith("jdk/"))
            return classfileBuffer;

        try {
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
            classReader.accept(new ClassVisitor(classWriter, className), 0);

            return classWriter.toByteArray();
        } catch (Throwable t) {
            return classfileBuffer;
        }
    }
}
