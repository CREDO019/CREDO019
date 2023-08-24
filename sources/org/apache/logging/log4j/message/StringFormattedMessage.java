package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.IllegalFormatException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes5.dex */
public class StringFormattedMessage implements Message {
    private static final int HASHVAL = 31;
    private static final Logger LOGGER = StatusLogger.getLogger();
    private static final long serialVersionUID = -665975803997290697L;
    private transient Object[] argArray;
    private transient String formattedMessage;
    private String messagePattern;
    private String[] stringArgs;
    private transient Throwable throwable;

    public StringFormattedMessage(String str, Object... objArr) {
        this.messagePattern = str;
        this.argArray = objArr;
        if (objArr == null || objArr.length <= 0 || !(objArr[objArr.length - 1] instanceof Throwable)) {
            return;
        }
        this.throwable = (Throwable) objArr[objArr.length - 1];
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            this.formattedMessage = formatMessage(this.messagePattern, this.argArray);
        }
        return this.formattedMessage;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        Object[] objArr = this.argArray;
        return objArr != null ? objArr : this.stringArgs;
    }

    protected String formatMessage(String str, Object... objArr) {
        try {
            return String.format(str, objArr);
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
        StringFormattedMessage stringFormattedMessage = (StringFormattedMessage) obj;
        String str = this.messagePattern;
        if (str == null ? stringFormattedMessage.messagePattern == null : str.equals(stringFormattedMessage.messagePattern)) {
            return Arrays.equals(this.stringArgs, stringFormattedMessage.stringArgs);
        }
        return false;
    }

    public int hashCode() {
        String str = this.messagePattern;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String[] strArr = this.stringArgs;
        return hashCode + (strArr != null ? Arrays.hashCode(strArr) : 0);
    }

    public String toString() {
        return "StringFormatMessage[messagePattern=" + this.messagePattern + ", args=" + Arrays.toString(this.argArray) + ']';
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        getFormattedMessage();
        objectOutputStream.writeUTF(this.formattedMessage);
        objectOutputStream.writeUTF(this.messagePattern);
        objectOutputStream.writeInt(this.argArray.length);
        Object[] objArr = this.argArray;
        this.stringArgs = new String[objArr.length];
        int r2 = 0;
        for (Object obj : objArr) {
            this.stringArgs[r2] = obj.toString();
            r2++;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.formattedMessage = objectInputStream.readUTF();
        this.messagePattern = objectInputStream.readUTF();
        int readInt = objectInputStream.readInt();
        this.stringArgs = new String[readInt];
        for (int r1 = 0; r1 < readInt; r1++) {
            this.stringArgs[r1] = objectInputStream.readUTF();
        }
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }
}
