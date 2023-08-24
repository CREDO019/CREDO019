package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import java.util.ResourceBundle;
import org.apache.logging.log4j.status.StatusLogger;

/* loaded from: classes5.dex */
public class LocalizedMessage implements Message, LoggerNameAwareMessage {
    private static final long serialVersionUID = 3893703791567290742L;
    private transient Object[] argArray;
    private String baseName;
    private String formattedMessage;
    private String key;
    private final Locale locale;
    private transient StatusLogger logger;
    private String loggerName;
    private transient ResourceBundle resourceBundle;
    private String[] stringArgs;
    private transient Throwable throwable;

    public LocalizedMessage(String str, Object[] objArr) {
        this((ResourceBundle) null, (Locale) null, str, objArr);
    }

    public LocalizedMessage(String str, String str2, Object[] objArr) {
        this(str, (Locale) null, str2, objArr);
    }

    public LocalizedMessage(ResourceBundle resourceBundle, String str, Object[] objArr) {
        this(resourceBundle, (Locale) null, str, objArr);
    }

    public LocalizedMessage(String str, Locale locale, String str2, Object[] objArr) {
        this.logger = StatusLogger.getLogger();
        this.key = str2;
        this.argArray = objArr;
        this.throwable = null;
        this.baseName = str;
        this.resourceBundle = null;
        this.locale = locale;
    }

    public LocalizedMessage(ResourceBundle resourceBundle, Locale locale, String str, Object[] objArr) {
        this.logger = StatusLogger.getLogger();
        this.key = str;
        this.argArray = objArr;
        this.throwable = null;
        this.baseName = null;
        this.resourceBundle = resourceBundle;
        this.locale = locale;
    }

    public LocalizedMessage(Locale locale, String str, Object[] objArr) {
        this((ResourceBundle) null, locale, str, objArr);
    }

    public LocalizedMessage(String str, Object obj) {
        this((ResourceBundle) null, (Locale) null, str, new Object[]{obj});
    }

    public LocalizedMessage(String str, String str2, Object obj) {
        this(str, (Locale) null, str2, new Object[]{obj});
    }

    public LocalizedMessage(ResourceBundle resourceBundle, String str, Object obj) {
        this(resourceBundle, (Locale) null, str, new Object[]{obj});
    }

    public LocalizedMessage(String str, Locale locale, String str2, Object obj) {
        this(str, locale, str2, new Object[]{obj});
    }

    public LocalizedMessage(ResourceBundle resourceBundle, Locale locale, String str, Object obj) {
        this(resourceBundle, locale, str, new Object[]{obj});
    }

    public LocalizedMessage(Locale locale, String str, Object obj) {
        this((ResourceBundle) null, locale, str, new Object[]{obj});
    }

    public LocalizedMessage(String str, Object obj, Object obj2) {
        this((ResourceBundle) null, (Locale) null, str, new Object[]{obj, obj2});
    }

    public LocalizedMessage(String str, String str2, Object obj, Object obj2) {
        this(str, (Locale) null, str2, new Object[]{obj, obj2});
    }

    public LocalizedMessage(ResourceBundle resourceBundle, String str, Object obj, Object obj2) {
        this(resourceBundle, (Locale) null, str, new Object[]{obj, obj2});
    }

    public LocalizedMessage(String str, Locale locale, String str2, Object obj, Object obj2) {
        this(str, locale, str2, new Object[]{obj, obj2});
    }

    public LocalizedMessage(ResourceBundle resourceBundle, Locale locale, String str, Object obj, Object obj2) {
        this(resourceBundle, locale, str, new Object[]{obj, obj2});
    }

    public LocalizedMessage(Locale locale, String str, Object obj, Object obj2) {
        this((ResourceBundle) null, locale, str, new Object[]{obj, obj2});
    }

    @Override // org.apache.logging.log4j.message.LoggerNameAwareMessage
    public void setLoggerName(String str) {
        this.loggerName = str;
    }

