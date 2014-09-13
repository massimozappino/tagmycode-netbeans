package com.tagmycode.netbeans;

import org.junit.Test;

public class PasswordManagerTest {
    

    @Test
    public void testLoadValue() {
        PasswordKeyChain passwordManager = new PasswordKeyChain();
       
        String result = passwordManager.loadValue("not_existent_value");
        
        assertEquals("", result);
    }

}
