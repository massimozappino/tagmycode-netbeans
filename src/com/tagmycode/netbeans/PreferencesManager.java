package com.tagmycode.netbeans;

import com.tagmycode.plugin.IStorage;
import org.openide.util.NbPreferences;

class PreferencesManager implements IStorage {

    @Override
    public String read(String string) {
        return NbPreferences.forModule(PreferencesManager.class).get(string, "");
    }

    @Override
    public void write(String key, String value) {
        NbPreferences.forModule(PreferencesManager.class).put(key, value);
    }

    @Override
    public void unset(String string) {
        NbPreferences.forModule(PreferencesManager.class).remove(string);
    }

}
