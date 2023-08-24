package org.apache.logging.log4j.message;

import java.util.ResourceBundle;

/* loaded from: classes5.dex */
public class LocalizedMessageFactory extends AbstractMessageFactory {
    private static final long serialVersionUID = 1;
    private final String baseName;
    private final ResourceBundle resourceBundle;

    public LocalizedMessageFactory(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        this.baseName = null;
    }

    public LocalizedMessageFactory(String str) {
        this.resourceBundle = null;
        this.baseName = str;
    }

    public String getBaseName() {
        return this.baseName;
    }

    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str, Object... objArr) {
        ResourceBundle resourceBundle = this.resourceBundle;
        if (resourceBundle == null) {
            return new LocalizedMessage(this.baseName, str, objArr);
        }
        return new LocalizedMessage(resourceBundle, str, objArr);
    }
}
