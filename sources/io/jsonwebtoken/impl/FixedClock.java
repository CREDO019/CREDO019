package io.jsonwebtoken.impl;

import io.jsonwebtoken.Clock;
import java.util.Date;

/* loaded from: classes5.dex */
public class FixedClock implements Clock {
    private final Date now;

    public FixedClock() {
        this(new Date());
    }

    public FixedClock(Date date) {
        this.now = date;
    }

    @Override // io.jsonwebtoken.Clock
    public Date now() {
        return this.now;
    }
}
