package expo.modules.adapters.react.views;

import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ViewProps;
import expo.modules.adapters.react.ArgumentsHelper;
import expo.modules.core.ViewManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class ViewManagerAdapterUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getViewManagerAdapterName(ViewManager viewManager) {
        return "ViewManagerAdapter_" + viewManager.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getConstants(ViewManager viewManager) {
        HashMap hashMap = new HashMap();
        hashMap.put("eventNames", viewManager.getExportedEventNames());
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map<String, Object> getExportedCustomDirectEventTypeConstants(ViewManager viewManager) {
        MapBuilder.Builder builder = MapBuilder.builder();
        for (String str : viewManager.getExportedEventNames()) {
            if (str instanceof String) {
                builder.put(normalizeEventName(str), MapBuilder.m1261of("registrationName", str));
            }
        }
        return builder.build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <V extends View> void setProxiedProperties(String str, ViewManager<V> viewManager, V v, ReadableMap readableMap) {
        ViewManager<V>.PropSetterInfo propSetterInfo;
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            try {
                propSetterInfo = viewManager.getPropSetterInfos().get(nextKey);
            } catch (Exception e) {
                Log.e(str, "Error when setting prop " + nextKey + ". " + e.getMessage());
            }
            if (propSetterInfo == null) {
                throw new IllegalArgumentException("No setter found for prop " + nextKey + " in " + str);
                break;
            }
            viewManager.updateProp(v, nextKey, ArgumentsHelper.getNativeArgumentForExpectedClass(readableMap.getDynamic(nextKey), propSetterInfo.getExpectedValueClass()));
        }
    }

    public static String normalizeEventName(String str) {
        if (str.startsWith("on")) {
            return ViewProps.TOP + str.substring(2);
        }
        return str;
    }
}
