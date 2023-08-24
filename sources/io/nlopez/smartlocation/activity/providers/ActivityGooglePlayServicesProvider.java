package io.nlopez.smartlocation.activity.providers;

import android.app.Activity;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Bundle;
import com.google.android.exoplayer2.C1856C;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;
import io.nlopez.smartlocation.OnActivityUpdatedListener;
import io.nlopez.smartlocation.activity.ActivityProvider;
import io.nlopez.smartlocation.activity.ActivityStore;
import io.nlopez.smartlocation.activity.config.ActivityParams;
import io.nlopez.smartlocation.utils.GooglePlayServicesListener;
import io.nlopez.smartlocation.utils.Logger;

/* loaded from: classes5.dex */
public class ActivityGooglePlayServicesProvider implements ActivityProvider, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status> {
    private static final String BROADCAST_INTENT_ACTION = ActivityGooglePlayServicesProvider.class.getCanonicalName() + ".DETECTED_ACTIVITY";
    private static final String DETECTED_ACTIVITY_EXTRA_ID = "activity";
    private static final String GMS_ID = "GMS";
    public static final int RESULT_CODE = 10002;
    private ActivityParams activityParams;
    private BroadcastReceiver activityReceiver;
    private ActivityStore activityStore;
    private GoogleApiClient client;
    private Context context;
    private final GooglePlayServicesListener googlePlayServicesListener;
    private OnActivityUpdatedListener listener;
    private Logger logger;
    private PendingIntent pendingIntent;
    private boolean shouldStart;
    private boolean stopped;

    public ActivityGooglePlayServicesProvider() {
        this(null);
    }

