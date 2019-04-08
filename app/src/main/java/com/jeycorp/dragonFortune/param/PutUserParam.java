package com.jeycorp.dragonFortune.param;


import com.jeycorp.dragonFortune.type.Device;
import com.jeycorp.dragonFortune.type.User;

public class PutUserParam extends BaseParam{
    private String uid;
    private User user;
    private Device device;

    public String getUid() {
        return uid;
    }


    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
