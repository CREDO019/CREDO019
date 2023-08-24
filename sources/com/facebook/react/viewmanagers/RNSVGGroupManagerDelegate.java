package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGGroupManagerInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class RNSVGGroupManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGGroupManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGGroupManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1267206133:
                if (str.equals(ViewProps.OPACITY)) {
                    c = 0;
                    break;
                }
                break;
            case -1081239615:
                if (str.equals("matrix")) {
                    c = 1;
                    break;
                }
                break;
            case -993894751:
                if (str.equals("propList")) {
                    c = 2;
                    break;
                }
                break;
            case -933864895:
                if (str.equals("markerEnd")) {
                    c = 3;
                    break;
                }
                break;
            case -933857362:
                if (str.equals("markerMid")) {
                    c = 4;
                    break;
                }
                break;
            case -891980232:
                if (str.equals("stroke")) {
                    c = 5;
                    break;
                }
                break;
            case -734428249:
                if (str.equals("fontWeight")) {
                    c = 6;
                    break;
                }
                break;
            case -729118945:
                if (str.equals("fillRule")) {
                    c = 7;
                    break;
                }
                break;
            case -416535885:
                if (str.equals("strokeOpacity")) {
                    c = '\b';
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = '\t';
                    break;
                }
                break;
            case -53677816:
                if (str.equals("fillOpacity")) {
                    c = '\n';
                    break;
                }
                break;
            case -44578051:
                if (str.equals("strokeDashoffset")) {
                    c = 11;
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c = '\f';
                    break;
                }
                break;
            case 3148879:
                if (str.equals("font")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = 14;
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 15;
                    break;
                }
                break;
            case 78845486:
                if (str.equals("strokeMiterlimit")) {
                    c = 16;
                    break;
                }
                break;
            case 104482996:
                if (str.equals("vectorEffect")) {
                    c = 17;
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = 18;
                    break;
                }
                break;
            case 365601008:
                if (str.equals("fontSize")) {
                    c = 19;
                    break;
                }
                break;
            case 401643183:
                if (str.equals("strokeDasharray")) {
                    c = 20;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 21;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = 22;
                    break;
                }
                break;
            case 1027575302:
                if (str.equals("strokeLinecap")) {
                    c = 23;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = 24;
                    break;
                }
                break;
            case 1790285174:
                if (str.equals("strokeLinejoin")) {
                    c = 25;
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = 26;
                    break;
                }
                break;
            case 1924065902:
                if (str.equals("strokeWidth")) {
                    c = 27;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 1:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                return;
            case 2:
                ((RNSVGGroupManagerInterface) this.mViewManager).setPropList(t, (ReadableArray) obj);
                return;
            case 3:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                return;
            case 4:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                return;
            case 5:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStroke(t, (ReadableMap) obj);
                return;
            case 6:
                if (obj instanceof String) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontWeight((RNSVGGroupManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontWeight((RNSVGGroupManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontWeight((RNSVGGroupManagerInterface) t, (Double) null);
                    return;
                }
            case 7:
                ((RNSVGGroupManagerInterface) this.mViewManager).setFillRule(t, obj != null ? ((Double) obj).intValue() : 1);
                return;
            case '\b':
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case '\t':
                ((RNSVGGroupManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case '\n':
                ((RNSVGGroupManagerInterface) this.mViewManager).setFillOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 11:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeDashoffset(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case '\f':
                ((RNSVGGroupManagerInterface) this.mViewManager).setFill(t, (ReadableMap) obj);
                return;
            case '\r':
                ((RNSVGGroupManagerInterface) this.mViewManager).setFont(t, (ReadableMap) obj);
                return;
            case 14:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                return;
            case 15:
                ((RNSVGGroupManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                return;
            case 16:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeMiterlimit(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 17:
                ((RNSVGGroupManagerInterface) this.mViewManager).setVectorEffect(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 18:
                ((RNSVGGroupManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                return;
            case 19:
                if (obj instanceof String) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontSize((RNSVGGroupManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontSize((RNSVGGroupManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setFontSize((RNSVGGroupManagerInterface) t, (Double) null);
                    return;
                }
            case 20:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeDasharray(t, (ReadableArray) obj);
                return;
            case 21:
                ((RNSVGGroupManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                return;
            case 22:
                ((RNSVGGroupManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 23:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeLinecap(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 24:
                ((RNSVGGroupManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                return;
            case 25:
                ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeLinejoin(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 26:
                ((RNSVGGroupManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 27:
                if (obj instanceof String) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeWidth((RNSVGGroupManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeWidth((RNSVGGroupManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGGroupManagerInterface) this.mViewManager).setStrokeWidth((RNSVGGroupManagerInterface) t, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                    return;
                }
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
