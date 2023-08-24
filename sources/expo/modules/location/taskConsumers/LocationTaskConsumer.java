package expo.modules.location.taskConsumers;

import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.util.Log;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import expo.modules.core.MapHelper;
import expo.modules.core.arguments.MapArguments;
import expo.modules.core.interfaces.LifecycleEventListener;
import expo.modules.interfaces.taskManager.TaskConsumer;
import expo.modules.interfaces.taskManager.TaskConsumerInterface;
import expo.modules.interfaces.taskManager.TaskExecutionCallback;
import expo.modules.interfaces.taskManager.TaskInterface;
import expo.modules.interfaces.taskManager.TaskManagerUtilsInterface;
import expo.modules.location.LocationHelpers;
import expo.modules.location.services.LocationTaskService;
import io.nlopez.smartlocation.geofencing.providers.GeofencingGooglePlayServicesProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class LocationTaskConsumer extends TaskConsumer implements TaskConsumerInterface, LifecycleEventListener {
    private static final String FOREGROUND_SERVICE_KEY = "foregroundService";
    private static final String TAG = "LocationTaskConsumer";
    public static int VERSION = 1;
    private static long sLastTimestamp;
    private double mDeferredDistance;
    private List<Location> mDeferredLocations;
    private boolean mIsHostPaused;
    private Location mLastReportedLocation;
    private FusedLocationProviderClient mLocationClient;
    private LocationRequest mLocationRequest;
    private PendingIntent mPendingIntent;
    private LocationTaskService mService;
    private TaskInterface mTask;

    @Override // expo.modules.interfaces.taskManager.TaskConsumerInterface
    public String taskType() {
        return GeofencingGooglePlayServicesProvider.LOCATION_EXTRA_ID;
    }

    public LocationTaskConsumer(Context context, TaskManagerUtilsInterface taskManagerUtilsInterface) {
        super(context, taskManagerUtilsInterface);
        this.mDeferredDistance = 0.0d;
        this.mDeferredLocations = new ArrayList();
        this.mIsHostPaused = true;
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void didRegister(TaskInterface taskInterface) {
        this.mTask = taskInterface;
        startLocationUpdates();
        maybeStartForegroundService();
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void didUnregister() {
        stopLocationUpdates();
        stopForegroundService();
        this.mTask = null;
        this.mPendingIntent = null;
        this.mLocationRequest = null;
        this.mLocationClient = null;
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumer, expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void setOptions(Map<String, Object> map) {
        super.setOptions(map);
        stopLocationUpdates();
        startLocationUpdates();
        maybeStartForegroundService();
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumer, expo.modules.interfaces.taskManager.TaskConsumerInterface
    public void didReceiveBroadcast(Intent intent) {
        if (this.mTask == null) {
            return;
        }
        LocationResult extractResult = LocationResult.extractResult(intent);
        if (extractResult != null) {
            deferLocations(extractResult.getLocations());
            maybeReportDeferredLocations();
            return;
        }
        try {
            FusedLocationProviderClient fusedLocationProviderClient = this.mLocationClient;
            if (fusedLocationProviderClient == null) {
                Log.w(TAG, "LocationClient is null.");
            } else {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() { // from class: expo.modules.location.taskConsumers.LocationTaskConsumer.1
                    @Override // com.google.android.gms.tasks.OnCompleteListener
                    public void onComplete(Task<Location> task) {
                        Location result = task.getResult();
                        if (result != null) {
                            Log.i(LocationTaskConsumer.TAG, "get last location: " + result);
                            LocationTaskConsumer.this.deferLocations(Collections.singletonList(result));
                            LocationTaskConsumer.this.maybeReportDeferredLocations();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "Cannot get last location: " + e.getMessage());
        }
    }

    @Override // expo.modules.interfaces.taskManager.TaskConsumer, expo.modules.interfaces.taskManager.TaskConsumerInterface
    public boolean didExecuteJob(final JobService jobService, final JobParameters jobParameters) {
        List<PersistableBundle> extractDataFromJobParams = getTaskManagerUtils().extractDataFromJobParams(jobParameters);
        ArrayList<Bundle> arrayList = new ArrayList<>();
        for (PersistableBundle persistableBundle : extractDataFromJobParams) {
            Bundle bundle = new Bundle();
            Bundle bundle2 = new Bundle();
            if (persistableBundle != null) {
                bundle2.putAll(persistableBundle.getPersistableBundle("coords"));
                bundle.putAll(persistableBundle);
                bundle.putBundle("coords", bundle2);
                arrayList.add(bundle);
            }
        }
        executeTaskWithLocationBundles(arrayList, new TaskExecutionCallback() { // from class: expo.modules.location.taskConsumers.LocationTaskConsumer.2
            @Override // expo.modules.interfaces.taskManager.TaskExecutionCallback
            public void onFinished(Map<String, Object> map) {
                jobService.jobFinished(jobParameters, false);
            }
        });
        return true;
    }

    public static boolean shouldUseForegroundService(Map<String, Object> map) {
        return map.containsKey(FOREGROUND_SERVICE_KEY);
    }

    private void startLocationUpdates() {
        Context context = getContext();
        if (context == null) {
            Log.w(TAG, "The context has been abandoned.");
        } else if (!LocationHelpers.isAnyProviderAvailable(context)) {
            Log.w(TAG, "There is no location provider available.");
        } else {
            this.mLocationRequest = LocationHelpers.prepareLocationRequest(this.mTask.getOptions());
            this.mPendingIntent = preparePendingIntent();
            try {
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
                this.mLocationClient = fusedLocationProviderClient;
                fusedLocationProviderClient.requestLocationUpdates(this.mLocationRequest, this.mPendingIntent);
            } catch (SecurityException e) {
                Log.w(TAG, "Location request has been rejected.", e);
            }
        }
    }

    private void stopLocationUpdates() {
        PendingIntent pendingIntent;
        FusedLocationProviderClient fusedLocationProviderClient = this.mLocationClient;
        if (fusedLocationProviderClient == null || (pendingIntent = this.mPendingIntent) == null) {
            return;
        }
        fusedLocationProviderClient.removeLocationUpdates(pendingIntent);
        this.mPendingIntent.cancel();
    }

    private void maybeStartForegroundService() {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        MapArguments mapArguments = new MapArguments(this.mTask.getOptions());
        final Context context = getContext();
        boolean shouldUseForegroundService = shouldUseForegroundService(this.mTask.getOptions());
        if (context == null) {
            Log.w(TAG, "Context not found when trying to start foreground service.");
            return;
        }
        LocationTaskService locationTaskService = this.mService;
        if (locationTaskService != null && !shouldUseForegroundService) {
            stopForegroundService();
        } else if (shouldUseForegroundService) {
            if (locationTaskService == null) {
                Intent intent = new Intent(context, LocationTaskService.class);
                Bundle bundle = new Bundle();
                final Bundle bundle2 = mapArguments.getArguments(FOREGROUND_SERVICE_KEY).toBundle();
                bundle.putString("appId", this.mTask.getAppScopeKey());
                bundle.putString("taskName", this.mTask.getName());
                bundle.putBoolean("killService", bundle2.getBoolean("killServiceOnDestroy", false));
                intent.putExtras(bundle);
                context.startForegroundService(intent);
                context.bindService(intent, new ServiceConnection() { // from class: expo.modules.location.taskConsumers.LocationTaskConsumer.3
                    @Override // android.content.ServiceConnection
                    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                        LocationTaskConsumer.this.mService = ((LocationTaskService.ServiceBinder) iBinder).getService();
                        LocationTaskConsumer.this.mService.setParentContext(context);
                        LocationTaskConsumer.this.mService.startForeground(bundle2);
                    }

                    @Override // android.content.ServiceConnection
                    public void onServiceDisconnected(ComponentName componentName) {
                        LocationTaskConsumer.this.mService.stop();
                        LocationTaskConsumer.this.mService = null;
                    }
                }, 1);
                return;
            }
            locationTaskService.startForeground(mapArguments.getArguments(FOREGROUND_SERVICE_KEY).toBundle());
        }
    }

    private void stopForegroundService() {
        LocationTaskService locationTaskService = this.mService;
        if (locationTaskService != null) {
            locationTaskService.stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deferLocations(List<Location> list) {
        int size = this.mDeferredLocations.size();
        Location location = size > 0 ? this.mDeferredLocations.get(size - 1) : this.mLastReportedLocation;
        for (Location location2 : list) {
            if (location != null) {
                this.mDeferredDistance += Math.abs(location2.distanceTo(location));
            }
            location = location2;
        }
        this.mDeferredLocations.addAll(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeReportDeferredLocations() {
        if (shouldReportDeferredLocations()) {
            Context applicationContext = getContext().getApplicationContext();
            ArrayList arrayList = new ArrayList();
            for (Location location : this.mDeferredLocations) {
                long time = location.getTime();
                if (time > sLastTimestamp) {
                    arrayList.add((PersistableBundle) LocationHelpers.locationToBundle(location, PersistableBundle.class));
                    sLastTimestamp = time;
                }
            }
            if (arrayList.size() > 0) {
                List<Location> list = this.mDeferredLocations;
                this.mLastReportedLocation = list.get(list.size() - 1);
                this.mDeferredDistance = 0.0d;
                this.mDeferredLocations.clear();
                getTaskManagerUtils().scheduleJob(applicationContext, this.mTask, arrayList);
            }
        }
    }

    private boolean shouldReportDeferredLocations() {
        if (this.mDeferredLocations.size() == 0 || this.mTask == null) {
            return false;
        }
        if (this.mIsHostPaused) {
            Location location = this.mLastReportedLocation;
            if (location == null) {
                location = this.mDeferredLocations.get(0);
            }
            List<Location> list = this.mDeferredLocations;
            MapHelper mapHelper = new MapHelper(this.mTask.getOptions());
            return list.get(list.size() - 1).getTime() - location.getTime() >= mapHelper.getLong("deferredUpdatesInterval") && this.mDeferredDistance >= mapHelper.getDouble("deferredUpdatesDistance");
        }
        return true;
    }

    private PendingIntent preparePendingIntent() {
        return getTaskManagerUtils().createTaskIntent(getContext(), this.mTask);
    }

    private void executeTaskWithLocationBundles(ArrayList<Bundle> arrayList, TaskExecutionCallback taskExecutionCallback) {
        if (arrayList.size() > 0 && this.mTask != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("locations", arrayList);
            this.mTask.execute(bundle, null, taskExecutionCallback);
            return;
        }
        taskExecutionCallback.onFinished(null);
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostResume() {
        this.mIsHostPaused = false;
        maybeReportDeferredLocations();
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostPause() {
        this.mIsHostPaused = true;
    }

    @Override // expo.modules.core.interfaces.LifecycleEventListener
    public void onHostDestroy() {
        this.mIsHostPaused = true;
    }
}
