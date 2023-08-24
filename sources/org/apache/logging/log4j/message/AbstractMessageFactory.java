package org.apache.logging.log4j.message;

import java.io.Serializable;

/* loaded from: classes5.dex */
public abstract class AbstractMessageFactory implements MessageFactory, Serializable {
    private static final long serialVersionUID = 1;

    @Override // org.apache.logging.log4j.message.MessageFactory
    public abstract Message newMessage(String str, Object... objArr);

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(Object obj) {
        return new ObjectMessage(obj);
    }

    @Override // org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str) {
        return new SimpleMessage(str);
    }
}
