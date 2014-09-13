TagMyCode Official NetBeans Plugin
==================================

This is the official NetBeans plugin for TagMyCode


## Getting started ##
### Dependencies###
To download all dependencies use Maven

```bash
mvn clean package
```

this command download all required dependencies and merge it into a jar file ```release/modules/ext/tagmycode-jar-with-dependencies.jar```

###Consumer Id and Consumer secret###
You need to create a Java class Secret.java into ```com/tagmycode/netbeans```

```java
package com.tagmycode.netbeans;

import com.tagmycode.plugin.AbstractSecret;

class Secret extends AbstractSecret {

    @Override
    public String getConsumerId() {
        return "";
    }

    @Override
    public String getConsumerSecret() {
        return "";
    }

}

```
