package com.facebook.react.viewmanagers;

import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.RNSVGLineManagerInterface;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes.dex */
public class RNSVGLineManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNSVGLineManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNSVGLineManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
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
            case -729118945:
                if (str.equals("fillRule")) {
                    c = 6;
                    break;
                }
                break;
            case -416535885:
                if (str.equals("strokeOpacity")) {
                    c = 7;
                    break;
                }
                break;
            case -293492298:
                if (str.equals(ViewProps.POINTER_EVENTS)) {
                    c = '\b';
                    break;
                }
                break;
            case -53677816:
                if (str.equals("fillOpacity")) {
                    c = '\t';
                    break;
                }
                break;
            case -44578051:
                if (str.equals("strokeDashoffset")) {
                    c = '\n';
                    break;
                }
                break;
            case 3769:
                if (str.equals("x1")) {
                    c = 11;
                    break;
                }
                break;
            case 3770:
                if (str.equals("x2")) {
                    c = '\f';
                    break;
                }
                break;
            case 3800:
                if (str.equals("y1")) {
                    c = CharUtils.f1567CR;
                    break;
                }
                break;
            case 3801:
                if (str.equals("y2")) {
                    c = 14;
                    break;
                }
                break;
            case 3143043:
                if (str.equals("fill")) {
                    c = 15;
                    break;
                }
                break;
            case 3344108:
                if (str.equals("mask")) {
                    c = 16;
                    break;
                }
                break;
            case 3373707:
                if (str.equals("name")) {
                    c = 17;
                    break;
                }
                break;
            case 78845486:
                if (str.equals("strokeMiterlimit")) {
                    c = 18;
                    break;
                }
                break;
            case 104482996:
                if (str.equals("vectorEffect")) {
                    c = 19;
                    break;
                }
                break;
            case 217109576:
                if (str.equals("markerStart")) {
                    c = 20;
                    break;
                }
                break;
            case 401643183:
                if (str.equals("strokeDasharray")) {
                    c = 21;
                    break;
                }
                break;
            case 917656469:
                if (str.equals("clipPath")) {
                    c = 22;
                    break;
                }
                break;
            case 917735020:
                if (str.equals("clipRule")) {
                    c = 23;
                    break;
                }
                break;
            case 1027575302:
                if (str.equals("strokeLinecap")) {
                    c = 24;
                    break;
                }
                break;
            case 1671764162:
                if (str.equals("display")) {
                    c = 25;
                    break;
                }
                break;
            case 1790285174:
                if (str.equals("strokeLinejoin")) {
                    c = 26;
                    break;
                }
                break;
            case 1847674614:
                if (str.equals("responsible")) {
                    c = 27;
                    break;
                }
                break;
            case 1924065902:
                if (str.equals("strokeWidth")) {
                    c = 28;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mViewManager.setOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case 1:
                ((RNSVGLineManagerInterface) this.mViewManager).setMatrix(t, (ReadableArray) obj);
                return;
            case 2:
                ((RNSVGLineManagerInterface) this.mViewManager).setPropList(t, (ReadableArray) obj);
                return;
            case 3:
                ((RNSVGLineManagerInterface) this.mViewManager).setMarkerEnd(t, obj != null ? (String) obj : null);
                return;
            case 4:
                ((RNSVGLineManagerInterface) this.mViewManager).setMarkerMid(t, obj != null ? (String) obj : null);
                return;
            case 5:
                ((RNSVGLineManagerInterface) this.mViewManager).setStroke(t, (ReadableMap) obj);
                return;
            case 6:
                ((RNSVGLineManagerInterface) this.mViewManager).setFillRule(t, obj != null ? ((Double) obj).intValue() : 1);
                return;
            case 7:
                ((RNSVGLineManagerInterface) this.mViewManager).setStrokeOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case '\b':
                ((RNSVGLineManagerInterface) this.mViewManager).setPointerEvents(t, obj != null ? (String) obj : null);
                return;
            case '\t':
                ((RNSVGLineManagerInterface) this.mViewManager).setFillOpacity(t, obj != null ? ((Double) obj).floatValue() : 1.0f);
                return;
            case '\n':
                ((RNSVGLineManagerInterface) this.mViewManager).setStrokeDashoffset(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 11:
                if (obj instanceof String) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setX1((RNSVGLineManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setX1((RNSVGLineManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLineManagerInterface) this.mViewManager).setX1((RNSVGLineManagerInterface) t, (Double) null);
                    return;
                }
            case '\f':
                if (obj instanceof String) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setX2((RNSVGLineManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setX2((RNSVGLineManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLineManagerInterface) this.mViewManager).setX2((RNSVGLineManagerInterface) t, (Double) null);
                    return;
                }
            case '\r':
                if (obj instanceof String) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setY1((RNSVGLineManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setY1((RNSVGLineManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLineManagerInterface) this.mViewManager).setY1((RNSVGLineManagerInterface) t, (Double) null);
                    return;
                }
            case 14:
                if (obj instanceof String) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setY2((RNSVGLineManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setY2((RNSVGLineManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLineManagerInterface) this.mViewManager).setY2((RNSVGLineManagerInterface) t, (Double) null);
                    return;
                }
            case 15:
                ((RNSVGLineManagerInterface) this.mViewManager).setFill(t, (ReadableMap) obj);
                return;
            case 16:
                ((RNSVGLineManagerInterface) this.mViewManager).setMask(t, obj != null ? (String) obj : null);
                return;
            case 17:
                ((RNSVGLineManagerInterface) this.mViewManager).setName(t, obj != null ? (String) obj : null);
                return;
            case 18:
                ((RNSVGLineManagerInterface) this.mViewManager).setStrokeMiterlimit(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                return;
            case 19:
                ((RNSVGLineManagerInterface) this.mViewManager).setVectorEffect(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 20:
                ((RNSVGLineManagerInterface) this.mViewManager).setMarkerStart(t, obj != null ? (String) obj : null);
                return;
            case 21:
                ((RNSVGLineManagerInterface) this.mViewManager).setStrokeDasharray(t, (ReadableArray) obj);
                return;
            case 22:
                ((RNSVGLineManagerInterface) this.mViewManager).setClipPath(t, obj != null ? (String) obj : null);
                return;
            case 23:
                ((RNSVGLineManagerInterface) this.mViewManager).setClipRule(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 24:
                ((RNSVGLineManagerInterface) this.mViewManager).setStrokeLinecap(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 25:
                ((RNSVGLineManagerInterface) this.mViewManager).setDisplay(t, obj != null ? (String) obj : null);
                return;
            case 26:
                ((RNSVGLineManagerInterface) this.mViewManager).setStrokeLinejoin(t, obj != null ? ((Double) obj).intValue() : 0);
                return;
            case 27:
                ((RNSVGLineManagerInterface) this.mViewManager).setResponsible(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                return;
            case 28:
                if (obj instanceof String) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setStrokeWidth((RNSVGLineManagerInterface) t, (String) obj);
                    return;
                } else if (obj instanceof Double) {
                    ((RNSVGLineManagerInterface) this.mViewManager).setStrokeWidth((RNSVGLineManagerInterface) t, (Double) obj);
                    return;
                } else {
                    ((RNSVGLineManagerInterface) this.mViewManager).setStrokeWidth((RNSVGLineManagerInterface) t, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                    return;
                }
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
