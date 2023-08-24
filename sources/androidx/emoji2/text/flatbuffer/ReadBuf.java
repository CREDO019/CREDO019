package androidx.emoji2.text.flatbuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public interface ReadBuf {
    byte[] data();

    byte get(int r1);

    boolean getBoolean(int r1);

    double getDouble(int r1);

    float getFloat(int r1);

    int getInt(int r1);

    long getLong(int r1);

    short getShort(int r1);

    String getString(int r1, int r2);

    int limit();
}
