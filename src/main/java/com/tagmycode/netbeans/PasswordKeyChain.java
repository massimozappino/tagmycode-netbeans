package com.tagmycode.netbeans;

import com.tagmycode.plugin.IPasswordKeyChain;
import org.netbeans.api.keyring.Keyring;

class PasswordKeyChain implements IPasswordKeyChain {

    @Override
    public void saveValue(String key, String value) {
        Keyring.save(key, value.toCharArray(), "Access token");
    }

    @Override
    public String loadValue(String value) {
        final char[] readValue = Keyring.read(value);
        if (readValue == null) {
            return "";
        }
        return String.valueOf(readValue);
    }

    @Override
    public void deleteValue(String string) {
        Keyring.delete(string);
    }

}
