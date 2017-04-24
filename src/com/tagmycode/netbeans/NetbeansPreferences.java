package com.tagmycode.netbeans;

import org.openide.util.NbPreferences;

class NetbeansPreferences {

    public String read(String string) {
        return NbPreferences.forModule(NetbeansPreferences.class).get(string, "");
    }

    public void write(String key, String value) {
        NbPreferences.forModule(NetbeansPreferences.class).put(key, value);
    }

    public void unset(String string) {
        NbPreferences.forModule(NetbeansPreferences.class).remove(string);
    }

}
