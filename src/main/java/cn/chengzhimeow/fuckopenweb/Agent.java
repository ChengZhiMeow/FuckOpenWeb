package cn.chengzhimeow.fuckopenweb;

import cn.chengzhimeow.ccyaml.CCYaml;
import cn.chengzhimeow.fuckopenweb.config.ConfigSetting;
import cn.chengzhimeow.fuckopenweb.transformer.ClassFormer;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.util.jar.JarFile;

public class Agent {
    public static CCYaml yamlManager = new CCYaml(
            Agent.class.getClassLoader(),
            new File("./"),
            "1.0.0"
    );
    public static ConfigSetting configSetting = new ConfigSetting();

    public static void premain(String args, Instrumentation inst) {
        Agent.configSetting.saveDefaultFile();
        Agent.configSetting.update();
        Agent.configSetting.reload();

        try {
            File agentJarFile = new File(Agent.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            inst.appendToBootstrapClassLoaderSearch(new JarFile(agentJarFile));
        } catch (Exception ignored) {
        }
        inst.addTransformer(new ClassFormer());
    }
}
