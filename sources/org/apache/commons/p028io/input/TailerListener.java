package org.apache.commons.p028io.input;

/* renamed from: org.apache.commons.io.input.TailerListener */
/* loaded from: classes5.dex */
public interface TailerListener {
    void fileNotFound();

    void fileRotated();

    void handle(Exception exc);

    void handle(String str);

    void init(Tailer tailer);
}
