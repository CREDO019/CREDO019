package expo.modules.p019av.video;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.viewevent.ViewEventCallback;
import expo.modules.kotlin.viewevent.ViewEventDelegate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* compiled from: VideoViewWrapper.kt */
@Metadata(m184d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010$\u001a\u00020\u0017H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0011\u0010\rR!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0014\u0010\rR!\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u0018\u0010\rR!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u000f\u001a\u0004\b\u001b\u0010\rR!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u000f\u001a\u0004\b\u001e\u0010\rR\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#¨\u0006%"}, m183d2 = {"Lexpo/modules/av/video/VideoViewWrapper;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "appContext", "Lexpo/modules/kotlin/AppContext;", "(Landroid/content/Context;Lexpo/modules/kotlin/AppContext;)V", "mLayoutRunnable", "Ljava/lang/Runnable;", "onError", "Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "Landroid/os/Bundle;", "getOnError", "()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", "onError$delegate", "Lexpo/modules/kotlin/viewevent/ViewEventDelegate;", "onFullscreenUpdate", "getOnFullscreenUpdate", "onFullscreenUpdate$delegate", "onLoad", "getOnLoad", "onLoad$delegate", "onLoadStart", "", "getOnLoadStart", "onLoadStart$delegate", "onReadyForDisplay", "getOnReadyForDisplay", "onReadyForDisplay$delegate", "onStatusUpdate", "getOnStatusUpdate", "onStatusUpdate$delegate", "videoViewInstance", "Lexpo/modules/av/video/VideoView;", "getVideoViewInstance", "()Lexpo/modules/av/video/VideoView;", "requestLayout", "expo-av_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* renamed from: expo.modules.av.video.VideoViewWrapper */
/* loaded from: classes4.dex */
public final class VideoViewWrapper extends FrameLayout {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(VideoViewWrapper.class, "onStatusUpdate", "getOnStatusUpdate()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoViewWrapper.class, "onLoadStart", "getOnLoadStart()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoViewWrapper.class, "onLoad", "getOnLoad()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoViewWrapper.class, "onError", "getOnError()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoViewWrapper.class, "onReadyForDisplay", "getOnReadyForDisplay()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0)), Reflection.property1(new PropertyReference1Impl(VideoViewWrapper.class, "onFullscreenUpdate", "getOnFullscreenUpdate()Lexpo/modules/kotlin/viewevent/ViewEventCallback;", 0))};
    private final Runnable mLayoutRunnable;
    private final ViewEventDelegate onError$delegate;
    private final ViewEventDelegate onFullscreenUpdate$delegate;
    private final ViewEventDelegate onLoad$delegate;
    private final ViewEventDelegate onLoadStart$delegate;
    private final ViewEventDelegate onReadyForDisplay$delegate;
    private final ViewEventDelegate onStatusUpdate$delegate;
    private final VideoView videoViewInstance;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoViewWrapper(Context context, AppContext appContext) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        this.mLayoutRunnable = new Runnable() { // from class: expo.modules.av.video.VideoViewWrapper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoViewWrapper.m1622mLayoutRunnable$lambda0(VideoViewWrapper.this);
            }
        };
        VideoView videoView = new VideoView(context, this, appContext);
        this.videoViewInstance = videoView;
        addView(videoView, generateDefaultLayoutParams());
        VideoViewWrapper videoViewWrapper = this;
        this.onStatusUpdate$delegate = new ViewEventDelegate(Reflection.typeOf(Bundle.class), videoViewWrapper, null);
        this.onLoadStart$delegate = new ViewEventDelegate(Reflection.typeOf(Unit.class), videoViewWrapper, null);
        this.onLoad$delegate = new ViewEventDelegate(Reflection.typeOf(Bundle.class), videoViewWrapper, null);
        this.onError$delegate = new ViewEventDelegate(Reflection.typeOf(Bundle.class), videoViewWrapper, null);
        this.onReadyForDisplay$delegate = new ViewEventDelegate(Reflection.typeOf(Bundle.class), videoViewWrapper, null);
        this.onFullscreenUpdate$delegate = new ViewEventDelegate(Reflection.typeOf(Bundle.class), videoViewWrapper, null);
    }

    public final VideoView getVideoViewInstance() {
        return this.videoViewInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: mLayoutRunnable$lambda-0  reason: not valid java name */
    public static final void m1622mLayoutRunnable$lambda0(VideoViewWrapper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.measure(View.MeasureSpec.makeMeasureSpec(this$0.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(this$0.getHeight(), 1073741824));
        this$0.layout(this$0.getLeft(), this$0.getTop(), this$0.getRight(), this$0.getBottom());
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.mLayoutRunnable);
    }

    public final ViewEventCallback<Bundle> getOnStatusUpdate() {
        return this.onStatusUpdate$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public final ViewEventCallback<Unit> getOnLoadStart() {
        return this.onLoadStart$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public final ViewEventCallback<Bundle> getOnLoad() {
        return this.onLoad$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public final ViewEventCallback<Bundle> getOnError() {
        return this.onError$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public final ViewEventCallback<Bundle> getOnReadyForDisplay() {
        return this.onReadyForDisplay$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public final ViewEventCallback<Bundle> getOnFullscreenUpdate() {
        return this.onFullscreenUpdate$delegate.getValue(this, $$delegatedProperties[5]);
    }
}
