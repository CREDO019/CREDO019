package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.UiThreadUtil;
import com.onesignal.NotificationBundleProcessor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ViewHierarchyDumper {
    public static JSONObject toJSON(View view) throws JSONException {
        UiThreadUtil.assertOnUiThread();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NotificationBundleProcessor.PUSH_MINIFIED_BUTTON_TEXT, view.getClass().getName());
        jSONObject.put("i", System.identityHashCode(view));
        Object tag = view.getTag();
        if (tag != null && (tag instanceof String)) {
            jSONObject.put("t", tag);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int r2 = 0; r2 < viewGroup.getChildCount(); r2++) {
                    jSONArray.put(r2, toJSON(viewGroup.getChildAt(r2)));
                }
                jSONObject.put("c", jSONArray);
            }
        }
        return jSONObject;
    }
}
