package io.nlopez.smartlocation.activity;

import android.content.Context;
import com.google.android.gms.location.DetectedActivity;
import io.nlopez.smartlocation.OnActivityUpdatedListener;
import io.nlopez.smartlocation.activity.config.ActivityParams;
import io.nlopez.smartlocation.utils.Logger;

/* loaded from: classes5.dex */
public interface ActivityProvider {
    DetectedActivity getLastActivity();

    void init(Context context, Logger logger);

    void start(OnActivityUpdatedListener onActivityUpdatedListener, ActivityParams activityParams);

    void stop();
}
