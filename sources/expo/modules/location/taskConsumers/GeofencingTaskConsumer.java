package expo.modules.location.taskConsumers;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingEvent;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import expo.modules.interfaces.taskManager.TaskConsumer;
import expo.modules.interfaces.taskManager.TaskConsumerInterface;
import expo.modules.interfaces.taskManager.TaskExecutionCallback;
import expo.modules.interfaces.taskManager.TaskInterface;
import expo.modules.interfaces.taskManager.TaskManagerUtilsInterface;
import expo.modules.location.LocationHelpers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes4.dex */
public class GeofencingTaskConsumer extends TaskConsumer implements TaskConsumerInterface {
    private static final String TAG = "GeofencingTaskConsumer";
    public static int VERSION = 1;
    private GeofencingClient mGeofencingClient;
    private List<Geofence> mGeofencingList;
    private GeofencingRequest mGeofencingRequest;
    private PendingIntent mPendingIntent;
    private Map<String, PersistableBundle> mRegions;
    private TaskInterface mTask;

    private int eventTypeFromTransitionType(int r2) {
        int r0 = 1;
        if (r2 != 1) {
            r0 = 2;
            if (r2 != 2) {
                return 0;
            }
        }
        return r0;
    }

    private static String getErrorString(int r0) {
        switch (r0) {
            case 1000:
                return "Geofencing not available.";
            case 1001:
                return "Too many geofences.";
            case 1002:
                return "Too many pending intents.";
            default:
                return "Unknown geofencing error.";
        }
    }

