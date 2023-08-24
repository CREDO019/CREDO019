package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNSScreenStackHeaderSubviewManagerInterface;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;

/* loaded from: classes.dex */
public class RNSScreenStackHeaderSubviewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSScreenStackHeaderSubviewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSScreenStackHeaderSubviewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        if (str.equals(SessionDescription.ATTR_TYPE)) {
            ((RNSScreenStackHeaderSubviewManagerInterface) this.mViewManager).setType(t, (String) obj);
        } else {
            super.setProperty(t, str, obj);
        }
    }
}