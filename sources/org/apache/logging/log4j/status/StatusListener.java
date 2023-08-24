package org.apache.logging.log4j.status;

import java.io.Closeable;
import java.util.EventListener;
import org.apache.logging.log4j.Level;

/* loaded from: classes5.dex */
public interface StatusListener extends Closeable, EventListener {
    Level getStatusLevel();

    void log(StatusData statusData);
}