    private int regionStateForTransitionType(int r3) {
        if (r3 != 1) {
            if (r3 == 2) {
                return 2;
            }
            if (r3 != 4) {
                return 0;
            }
        }
        return 1;
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumerInterface
    public String taskType() {
        return "geofencing";
    }

    public GeofencingTaskConsumer(Context context, TaskManagerUtilsInterface taskManagerUtilsInterface) {
        super(context, taskManagerUtilsInterface);
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void didRegister(TaskInterface taskInterface) {
        if (taskInterface == null) {
            return;
        }
        this.mTask = taskInterface;
        startGeofencing();
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void didUnregister() {
        stopGeofencing();
        this.mTask = null;
        this.mPendingIntent = null;
        this.mGeofencingClient = null;
        this.mGeofencingRequest = null;
        this.mGeofencingList = null;
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumer, expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void setOptions(Map<String, Object> map) {
        super.setOptions(map);
        stopGeofencing();
        startGeofencing();
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumer, expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void didReceiveBroadcast(Intent intent) {
        GeofencingEvent fromIntent = GeofencingEvent.fromIntent(intent);
        if (fromIntent.hasError()) {
            this.mTask.execute(null, new Error(getErrorString(fromIntent.getErrorCode())));
            return;
        }
        int geofenceTransition = fromIntent.getGeofenceTransition();
        int regionStateForTransitionType = regionStateForTransitionType(geofenceTransition);
        int eventTypeFromTransitionType = eventTypeFromTransitionType(geofenceTransition);
        for (Geofence geofence : fromIntent.getTriggeringGeofences()) {
            PersistableBundle persistableBundle = this.mRegions.get(geofence.getRequestId());
            if (persistableBundle != null) {
                PersistableBundle persistableBundle2 = new PersistableBundle();
                persistableBundle.putInt("state", regionStateForTransitionType);
                persistableBundle2.putInt("eventType", eventTypeFromTransitionType);
                persistableBundle2.putPersistableBundle("region", persistableBundle);
                getTaskManagerUtils().scheduleJob(getContext().getApplicationContext(), this.mTask, Collections.singletonList(persistableBundle2));
            }
        }
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumer, expo.modules.interfaces.taskManager.TaskConsumerInterface
    public boolean didExecuteJob(final JobService jobService, final JobParameters jobParameters) {
        if (this.mTask == null) {
            return false;
        }
        for (PersistableBundle persistableBundle : getTaskManagerUtils().extractDataFromJobParams(jobParameters)) {
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            bundle2.putAll(persistableBundle.getPersistableBundle("region"));
            bundle.putInt("eventType", persistableBundle.getInt("eventType"));
            bundle.putBundle("region", bundle2);
            TaskInterface taskInterface = this.mTask;
            if (taskInterface == null) {
                return false;
            }
            taskInterface.execute(bundle, null, new TaskExecutionCallback() { // from class: expo.modules.location.taskConsumers.GeofencingTaskConsumer.1
                @Override // expo.modules.interfaces.taskManager.TaskExecutionCallback
                public void onFinished(Map<String, Object> map) {
                    jobService.jobFinished(jobParameters, false);
                }
            });
        }
        return true;
    }

    private void startGeofencing() {
        Context context = getContext();
        if (context == null) {
            Log.w(TAG, "The context has been abandoned.");
        } else if (!LocationHelpers.isAnyProviderAvailable(context)) {
            Log.w(TAG, "There is no location provider available.");
        } else {
            this.mRegions = new HashMap();
            this.mGeofencingList = new ArrayList();
            for (Map<String, Object> map : (ArrayList) this.mTask.getOptions().get("regions")) {
                Geofence geofenceFromRegion = geofenceFromRegion(map);
                String requestId = geofenceFromRegion.getRequestId();
                this.mRegions.put(requestId, bundleFromRegion(requestId, map));
                this.mGeofencingList.add(geofenceFromRegion);
            }
            this.mPendingIntent = preparePendingIntent();
            this.mGeofencingRequest = prepareGeofencingRequest(this.mGeofencingList);
            GeofencingClient geofencingClient = LocationServices.getGeofencingClient(getContext());
            this.mGeofencingClient = geofencingClient;
            try {
                geofencingClient.addGeofences(this.mGeofencingRequest, this.mPendingIntent);
            } catch (SecurityException e) {
                Log.w(TAG, "Geofencing request has been rejected.", e);
            }
        }
    }

    private void stopGeofencing() {
        PendingIntent pendingIntent;
        GeofencingClient geofencingClient = this.mGeofencingClient;
        if (geofencingClient == null || (pendingIntent = this.mPendingIntent) == null) {
            return;
        }
        geofencingClient.removeGeofences(pendingIntent);
        this.mPendingIntent.cancel();
    }

    private GeofencingRequest prepareGeofencingRequest(List<Geofence> list) {
        return new GeofencingRequest.Builder().setInitialTrigger(3).addGeofences(list).build();
    }

    private PendingIntent preparePendingIntent() {
        return getTaskManagerUtils().createTaskIntent(getContext(), this.mTask);
    }

    private Geofence geofenceFromRegion(Map<String, Object> map) {
        String str = map.containsKey("identifier") ? (String) map.get("identifier") : UUID.randomUUID().toString();
        double doubleFromObject = doubleFromObject(map.get("latitude"));
        double doubleFromObject2 = doubleFromObject(map.get("longitude"));
        double doubleFromObject3 = doubleFromObject(map.get("radius"));
        boolean z = true;
        int r7 = (!map.containsKey("notifyOnEnter") || ((Boolean) map.get("notifyOnEnter")).booleanValue()) ? 1 : 0;
        if (map.containsKey("notifyOnExit") && !((Boolean) map.get("notifyOnExit")).booleanValue()) {
            z = false;
        }
        return new Geofence.Builder().setRequestId(str).setCircularRegion(doubleFromObject, doubleFromObject2, (float) doubleFromObject3).setExpirationDuration(-1L).setTransitionTypes(r7 | (z ? 2 : 0)).build();
    }

    private PersistableBundle bundleFromRegion(String str, Map<String, Object> map) {
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("identifier", str);
        persistableBundle.putDouble("radius", doubleFromObject(map.get("radius")));
        persistableBundle.putDouble("latitude", doubleFromObject(map.get("latitude")));
        persistableBundle.putDouble("longitude", doubleFromObject(map.get("longitude")));
        persistableBundle.putInt("state", 0);
        return persistableBundle;
    }

    private static double doubleFromObject(Object obj) {
        if (obj instanceof Integer) {
            return ((Integer) obj).doubleValue();
        }
        return ((Double) obj).doubleValue();
    }
}
