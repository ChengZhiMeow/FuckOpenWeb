package cn.chengzhimeow.fuckopenweb.magic;

import cn.chengzhimeow.fuckopenweb.Agent;

import java.awt.*;
import java.net.URI;

public class FakeMethods {
    public static void fakeBrowse(Desktop desktop, URI uri) {
    }

    public static void fakeSleep(long time) {
        if (
                Agent.configSetting.canSleepTime != -1 && Agent.configSetting.canSleepTime >= System.currentTimeMillis() &&
                        time >= Agent.configSetting.getData().getLong("thread_sleep.value")
        ) {
            return;
        }

        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void fakeSleep(long time, int nanos) {
        FakeMethods.fakeSleep(time);
    }
}
