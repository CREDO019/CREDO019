package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.time.Clock;
import com.google.firebase.installations.time.SystemClock;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* renamed from: com.google.firebase.installations.Utils */
/* loaded from: classes3.dex */
public final class C3253Utils {
    private static final String APP_ID_IDENTIFICATION_SUBSTRING = ":";
    private static C3253Utils singleton;
    private final Clock clock;
    public static final long AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS = TimeUnit.HOURS.toSeconds(1);
    private static final Pattern API_KEY_FORMAT = Pattern.compile("\\AA[\\w-]{38}\\z");

    private C3253Utils(Clock clock) {
        this.clock = clock;
    }

    public static C3253Utils getInstance() {
        return getInstance(SystemClock.getInstance());
    }

    public static C3253Utils getInstance(Clock clock) {
        if (singleton == null) {
            singleton = new C3253Utils(clock);
        }
        return singleton;
    }

    public boolean isAuthTokenExpired(PersistedInstallationEntry persistedInstallationEntry) {
        return TextUtils.isEmpty(persistedInstallationEntry.getAuthToken()) || persistedInstallationEntry.getTokenCreationEpochInSecs() + persistedInstallationEntry.getExpiresInSecs() < currentTimeInSecs() + AUTH_TOKEN_EXPIRATION_BUFFER_IN_SECS;
    }

    public long currentTimeInSecs() {
        return TimeUnit.MILLISECONDS.toSeconds(currentTimeInMillis());
    }

    public long currentTimeInMillis() {
        return this.clock.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidAppIdFormat(String str) {
        return str.contains(":");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isValidApiKeyFormat(String str) {
        return API_KEY_FORMAT.matcher(str).matches();
    }

    public long getRandomDelayForSyncPrevention() {
        return (long) (Math.random() * 1000.0d);
    }
}
