package io.jsonwebtoken.impl;

import io.jsonwebtoken.Clock;
import java.util.Date;

/* loaded from: classes5.dex */
public class DefaultClock implements Clock {
    public static final Clock INSTANCE = new DefaultClock();

    @Override // io.jsonwebtoken.Clock
    public Date now() {
        return new Date();
    }
}
