package com.google.android.exoplayer2.p012ui;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.google.android.exoplayer2.p012ui.SubtitleView;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.exoplayer2.ui.WebViewSubtitleOutput */
/* loaded from: classes2.dex */
final class WebViewSubtitleOutput extends FrameLayout implements SubtitleView.Output {
    private static final float CSS_LINE_HEIGHT = 1.2f;
    private static final String DEFAULT_BACKGROUND_CSS_CLASS = "default_bg";
    private float bottomPaddingFraction;
    private final CanvasSubtitleOutput canvasSubtitleOutput;
    private float defaultTextSize;
    private int defaultTextSizeType;
    private CaptionStyleCompat style;
    private List<Cue> textCues;
    private final WebView webView;

    private static int anchorTypeToTranslatePercent(int r1) {
        if (r1 != 1) {
            return r1 != 2 ? 0 : -100;
        }
        return -50;
    }

    private static String convertVerticalTypeToCss(int r1) {
        return r1 != 1 ? r1 != 2 ? "horizontal-tb" : "vertical-lr" : "vertical-rl";
    }

    public WebViewSubtitleOutput(Context context) {
        this(context, null);
    }

    public WebViewSubtitleOutput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textCues = Collections.emptyList();
        this.style = CaptionStyleCompat.DEFAULT;
        this.defaultTextSize = 0.0533f;
        this.defaultTextSizeType = 0;
        this.bottomPaddingFraction = 0.08f;
        CanvasSubtitleOutput canvasSubtitleOutput = new CanvasSubtitleOutput(context, attributeSet);
        this.canvasSubtitleOutput = canvasSubtitleOutput;
        WebView webView = new WebView(this, context, attributeSet) { // from class: com.google.android.exoplayer2.ui.WebViewSubtitleOutput.1
            @Override // android.webkit.WebView, android.view.View
            public boolean onTouchEvent(MotionEvent motionEvent) {
                super.onTouchEvent(motionEvent);
                return false;
            }

            @Override // android.view.View
            public boolean performClick() {
                super.performClick();
                return false;
            }
        };
        this.webView = webView;
        webView.setBackgroundColor(0);
        addView(canvasSubtitleOutput);
        addView(webView);
    }

    @Override // com.google.android.exoplayer2.p012ui.SubtitleView.Output
    public void update(List<Cue> list, CaptionStyleCompat captionStyleCompat, float f, int r10, float f2) {
        this.style = captionStyleCompat;
        this.defaultTextSize = f;
        this.defaultTextSizeType = r10;
        this.bottomPaddingFraction = f2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int r2 = 0; r2 < list.size(); r2++) {
            Cue cue = list.get(r2);
            if (cue.bitmap != null) {
                arrayList.add(cue);
            } else {
                arrayList2.add(cue);
            }
        }
        if (!this.textCues.isEmpty() || !arrayList2.isEmpty()) {
            this.textCues = arrayList2;
            updateWebView();
        }
        this.canvasSubtitleOutput.update(arrayList, captionStyleCompat, f, r10, f2);
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int r2, int r3, int r4, int r5) {
        super.onLayout(z, r2, r3, r4, r5);
        if (!z || this.textCues.isEmpty()) {
            return;
        }
        updateWebView();
    }

    public void destroy() {
        this.webView.destroy();
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x0152, code lost:
        if (r13 != false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0155, code lost:
        if (r13 != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x0157, code lost:
        r21 = "left";
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0159, code lost:
        r22 = com.facebook.react.uimanager.ViewProps.TOP;
        r23 = r21;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0116  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0231  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x024f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updateWebView() {
        /*
            Method dump skipped, instructions count: 718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.p012ui.WebViewSubtitleOutput.updateWebView():void");
    }

    private static String getBlockShearTransformFunction(Cue cue) {
        if (cue.shearDegrees != 0.0f) {
            return Util.formatInvariant("%s(%.2fdeg)", (cue.verticalType == 2 || cue.verticalType == 1) ? "skewY" : "skewX", Float.valueOf(cue.shearDegrees));
        }
        return "";
    }

    private String convertTextSizeToCss(int r4, float f) {
        float resolveTextSize = SubtitleViewUtils.resolveTextSize(r4, f, getHeight(), (getHeight() - getPaddingTop()) - getPaddingBottom());
        return resolveTextSize == -3.4028235E38f ? "unset" : Util.formatInvariant("%.2fpx", Float.valueOf(resolveTextSize / getContext().getResources().getDisplayMetrics().density));
    }

    private static String convertCaptionStyleToCssTextShadow(CaptionStyleCompat captionStyleCompat) {
        int r0 = captionStyleCompat.edgeType;
        if (r0 != 1) {
            if (r0 != 2) {
                if (r0 != 3) {
                    return r0 != 4 ? "unset" : Util.formatInvariant("-0.05em -0.05em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
                }
                return Util.formatInvariant("0.06em 0.08em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
            }
            return Util.formatInvariant("0.1em 0.12em 0.15em %s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
        }
        return Util.formatInvariant("1px 1px 0 %1$s, 1px -1px 0 %1$s, -1px 1px 0 %1$s, -1px -1px 0 %1$s", HtmlUtils.toCssRgba(captionStyleCompat.edgeColor));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.android.exoplayer2.ui.WebViewSubtitleOutput$2 */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class C20822 {
        static final /* synthetic */ int[] $SwitchMap$android$text$Layout$Alignment;

        static {
            int[] r0 = new int[Layout.Alignment.values().length];
            $SwitchMap$android$text$Layout$Alignment = r0;
            try {
                r0[Layout.Alignment.ALIGN_NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_OPPOSITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$text$Layout$Alignment[Layout.Alignment.ALIGN_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static String convertAlignmentToCss(Layout.Alignment alignment) {
        if (alignment == null) {
            return TtmlNode.CENTER;
        }
        int r2 = C20822.$SwitchMap$android$text$Layout$Alignment[alignment.ordinal()];
        return r2 != 1 ? r2 != 2 ? TtmlNode.CENTER : "end" : "start";
    }
}