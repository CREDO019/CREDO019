package expo.modules.sensors.modules;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Choreographer;
import android.view.WindowManager;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.core.ExportedModule;
import expo.modules.core.ModuleRegistry;
import expo.modules.core.Promise;
import expo.modules.core.interfaces.ExpoMethod;
import expo.modules.core.interfaces.services.EventEmitter;
import expo.modules.core.interfaces.services.UIManager;
import expo.modules.interfaces.sensors.SensorServiceInterface;
import expo.modules.interfaces.sensors.SensorServiceSubscriptionInterface;
import expo.modules.interfaces.sensors.services.AccelerometerServiceInterface;
import expo.modules.interfaces.sensors.services.GravitySensorServiceInterface;
import expo.modules.interfaces.sensors.services.GyroscopeServiceInterface;
import expo.modules.interfaces.sensors.services.LinearAccelerationSensorServiceInterface;
import expo.modules.interfaces.sensors.services.RotationVectorSensorServiceInterface;
import expo.modules.sensors.modules.DeviceMotionModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Tuples;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceMotionModule.kt */
@Metadata(m184d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u00012\u00020\u0002:\u0002=>B\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010 \u001a\u00020!H\u0002J\u0014\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#H\u0016J\b\u0010&\u001a\u00020$H\u0016J\b\u0010'\u001a\u00020(H\u0002J\u000e\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0007J\u0018\u00100\u001a\u00020-2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020(H\u0016J\u0010\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020\u0013H\u0016J\u0010\u00106\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0016J\u0010\u00107\u001a\u00020-2\u0006\u00108\u001a\u00020\u0007H\u0016J\u0018\u00109\u001a\u00020-2\u0006\u0010:\u001a\u00020(2\u0006\u0010.\u001a\u00020/H\u0007J\u0010\u0010;\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0007J\u0010\u0010<\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0007R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m183d2 = {"Lexpo/modules/sensors/modules/DeviceMotionModule;", "Lexpo/modules/core/ExportedModule;", "Landroid/hardware/SensorEventListener2;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mAccelerationEvent", "Landroid/hardware/SensorEvent;", "mAccelerationIncludingGravityEvent", "mCurrentFrameCallback", "Lexpo/modules/sensors/modules/DeviceMotionModule$ScheduleDispatchFrameCallback;", "mDispatchEventRunnable", "Lexpo/modules/sensors/modules/DeviceMotionModule$DispatchEventRunnable;", "mEventEmitter", "Lexpo/modules/core/interfaces/services/EventEmitter;", "mGravityEvent", "mLastUpdate", "", "mModuleRegistry", "Lexpo/modules/core/ModuleRegistry;", "mRotationEvent", "mRotationMatrix", "", "mRotationRateEvent", "mRotationResult", "mServiceSubscriptions", "", "Lexpo/modules/interfaces/sensors/SensorServiceSubscriptionInterface;", "mUIManager", "Lexpo/modules/core/interfaces/services/UIManager;", "mUpdateInterval", "", "eventsToMap", "Landroid/os/Bundle;", "getConstants", "", "", "", "getName", "getOrientation", "", "getSensorKernelServices", "", "Lexpo/modules/interfaces/sensors/SensorServiceInterface;", "isAvailableAsync", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lexpo/modules/core/Promise;", "onAccuracyChanged", "sensor", "Landroid/hardware/Sensor;", "accuracy", "onCreate", "moduleRegistry", "onFlushCompleted", "onSensorChanged", "sensorEvent", "setUpdateInterval", "updateInterval", "startObserving", "stopObserving", "DispatchEventRunnable", "ScheduleDispatchFrameCallback", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DeviceMotionModule extends ExportedModule implements SensorEventListener2 {
    private SensorEvent mAccelerationEvent;
    private SensorEvent mAccelerationIncludingGravityEvent;
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback;
    private final DispatchEventRunnable mDispatchEventRunnable;
    private EventEmitter mEventEmitter;
    private SensorEvent mGravityEvent;
    private long mLastUpdate;
    private ModuleRegistry mModuleRegistry;
    private SensorEvent mRotationEvent;
    private final float[] mRotationMatrix;
    private SensorEvent mRotationRateEvent;
    private final float[] mRotationResult;
    private List<SensorServiceSubscriptionInterface> mServiceSubscriptions;
    private UIManager mUIManager;
    private float mUpdateInterval;

    @Override // expo.modules.core.ExportedModule
    public String getName() {
        return "ExponentDeviceMotion";
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int r2) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    @Override // android.hardware.SensorEventListener2
    public void onFlushCompleted(Sensor sensor) {
        Intrinsics.checkNotNullParameter(sensor, "sensor");
    }

    public DeviceMotionModule(Context context) {
        super(context);
        this.mUpdateInterval = 0.016666668f;
        this.mRotationMatrix = new float[9];
        this.mRotationResult = new float[3];
        this.mCurrentFrameCallback = new ScheduleDispatchFrameCallback(this);
        this.mDispatchEventRunnable = new DispatchEventRunnable(this);
    }

    @Override // expo.modules.core.ExportedModule
    public Map<String, Object> getConstants() {
        return MapsKt.mapOf(new Tuples("Gravity", Double.valueOf(9.80665d)));
    }

    @ExpoMethod
    public final void setUpdateInterval(int r2, Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        this.mUpdateInterval = r2;
        promise.resolve(null);
    }

    @ExpoMethod
    public final void startObserving(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (this.mServiceSubscriptions == null) {
            this.mServiceSubscriptions = new ArrayList();
            for (SensorServiceInterface sensorServiceInterface : getSensorKernelServices()) {
                SensorServiceSubscriptionInterface subscription = sensorServiceInterface.createSubscriptionForListener(this);
                subscription.setUpdateInterval(0L);
                List<SensorServiceSubscriptionInterface> list = this.mServiceSubscriptions;
                if (list == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mServiceSubscriptions");
                    list = null;
                }
                Intrinsics.checkNotNullExpressionValue(subscription, "subscription");
                list.add(subscription);
            }
        }
        List<SensorServiceSubscriptionInterface> list2 = this.mServiceSubscriptions;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mServiceSubscriptions");
            list2 = null;
        }
        for (SensorServiceSubscriptionInterface sensorServiceSubscriptionInterface : list2) {
            sensorServiceSubscriptionInterface.start();
        }
        promise.resolve(null);
    }

    @ExpoMethod
    public final void stopObserving(final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        UIManager uIManager = this.mUIManager;
        if (uIManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUIManager");
            uIManager = null;
        }
        uIManager.runOnUiQueueThread(new Runnable() { // from class: expo.modules.sensors.modules.DeviceMotionModule$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DeviceMotionModule.m1698stopObserving$lambda2(DeviceMotionModule.this, promise);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: stopObserving$lambda-2  reason: not valid java name */
    public static final void m1698stopObserving$lambda2(DeviceMotionModule this$0, Promise promise) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(promise, "$promise");
        List<SensorServiceSubscriptionInterface> list = this$0.mServiceSubscriptions;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mServiceSubscriptions");
            list = null;
        }
        for (SensorServiceSubscriptionInterface sensorServiceSubscriptionInterface : list) {
            sensorServiceSubscriptionInterface.stop();
        }
        this$0.mCurrentFrameCallback.stop();
        promise.resolve(null);
    }

    @ExpoMethod
    public final void isAvailableAsync(Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        Object systemService = getContext().getSystemService("sensor");
        Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.hardware.SensorManager");
        SensorManager sensorManager = (SensorManager) systemService;
        Iterator it = CollectionsKt.arrayListOf(4, 1, 10, 11, 9).iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            Intrinsics.checkNotNull(num);
            Intrinsics.checkNotNullExpressionValue(num, "type!!");
            if (sensorManager.getDefaultSensor(num.intValue()) == null) {
                promise.resolve(false);
                return;
            }
        }
        promise.resolve(true);
    }

    @Override // expo.modules.core.ExportedModule, expo.modules.core.interfaces.RegistryLifecycleListener
    public void onCreate(ModuleRegistry moduleRegistry) {
        Intrinsics.checkNotNullParameter(moduleRegistry, "moduleRegistry");
        Object module = moduleRegistry.getModule(EventEmitter.class);
        Intrinsics.checkNotNullExpressionValue(module, "moduleRegistry.getModule(EventEmitter::class.java)");
        this.mEventEmitter = (EventEmitter) module;
        Object module2 = moduleRegistry.getModule(UIManager.class);
        Intrinsics.checkNotNullExpressionValue(module2, "moduleRegistry.getModule(UIManager::class.java)");
        this.mUIManager = (UIManager) module2;
        this.mModuleRegistry = moduleRegistry;
    }

    private final List<SensorServiceInterface> getSensorKernelServices() {
        SensorServiceInterface[] sensorServiceInterfaceArr = new SensorServiceInterface[5];
        ModuleRegistry moduleRegistry = this.mModuleRegistry;
        ModuleRegistry moduleRegistry2 = null;
        if (moduleRegistry == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry = null;
        }
        sensorServiceInterfaceArr[0] = (SensorServiceInterface) moduleRegistry.getModule(GyroscopeServiceInterface.class);
        ModuleRegistry moduleRegistry3 = this.mModuleRegistry;
        if (moduleRegistry3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry3 = null;
        }
        sensorServiceInterfaceArr[1] = (SensorServiceInterface) moduleRegistry3.getModule(LinearAccelerationSensorServiceInterface.class);
        ModuleRegistry moduleRegistry4 = this.mModuleRegistry;
        if (moduleRegistry4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry4 = null;
        }
        sensorServiceInterfaceArr[2] = (SensorServiceInterface) moduleRegistry4.getModule(AccelerometerServiceInterface.class);
        ModuleRegistry moduleRegistry5 = this.mModuleRegistry;
        if (moduleRegistry5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
            moduleRegistry5 = null;
        }
        sensorServiceInterfaceArr[3] = (SensorServiceInterface) moduleRegistry5.getModule(RotationVectorSensorServiceInterface.class);
        ModuleRegistry moduleRegistry6 = this.mModuleRegistry;
        if (moduleRegistry6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mModuleRegistry");
        } else {
            moduleRegistry2 = moduleRegistry6;
        }
        sensorServiceInterfaceArr[4] = (SensorServiceInterface) moduleRegistry2.getModule(GravitySensorServiceInterface.class);
        return CollectionsKt.arrayListOf(sensorServiceInterfaceArr);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        Intrinsics.checkNotNullParameter(sensorEvent, "sensorEvent");
        int type = sensorEvent.sensor.getType();
        if (type == 1) {
            this.mAccelerationIncludingGravityEvent = sensorEvent;
        } else if (type == 4) {
            this.mRotationRateEvent = sensorEvent;
        } else {
            switch (type) {
                case 9:
                    this.mGravityEvent = sensorEvent;
                    break;
                case 10:
                    this.mAccelerationEvent = sensorEvent;
                    break;
                case 11:
                    this.mRotationEvent = sensorEvent;
                    break;
            }
        }
        this.mCurrentFrameCallback.maybePostFromNonUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeviceMotionModule.kt */
    @Metadata(m184d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0006\u0010\n\u001a\u00020\u0007J\u0006\u0010\u000b\u001a\u00020\u0007J\b\u0010\f\u001a\u00020\u0007H\u0002J\u0006\u0010\r\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m183d2 = {"Lexpo/modules/sensors/modules/DeviceMotionModule$ScheduleDispatchFrameCallback;", "Landroid/view/Choreographer$FrameCallback;", "(Lexpo/modules/sensors/modules/DeviceMotionModule;)V", "mIsPosted", "", "mShouldStop", "doFrame", "", "frameTimeNanos", "", "maybePost", "maybePostFromNonUI", "post", "stop", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public final class ScheduleDispatchFrameCallback implements Choreographer.FrameCallback {
        private volatile boolean mIsPosted;
        private boolean mShouldStop;
        final /* synthetic */ DeviceMotionModule this$0;

        public ScheduleDispatchFrameCallback(DeviceMotionModule this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            if (this.mShouldStop) {
                this.mIsPosted = false;
            } else {
                post();
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (((float) (currentTimeMillis - this.this$0.mLastUpdate)) > this.this$0.mUpdateInterval) {
                UIManager uIManager = this.this$0.mUIManager;
                if (uIManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mUIManager");
                    uIManager = null;
                }
                uIManager.runOnClientCodeQueueThread(this.this$0.mDispatchEventRunnable);
                this.this$0.mLastUpdate = currentTimeMillis;
            }
        }

        public final void stop() {
            this.mShouldStop = true;
        }

        public final void maybePost() {
            if (this.mIsPosted) {
                return;
            }
            this.mIsPosted = true;
            post();
        }

        private final void post() {
            Choreographer.getInstance().postFrameCallback(this.this$0.mCurrentFrameCallback);
        }

        public final void maybePostFromNonUI() {
            if (this.mIsPosted) {
                return;
            }
            UIManager uIManager = this.this$0.mUIManager;
            if (uIManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUIManager");
                uIManager = null;
            }
            uIManager.runOnUiQueueThread(new Runnable() { // from class: expo.modules.sensors.modules.DeviceMotionModule$ScheduleDispatchFrameCallback$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DeviceMotionModule.ScheduleDispatchFrameCallback.m1699maybePostFromNonUI$lambda0(DeviceMotionModule.ScheduleDispatchFrameCallback.this);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: maybePostFromNonUI$lambda-0  reason: not valid java name */
        public static final void m1699maybePostFromNonUI$lambda0(ScheduleDispatchFrameCallback this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.maybePost();
        }
    }

    /* compiled from: DeviceMotionModule.kt */
    @Metadata(m184d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, m183d2 = {"Lexpo/modules/sensors/modules/DeviceMotionModule$DispatchEventRunnable;", "Ljava/lang/Runnable;", "(Lexpo/modules/sensors/modules/DeviceMotionModule;)V", "run", "", "expo-sensors_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    private final class DispatchEventRunnable implements Runnable {
        final /* synthetic */ DeviceMotionModule this$0;

        public DispatchEventRunnable(DeviceMotionModule this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // java.lang.Runnable
        public void run() {
            EventEmitter eventEmitter = this.this$0.mEventEmitter;
            if (eventEmitter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEventEmitter");
                eventEmitter = null;
            }
            eventEmitter.emit("deviceMotionDidUpdate", this.this$0.eventsToMap());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle eventsToMap() {
        double d;
        SensorEvent sensorEvent;
        SensorEvent sensorEvent2;
        SensorEvent sensorEvent3;
        SensorEvent sensorEvent4;
        SensorEvent sensorEvent5;
        SensorEvent sensorEvent6;
        SensorEvent sensorEvent7;
        SensorEvent sensorEvent8;
        SensorEvent sensorEvent9;
        Bundle bundle = new Bundle();
        if (this.mAccelerationEvent != null) {
            Bundle bundle2 = new Bundle();
            Intrinsics.checkNotNull(this.mAccelerationEvent);
            bundle2.putDouble("x", sensorEvent7.values[0]);
            Intrinsics.checkNotNull(this.mAccelerationEvent);
            bundle2.putDouble("y", sensorEvent8.values[1]);
            Intrinsics.checkNotNull(this.mAccelerationEvent);
            bundle2.putDouble("z", sensorEvent9.values[2]);
            Unit unit = Unit.INSTANCE;
            bundle.putBundle("acceleration", bundle2);
            SensorEvent sensorEvent10 = this.mAccelerationEvent;
            Intrinsics.checkNotNull(sensorEvent10);
            d = sensorEvent10.timestamp;
        } else {
            d = 0.0d;
        }
        if (this.mAccelerationIncludingGravityEvent != null && this.mGravityEvent != null) {
            Bundle bundle3 = new Bundle();
            SensorEvent sensorEvent11 = this.mAccelerationIncludingGravityEvent;
            Intrinsics.checkNotNull(sensorEvent11);
            float f = sensorEvent11.values[0];
            float f2 = 2;
            Intrinsics.checkNotNull(this.mGravityEvent);
            bundle3.putDouble("x", f - (sensorEvent4.values[0] * f2));
            SensorEvent sensorEvent12 = this.mAccelerationIncludingGravityEvent;
            Intrinsics.checkNotNull(sensorEvent12);
            float f3 = sensorEvent12.values[1];
            Intrinsics.checkNotNull(this.mGravityEvent);
            bundle3.putDouble("y", f3 - (sensorEvent5.values[1] * f2));
            SensorEvent sensorEvent13 = this.mAccelerationIncludingGravityEvent;
            Intrinsics.checkNotNull(sensorEvent13);
            float f4 = sensorEvent13.values[2];
            Intrinsics.checkNotNull(this.mGravityEvent);
            bundle3.putDouble("z", f4 - (f2 * sensorEvent6.values[2]));
            Unit unit2 = Unit.INSTANCE;
            bundle.putBundle("accelerationIncludingGravity", bundle3);
            SensorEvent sensorEvent14 = this.mAccelerationIncludingGravityEvent;
            Intrinsics.checkNotNull(sensorEvent14);
            d = sensorEvent14.timestamp;
        }
        if (this.mRotationRateEvent != null) {
            Bundle bundle4 = new Bundle();
            Intrinsics.checkNotNull(this.mRotationRateEvent);
            bundle4.putDouble("alpha", Math.toDegrees(sensorEvent.values[0]));
            Intrinsics.checkNotNull(this.mRotationRateEvent);
            bundle4.putDouble("beta", Math.toDegrees(sensorEvent2.values[1]));
            Intrinsics.checkNotNull(this.mRotationRateEvent);
            bundle4.putDouble("gamma", Math.toDegrees(sensorEvent3.values[2]));
            Unit unit3 = Unit.INSTANCE;
            bundle.putBundle("rotationRate", bundle4);
            SensorEvent sensorEvent15 = this.mRotationRateEvent;
            Intrinsics.checkNotNull(sensorEvent15);
            d = sensorEvent15.timestamp;
        }
        SensorEvent sensorEvent16 = this.mRotationEvent;
        if (sensorEvent16 != null) {
            float[] fArr = this.mRotationMatrix;
            Intrinsics.checkNotNull(sensorEvent16);
            SensorManager.getRotationMatrixFromVector(fArr, sensorEvent16.values);
            SensorManager.getOrientation(this.mRotationMatrix, this.mRotationResult);
            Bundle bundle5 = new Bundle();
            bundle5.putDouble("alpha", -this.mRotationResult[0]);
            bundle5.putDouble("beta", -this.mRotationResult[1]);
            bundle5.putDouble("gamma", this.mRotationResult[2]);
            Unit unit4 = Unit.INSTANCE;
            bundle.putBundle(ViewProps.ROTATION, bundle5);
            SensorEvent sensorEvent17 = this.mRotationEvent;
            Intrinsics.checkNotNull(sensorEvent17);
            d = sensorEvent17.timestamp;
        }
        bundle.putDouble("interval", d);
        bundle.putInt("orientation", getOrientation());
        return bundle;
    }

    private final int getOrientation() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        if (windowManager != null) {
            int rotation = windowManager.getDefaultDisplay().getRotation();
            if (rotation != 1) {
                return rotation != 2 ? rotation != 3 ? 0 : -90 : RotationOptions.ROTATE_180;
            }
            return 90;
        }
        return 0;
    }
}