    @Override // org.apache.logging.log4j.message.LoggerNameAwareMessage
    public String getLoggerName() {
        return this.loggerName;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        String str = this.formattedMessage;
        if (str != null) {
            return str;
        }
        ResourceBundle resourceBundle = this.resourceBundle;
        if (resourceBundle == null) {
            String str2 = this.baseName;
            if (str2 != null) {
                resourceBundle = getResourceBundle(str2, this.locale, false);
            } else {
                resourceBundle = getResourceBundle(this.loggerName, this.locale, true);
            }
        }
        String format = getFormat();
        if (resourceBundle != null && resourceBundle.containsKey(format)) {
            format = resourceBundle.getString(format);
        }
        Object[] objArr = this.argArray;
        if (objArr == null) {
            objArr = this.stringArgs;
        }
        FormattedMessage formattedMessage = new FormattedMessage(format, objArr);
        this.formattedMessage = formattedMessage.getFormattedMessage();
        this.throwable = formattedMessage.getThrowable();
        return this.formattedMessage;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.key;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        Object[] objArr = this.argArray;
        return objArr != null ? objArr : this.stringArgs;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002d  */
    /* JADX WARN: Type inference failed for: r6v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.util.ResourceBundle] */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.util.ResourceBundle] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.util.ResourceBundle] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.util.ResourceBundle] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x003c -> B:9:0x0011). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0041 -> B:9:0x0011). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected java.util.ResourceBundle getResourceBundle(java.lang.String r4, java.util.Locale r5, boolean r6) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            java.lang.String r1 = "Unable to locate ResourceBundle "
            if (r5 == 0) goto Ld
            java.util.ResourceBundle r6 = java.util.ResourceBundle.getBundle(r4, r5)     // Catch: java.util.MissingResourceException -> L13
            goto L11
        Ld:
            java.util.ResourceBundle r6 = java.util.ResourceBundle.getBundle(r4)     // Catch: java.util.MissingResourceException -> L13
        L11:
            r0 = r6
            goto L2b
        L13:
            if (r6 != 0) goto L2b
            org.apache.logging.log4j.status.StatusLogger r5 = r3.logger
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r1)
            r6.append(r4)
            java.lang.String r4 = r6.toString()
            r5.debug(r4)
            return r0
        L2b:
            if (r0 != 0) goto L5b
            r6 = 46
            int r6 = r4.lastIndexOf(r6)
            if (r6 <= 0) goto L5b
            r2 = 0
            java.lang.String r4 = r4.substring(r2, r6)
            if (r5 == 0) goto L41
            java.util.ResourceBundle r6 = java.util.ResourceBundle.getBundle(r4, r5)     // Catch: java.util.MissingResourceException -> L46
            goto L11
        L41:
            java.util.ResourceBundle r6 = java.util.ResourceBundle.getBundle(r4)     // Catch: java.util.MissingResourceException -> L46
            goto L11
        L46:
            org.apache.logging.log4j.status.StatusLogger r6 = r3.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r6.debug(r2)
            goto L2b
        L5b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.logging.log4j.message.LocalizedMessage.getResourceBundle(java.lang.String, java.util.Locale, boolean):java.util.ResourceBundle");
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        getFormattedMessage();
        objectOutputStream.writeUTF(this.formattedMessage);
        objectOutputStream.writeUTF(this.key);
        objectOutputStream.writeUTF(this.baseName);
        objectOutputStream.writeInt(this.argArray.length);
        Object[] objArr = this.argArray;
        this.stringArgs = new String[objArr.length];
        int r3 = 0;
        for (Object obj : objArr) {
            this.stringArgs[r3] = obj.toString();
            r3++;
        }
        objectOutputStream.writeObject(this.stringArgs);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.formattedMessage = objectInputStream.readUTF();
        this.key = objectInputStream.readUTF();
        this.baseName = objectInputStream.readUTF();
        objectInputStream.readInt();
        this.stringArgs = (String[]) objectInputStream.readObject();
        this.logger = StatusLogger.getLogger();
        this.resourceBundle = null;
        this.argArray = null;
    }
}
