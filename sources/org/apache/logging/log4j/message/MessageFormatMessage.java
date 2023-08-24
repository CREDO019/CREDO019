package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.IllegalFormatException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes5.dex */
public class MessageFormatMessage implements Message {
    private static final int HASHVAL = 31;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final long serialVersionUID = 1;
    private transient String formattedMessage;
    private String messagePattern;
    private transient Object[] parameters;
    private String[] serializedParameters;
    private transient Throwable throwable;

    public MessageFormatMessage(String str, Object... objArr) {
        this.messagePattern = str;
        this.parameters = objArr;
        int length = objArr == null ? 0 : objArr.length;
        if (length > 0) {
            int r2 = length - 1;
            if (objArr[r2] instanceof Throwable) {
                this.throwable = (Throwable) objArr[r2];
            }
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            this.formattedMessage = formatMessage(this.messagePattern, this.parameters);
        }
        return this.formattedMessage;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        Object[] objArr = this.parameters;
        return objArr != null ? objArr : this.serializedParameters;
    }

    protected String formatMessage(String str, Object... objArr) {
        try {
            return MessageFormat.format(str, objArr);
        } catch (IllegalFormatException e) {
            Logger logger = LOGGER;
            logger.error("Unable to format msg: " + str, (Throwable) e);
            return str;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MessageFormatMessage messageFormatMessage = (MessageFormatMessage) obj;
        String str = this.messagePattern;
        if (str == null ? messageFormatMessage.messagePattern == null : str.equals(messageFormatMessage.messagePattern)) {
            return Arrays.equals(this.serializedParameters, messageFormatMessage.serializedParameters);
        }
        return false;
    }

    public int hashCode() {
        String str = this.messagePattern;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String[] strArr = this.serializedParameters;
        return hashCode + (strArr != null ? Arrays.hashCode(strArr) : 0);
    }

    public String toString() {
        return "StringFormatMessage[messagePattern=" + this.messagePattern + ", args=" + Arrays.toString(this.parameters) + ']';
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        getFormattedMessage();
        objectOutputStream.writeUTF(this.formattedMessage);
        objectOutputStream.writeUTF(this.messagePattern);
        Object[] objArr = this.parameters;
        int length = objArr == null ? 0 : objArr.length;
        objectOutputStream.writeInt(length);
        this.serializedParameters = new String[length];
        if (length > 0) {
            for (int r1 = 0; r1 < length; r1++) {
                this.serializedParameters[r1] = String.valueOf(this.parameters[r1]);
                objectOutputStream.writeUTF(this.serializedParameters[r1]);
            }
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        this.parameters = null;
        this.throwable = null;
        this.formattedMessage = objectInputStream.readUTF();
        this.messagePattern = objectInputStream.readUTF();
        int readInt = objectInputStream.readInt();
        this.serializedParameters = new String[readInt];
        for (int r1 = 0; r1 < readInt; r1++) {
            this.serializedParameters[r1] = objectInputStream.readUTF();
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }
}
