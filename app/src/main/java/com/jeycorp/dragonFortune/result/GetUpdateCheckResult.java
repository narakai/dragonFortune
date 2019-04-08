package com.jeycorp.dragonFortune.result;

import com.jeycorp.dragonFortune.type.Version;

import java.util.List;

public class GetUpdateCheckResult extends BaseResult{

    List<Version> settings;

    public List<Version> getSettings() {
        return settings;
    }

    public void setSettings(List<Version> settings) {
        this.settings = settings;
    }
}
