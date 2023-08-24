package com.google.android.datatransport;

/* loaded from: classes2.dex */
public abstract class Event<T> {
    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public static <T> Event<T> ofData(int r2, T t) {
        return new AutoValue_Event(Integer.valueOf(r2), t, Priority.DEFAULT);
    }

    public static <T> Event<T> ofData(T t) {
        return new AutoValue_Event(null, t, Priority.DEFAULT);
    }

    public static <T> Event<T> ofTelemetry(int r2, T t) {
        return new AutoValue_Event(Integer.valueOf(r2), t, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofTelemetry(T t) {
        return new AutoValue_Event(null, t, Priority.VERY_LOW);
    }

    public static <T> Event<T> ofUrgent(int r2, T t) {
        return new AutoValue_Event(Integer.valueOf(r2), t, Priority.HIGHEST);
    }

    public static <T> Event<T> ofUrgent(T t) {
        return new AutoValue_Event(null, t, Priority.HIGHEST);
    }
}
