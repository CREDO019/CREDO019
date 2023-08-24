package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Format;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public class FormattedMessage implements Message {
    private static final int HASHVAL = 31;
    private static final long serialVersionUID = -665975803997290697L;
    private transient Object[] argArray;
    private transient String formattedMessage;
    private Message message;
    private String messagePattern;
    private String[] stringArgs;
    private final Throwable throwable;
    private static final String FORMAT_SPECIFIER = "%(\\d+\\$)?([-#+ 0,(\\<]*)?(\\d+)?(\\.\\d+)?([tT])?([a-zA-Z%])";
    private static final Pattern MSG_PATTERN = Pattern.compile(FORMAT_SPECIFIER);

    public FormattedMessage(String str, Object[] objArr, Throwable th) {
        this.messagePattern = str;
        this.argArray = objArr;
        this.throwable = th;
    }

    public FormattedMessage(String str, Object[] objArr) {
        this(str, objArr, (Throwable) null);
    }

    public FormattedMessage(String str, Object obj) {
        this(str, new Object[]{obj}, (Throwable) null);
    }

    public FormattedMessage(String str, Object obj, Object obj2) {
        this(str, new Object[]{obj, obj2});
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            if (this.message == null) {
                this.message = getMessage(this.messagePattern, this.argArray, this.throwable);
            }
            this.formattedMessage = this.message.getFormattedMessage();
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

    protected Message getMessage(String str, Object[] objArr, Throwable th) {
        try {
            Format[] formats = new MessageFormat(str).getFormats();
            if (formats != null && formats.length > 0) {
                return new MessageFormatMessage(str, objArr);
            }
        } catch (Exception unused) {
        }
        try {
            if (MSG_PATTERN.matcher(str).find()) {
                return new StringFormattedMessage(str, objArr);
            }
        } catch (Exception unused2) {
        }
        return new ParameterizedMessage(str, objArr, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FormattedMessage formattedMessage = (FormattedMessage) obj;
        String str = this.messagePattern;
        if (str == null ? formattedMessage.messagePattern == null : str.equals(formattedMessage.messagePattern)) {
            return Arrays.equals(this.stringArgs, formattedMessage.stringArgs);
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
        return "FormattedMessage[messagePattern=" + this.messagePattern + ", args=" + Arrays.toString(this.argArray) + ']';
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
        Throwable th = this.throwable;
        if (th != null) {
            return th;
        }
        if (this.message == null) {
            this.message = getMessage(this.messagePattern, this.argArray, null);
        }
        return this.message.getThrowable();
    }
}
