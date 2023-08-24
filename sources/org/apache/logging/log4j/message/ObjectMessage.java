package org.apache.logging.log4j.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class ObjectMessage implements Message {
    private static final long serialVersionUID = -5903272448334166185L;
    private transient Object obj;
    private transient String objectString;

    public ObjectMessage(String str) {
        this.obj = str == null ? "null" : str;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.objectString == null) {
            this.objectString = String.valueOf(this.obj);
        }
        return this.objectString;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return getFormattedMessage();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        return new Object[]{this.obj};
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Object obj2 = this.obj;
        Object obj3 = ((ObjectMessage) obj).obj;
        return obj2 == null ? obj3 == null : equalObjectsOrStrings(obj2, obj3);
    }

    private boolean equalObjectsOrStrings(Object obj, Object obj2) {
        return obj.equals(obj2) || String.valueOf(obj).equals(String.valueOf(obj2));
    }

    public int hashCode() {
        Object obj = this.obj;
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ObjectMessage[obj=" + getFormattedMessage() + ']';
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        Object obj = this.obj;
        if (obj instanceof Serializable) {
            objectOutputStream.writeObject(obj);
        } else {
            objectOutputStream.writeObject(String.valueOf(obj));
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.obj = objectInputStream.readObject();
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        Object obj = this.obj;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        return null;
    }
}
