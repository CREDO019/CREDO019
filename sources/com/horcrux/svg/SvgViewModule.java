package com.horcrux.svg;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.module.annotations.ReactModule;
import com.horcrux.rnsvg.NativeSvgViewModuleSpec;
import javax.annotation.Nonnull;

@ReactModule(name = SvgViewModule.NAME)
/* loaded from: classes3.dex */
class SvgViewModule extends NativeSvgViewModuleSpec {
    public static final String NAME = "RNSVGSvgViewModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return NAME;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SvgViewModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.horcrux.svg.SvgViewModule$1 */
    /* loaded from: classes3.dex */
    public class RunnableC34231 implements Runnable {
        final /* synthetic */ int val$attempt;
        final /* synthetic */ ReadableMap val$options;
        final /* synthetic */ Callback val$successCallback;
        final /* synthetic */ int val$tag;

        RunnableC34231(int r1, ReadableMap readableMap, Callback callback, int r4) {
            this.val$tag = r1;
            this.val$options = readableMap;
            this.val$successCallback = callback;
            this.val$attempt = r4;
        }

        @Override // java.lang.Runnable
        public void run() {
            SvgView svgViewByTag = SvgViewManager.getSvgViewByTag(this.val$tag);
            if (svgViewByTag == null) {
                SvgViewManager.runWhenViewIsAvailable(this.val$tag, new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SvgView svgViewByTag2 = SvgViewManager.getSvgViewByTag(RunnableC34231.this.val$tag);
                        if (svgViewByTag2 == null) {
                            return;
                        }
                        svgViewByTag2.setToDataUrlTask(new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                SvgViewModule.toDataURL(RunnableC34231.this.val$tag, RunnableC34231.this.val$options, RunnableC34231.this.val$successCallback, RunnableC34231.this.val$attempt + 1);
                            }
                        });
                    }
                });
            } else if (svgViewByTag.notRendered()) {
                svgViewByTag.setToDataUrlTask(new Runnable() { // from class: com.horcrux.svg.SvgViewModule.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SvgViewModule.toDataURL(RunnableC34231.this.val$tag, RunnableC34231.this.val$options, RunnableC34231.this.val$successCallback, RunnableC34231.this.val$attempt + 1);
                    }
                });
            } else {
                ReadableMap readableMap = this.val$options;
                if (readableMap != null) {
                    this.val$successCallback.invoke(svgViewByTag.toDataURL(readableMap.getInt("width"), this.val$options.getInt("height")));
                } else {
                    this.val$successCallback.invoke(svgViewByTag.toDataURL());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void toDataURL(int r1, ReadableMap readableMap, Callback callback, int r4) {
        UiThreadUtil.runOnUiThread(new RunnableC34231(r1, readableMap, callback, r4));
    }

    @Override // com.horcrux.rnsvg.NativeSvgViewModuleSpec
    @ReactMethod
    public void toDataURL(Double d, ReadableMap readableMap, Callback callback) {
        toDataURL(d.intValue(), readableMap, callback, 0);
    }
}