    public ActivityGooglePlayServicesProvider(GooglePlayServicesListener googlePlayServicesListener) {
        this.shouldStart = false;
        this.stopped = false;
        this.activityReceiver = new BroadcastReceiver() { // from class: io.nlopez.smartlocation.activity.providers.ActivityGooglePlayServicesProvider.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (ActivityGooglePlayServicesProvider.BROADCAST_INTENT_ACTION.equals(intent.getAction()) && intent.hasExtra(ActivityGooglePlayServicesProvider.DETECTED_ACTIVITY_EXTRA_ID)) {
                    ActivityGooglePlayServicesProvider.this.logger.mo195d("sending new activity", new Object[0]);
                    ActivityGooglePlayServicesProvider.this.notifyActivity((DetectedActivity) intent.getParcelableExtra(ActivityGooglePlayServicesProvider.DETECTED_ACTIVITY_EXTRA_ID));
                }
            }
        };
        this.googlePlayServicesListener = googlePlayServicesListener;
    }

    @Override // io.nlopez.smartlocation.activity.ActivityProvider
    public void init(Context context, Logger logger) {
        this.context = context;
        this.logger = logger;
        this.activityStore = new ActivityStore(context);
        if (!this.shouldStart) {
            GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(ActivityRecognition.API).addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
            this.client = build;
            build.connect();
            return;
        }
        logger.mo195d("already started", new Object[0]);
    }

    @Override // io.nlopez.smartlocation.activity.ActivityProvider
    public void start(OnActivityUpdatedListener onActivityUpdatedListener, ActivityParams activityParams) {
        this.activityParams = activityParams;
        this.listener = onActivityUpdatedListener;
        this.context.registerReceiver(this.activityReceiver, new IntentFilter(BROADCAST_INTENT_ACTION));
        if (this.client.isConnected()) {
            startUpdating(activityParams);
        } else if (this.stopped) {
            this.shouldStart = true;
            this.client.connect();
            this.stopped = false;
        } else {
            this.shouldStart = true;
            this.logger.mo195d("still not connected - scheduled start when connection is ok", new Object[0]);
        }
    }

    private void startUpdating(ActivityParams activityParams) {
        if (this.client.isConnected()) {
            this.pendingIntent = PendingIntent.getService(this.context, 0, new Intent(this.context, ActivityRecognitionService.class), C1856C.BUFFER_FLAG_FIRST_SAMPLE);
            ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(this.client, activityParams.getInterval(), this.pendingIntent).setResultCallback(this);
        }
    }

    @Override // io.nlopez.smartlocation.activity.ActivityProvider
    public void stop() {
        this.logger.mo195d("stop", new Object[0]);
        if (this.client.isConnected()) {
            ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(this.client, this.pendingIntent);
            this.client.disconnect();
        }
        try {
            this.context.unregisterReceiver(this.activityReceiver);
        } catch (IllegalArgumentException unused) {
            this.logger.mo195d("Silenced 'receiver not registered' stuff (calling stop more times than necessary did this)", new Object[0]);
        }
        this.shouldStart = false;
        this.stopped = true;
    }

    @Override // io.nlopez.smartlocation.activity.ActivityProvider
    public DetectedActivity getLastActivity() {
        ActivityStore activityStore = this.activityStore;
        if (activityStore != null) {
            return activityStore.get(GMS_ID);
        }
        return null;
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnected(Bundle bundle) {
        this.logger.mo195d("onConnected", new Object[0]);
        if (this.shouldStart) {
            startUpdating(this.activityParams);
        }
        GooglePlayServicesListener googlePlayServicesListener = this.googlePlayServicesListener;
        if (googlePlayServicesListener != null) {
            googlePlayServicesListener.onConnected(bundle);
        }
    }

    @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
    public void onConnectionSuspended(int r4) {
        Logger logger = this.logger;
        logger.mo195d("onConnectionSuspended " + r4, new Object[0]);
        GooglePlayServicesListener googlePlayServicesListener = this.googlePlayServicesListener;
        if (googlePlayServicesListener != null) {
            googlePlayServicesListener.onConnectionSuspended(r4);
        }
    }

    @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.logger.mo195d("onConnectionFailed", new Object[0]);
        GooglePlayServicesListener googlePlayServicesListener = this.googlePlayServicesListener;
        if (googlePlayServicesListener != null) {
            googlePlayServicesListener.onConnectionFailed(connectionResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyActivity(DetectedActivity detectedActivity) {
        OnActivityUpdatedListener onActivityUpdatedListener = this.listener;
        if (onActivityUpdatedListener != null) {
            onActivityUpdatedListener.onActivityUpdated(detectedActivity);
        }
        ActivityStore activityStore = this.activityStore;
        if (activityStore != null) {
            activityStore.put(GMS_ID, detectedActivity);
        }
    }

    /* loaded from: classes5.dex */
    public static class ActivityRecognitionService extends IntentService {
        public ActivityRecognitionService() {
            super(ActivityRecognitionService.class.getSimpleName());
        }

        @Override // android.app.IntentService
        protected void onHandleIntent(Intent intent) {
            if (ActivityRecognitionResult.hasResult(intent)) {
                DetectedActivity mostProbableActivity = ActivityRecognitionResult.extractResult(intent).getMostProbableActivity();
                Intent intent2 = new Intent(ActivityGooglePlayServicesProvider.BROADCAST_INTENT_ACTION);
                intent2.putExtra(ActivityGooglePlayServicesProvider.DETECTED_ACTIVITY_EXTRA_ID, mostProbableActivity);
                sendBroadcast(intent2);
            }
        }
    }

    @Override // com.google.android.gms.common.api.ResultCallback
    public void onResult(Status status) {
        if (status.isSuccess()) {
            this.logger.mo195d("Activity update request successful", new Object[0]);
        } else if (status.hasResolution() && (this.context instanceof Activity)) {
            this.logger.mo187w("Unable to register, but we can solve this - will startActivityForResult expecting result code 10002 (if received, please try again)", new Object[0]);
            try {
                status.startResolutionForResult((Activity) this.context, RESULT_CODE);
            } catch (IntentSender.SendIntentException e) {
                this.logger.mo192e(e, "problem with startResolutionForResult", new Object[0]);
            }
        } else {
            Logger logger = this.logger;
            logger.mo193e("Registering failed: " + status.getStatusMessage(), new Object[0]);
        }
    }
}
