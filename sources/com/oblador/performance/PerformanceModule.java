package com.oblador.performance;

import android.os.Process;
import android.os.SystemClock;
import android.system.Os;
import android.system.OsConstants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class PerformanceModule extends ReactContextBaseJavaModule {
    public static final String BRIDGE_SETUP_START = "bridgeSetupStart";
    public static final String PERFORMANCE_MODULE = "RNPerformanceManager";
    private boolean eventsBuffered;
    private static final long MODULE_INITIALIZED_AT = SystemClock.uptimeMillis();
    private static Map<String, Long> markBuffer = new HashMap();

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return PERFORMANCE_MODULE;
    }

    public PerformanceModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.eventsBuffered = true;
        setupMarkerListener();
    }

    public static void setupListener() {
        ReactMarker.addListener(new ReactMarker.MarkerListener() { // from class: com.oblador.performance.PerformanceModule$$ExternalSyntheticLambda1
            @Override // com.facebook.react.bridge.ReactMarker.MarkerListener
            public final void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int r3) {
                PerformanceModule.lambda$setupListener$0(reactMarkerConstants, str, r3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setupListener$0(ReactMarkerConstants reactMarkerConstants, String str, int r3) {
        switch (C34781.$SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[reactMarkerConstants.ordinal()]) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
                markBuffer.put(getMarkName(reactMarkerConstants), Long.valueOf(SystemClock.uptimeMillis()));
                return;
            case 2:
                markBuffer.clear();
                markBuffer.put(BRIDGE_SETUP_START, Long.valueOf(SystemClock.uptimeMillis()));
                return;
            default:
                return;
        }
    }

    private static String getMarkName(ReactMarkerConstants reactMarkerConstants) {
        String[] split;
        StringBuffer stringBuffer = new StringBuffer();
        for (String str : reactMarkerConstants.toString().toLowerCase().split("_")) {
            if (stringBuffer.length() == 0) {
                stringBuffer.append(str);
            } else {
                stringBuffer.append(Character.toUpperCase(str.charAt(0)));
                if (str.length() > 1) {
                    stringBuffer.append(str.substring(1, str.length()));
                }
            }
        }
        return stringBuffer.toString();
    }

    private void emitNativeStartupTime() {
        try {
            safelyEmitMark("nativeLaunchStart", getStartTime(Process.myPid()) - (SystemClock.elapsedRealtime() - SystemClock.uptimeMillis()));
            safelyEmitMark("nativeLaunchEnd", MODULE_INITIALIZED_AT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupMarkerListener() {
        ReactMarker.addListener(new ReactMarker.MarkerListener() { // from class: com.oblador.performance.PerformanceModule$$ExternalSyntheticLambda0
            @Override // com.facebook.react.bridge.ReactMarker.MarkerListener
            public final void logMarker(ReactMarkerConstants reactMarkerConstants, String str, int r4) {
                PerformanceModule.this.m244xbde4759c(reactMarkerConstants, str, r4);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.oblador.performance.PerformanceModule$1 */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class C34781 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants;

        static {
            int[] r0 = new int[ReactMarkerConstants.values().length];
            $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants = r0;
            try {
                r0[ReactMarkerConstants.CONTENT_APPEARED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.RELOAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_END.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.ATTACH_MEASURED_ROOT_VIEWS_START.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_END.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.BUILD_NATIVE_MODULE_REGISTRY_START.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_CATALYST_INSTANCE_END.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_CATALYST_INSTANCE_START.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_REACT_CONTEXT_END.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_REACT_CONTEXT_START.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_END.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_CONSTANTS_START.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_START.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_VIEW_MANAGERS_END.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.CREATE_VIEW_MANAGERS_START.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.DOWNLOAD_END.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.DOWNLOAD_START.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_END.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.LOAD_REACT_NATIVE_SO_FILE_START.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.PRE_RUN_JS_BUNDLE_START.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_END.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.PRE_SETUP_REACT_CONTEXT_START.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_END.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.PROCESS_CORE_REACT_PACKAGE_START.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.REACT_CONTEXT_THREAD_END.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.REACT_CONTEXT_THREAD_START.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.RUN_JS_BUNDLE_END.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.RUN_JS_BUNDLE_START.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.SETUP_REACT_CONTEXT_END.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.SETUP_REACT_CONTEXT_START.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[ReactMarkerConstants.VM_INIT.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$setupMarkerListener$1$com-oblador-performance-PerformanceModule */
    public /* synthetic */ void m244xbde4759c(ReactMarkerConstants reactMarkerConstants, String str, int r3) {
        int r1 = C34781.$SwitchMap$com$facebook$react$bridge$ReactMarkerConstants[reactMarkerConstants.ordinal()];
        if (r1 != 1) {
            if (r1 != 2) {
                return;
            }
            this.eventsBuffered = true;
            return;
        }
        this.eventsBuffered = false;
        emitNativeStartupTime();
        emitBufferedMarks();
    }

    private void safelyEmitMark(String str, long j) {
        if (this.eventsBuffered) {
            markBuffer.put(str, Long.valueOf(j));
        } else {
            emitMark(str, j);
        }
    }

    private void emitBufferedMarks() {
        for (Map.Entry<String, Long> entry : markBuffer.entrySet()) {
            emitMark(entry.getKey(), entry.getValue().longValue());
        }
    }

    private static long getStartTime(int r6) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/" + r6 + "/stat"));
        try {
            String readLine = bufferedReader.readLine();
            try {
                return (Long.parseLong(readLine.substring(readLine.lastIndexOf(") ")).split(" ")[20]) * 1000) / Os.sysconf(OsConstants._SC_CLK_TCK);
            } catch (Exception e) {
                throw new IOException(e);
            }
        } finally {
            bufferedReader.close();
        }
    }

    private void emitMark(String str, long j) {
        emit("mark", str, j);
    }

    private void emit(String str, String str2, long j) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("name", str2);
        createMap.putInt("startTime", (int) j);
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit(str, createMap);
    }
}
