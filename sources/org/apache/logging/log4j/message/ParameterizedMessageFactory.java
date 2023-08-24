package org.apache.logging.log4j.message;

/* loaded from: classes5.dex */
public final class ParameterizedMessageFactory extends AbstractMessageFactory {
    public static final ParameterizedMessageFactory INSTANCE = new ParameterizedMessageFactory();
    private static final long serialVersionUID = 1;

    @Override // org.apache.logging.log4j.message.AbstractMessageFactory, org.apache.logging.log4j.message.MessageFactory
    public Message newMessage(String str, Object... objArr) {
        return new ParameterizedMessage(str, objArr);
    }
}
