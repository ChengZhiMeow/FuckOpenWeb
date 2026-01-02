package cn.chengzhimeow.fuckopenweb;

import cn.chengzhimeow.fuckopenweb.transformer.ClassFormer;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class Agent {
    public static void premain(String args, Instrumentation inst) {
        try {
            File agentJarFile = new File(Agent.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            inst.appendToBootstrapClassLoaderSearch(new JarFile(agentJarFile));
        } catch (Exception ignored) {
        }
        inst.addTransformer(new ClassFormer());
    }
}
