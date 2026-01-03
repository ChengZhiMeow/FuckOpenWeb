package cn.chengzhimeow.fuckopenweb.config;

import cn.chengzhimeow.ccyaml.manager.AbstractYamlManager;
import cn.chengzhimeow.fuckopenweb.Agent;

public class ConfigSetting extends AbstractYamlManager {
    public long canSleepTime = -1L;

    public ConfigSetting() {
        super(Agent.yamlManager);
    }

    @Override
    public String originFilePath() {
        return "fuckopenweb_config.yml";
    }

    @Override
    public String filePath() {
        return "fuckopenweb_config.yml";
    }

    @Override
    public void reload() {
        super.reload();

        this.canSleepTime = System.currentTimeMillis() + super.getData().getLong("thread_sleep.time");
    }
}
