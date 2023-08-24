package org.apache.logging.log4j.spi;

import com.google.firebase.messaging.ServiceStarter;
import java.util.EnumSet;
import java.util.Iterator;

/* loaded from: classes5.dex */
public enum StandardLevel {
    OFF(0),
    FATAL(100),
    ERROR(200),
    WARN(300),
    INFO(400),
    DEBUG(ServiceStarter.ERROR_UNKNOWN),
    TRACE(600),
    ALL(Integer.MAX_VALUE);
    
    private static final EnumSet<StandardLevel> levelSet = EnumSet.allOf(StandardLevel.class);
    private final int intLevel;

    StandardLevel(int r3) {
        this.intLevel = r3;
    }

    public int intLevel() {
        return this.intLevel;
    }

    public static StandardLevel getStandardLevel(int r4) {
        StandardLevel standardLevel = OFF;
        Iterator it = levelSet.iterator();
        while (it.hasNext()) {
            StandardLevel standardLevel2 = (StandardLevel) it.next();
            if (standardLevel2.intLevel() > r4) {
                break;
            }
            standardLevel = standardLevel2;
        }
        return standardLevel;
    }
}
