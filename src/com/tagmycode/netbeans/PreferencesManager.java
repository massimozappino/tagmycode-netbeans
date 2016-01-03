package com.tagmycode.netbeans;

import com.tagmycode.plugin.AbstractStorage;
import org.openide.util.NbPreferences;

class PreferencesManager extends AbstractStorage {

    @Override
    protected String read(String string) {
        return NbPreferences.forModule(PreferencesManager.class).get(string, "");
    }

    @Override
    protected void write(String key, String value) {
        NbPreferences.forModule(PreferencesManager.class).put(key, value);
    }

    @Override
    protected void unset(String string) {
        NbPreferences.forModule(PreferencesManager.class).remove(string);
    }

}
