package com.google.android.exoplayer2.p012ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.exoplayer2.p012ui.SubtitleView;
import com.google.android.exoplayer2.text.Cue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.android.exoplayer2.ui.CanvasSubtitleOutput */
/* loaded from: classes2.dex */
final class CanvasSubtitleOutput extends View implements SubtitleView.Output {
    private float bottomPaddingFraction;
    private List<Cue> cues;
    private final List<SubtitlePainter> painters;
    private CaptionStyleCompat style;
    private float textSize;
    private int textSizeType;

    public CanvasSubtitleOutput(Context context) {
        this(context, null);
    }

    public CanvasSubtitleOutput(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.painters = new ArrayList();
        this.cues = Collections.emptyList();
        this.textSizeType = 0;
        this.textSize = 0.0533f;
        this.style = CaptionStyleCompat.DEFAULT;
        this.bottomPaddingFraction = 0.08f;
    }

    @Override // com.google.android.exoplayer2.p012ui.SubtitleView.Output
    public void update(List<Cue> list, CaptionStyleCompat captionStyleCompat, float f, int r4, float f2) {
        this.cues = list;
        this.style = captionStyleCompat;
        this.textSize = f;
        this.textSizeType = r4;
        this.bottomPaddingFraction = f2;
        while (this.painters.size() < list.size()) {
            this.painters.add(new SubtitlePainter(getContext()));
        }
        invalidate();
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas canvas) {
        List<Cue> list = this.cues;
        if (list.isEmpty()) {
            return;
        }
        int height = getHeight();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int paddingBottom = height - getPaddingBottom();
        if (paddingBottom <= paddingTop || width <= paddingLeft) {
            return;
        }
        int r11 = paddingBottom - paddingTop;
        float resolveTextSize = SubtitleViewUtils.resolveTextSize(this.textSizeType, this.textSize, height, r11);
        if (resolveTextSize <= 0.0f) {
            return;
        }
        int size = list.size();
        int r9 = 0;
        while (r9 < size) {
            Cue cue = list.get(r9);
            if (cue.verticalType != Integer.MIN_VALUE) {
                cue = repositionVerticalCue(cue);
            }
            Cue cue2 = cue;
            int r20 = paddingBottom;
            this.painters.get(r9).draw(cue2, this.style, resolveTextSize, SubtitleViewUtils.resolveTextSize(cue2.textSizeType, cue2.textSize, height, r11), this.bottomPaddingFraction, canvas, paddingLeft, paddingTop, width, r20);
            r9++;
            size = size;
            r11 = r11;
            paddingBottom = r20;
            width = width;
        }
    }

    private static Cue repositionVerticalCue(Cue cue) {
        Cue.Builder textAlignment = cue.buildUpon().setPosition(-3.4028235E38f).setPositionAnchor(Integer.MIN_VALUE).setTextAlignment(null);
        if (cue.lineType == 0) {
            textAlignment.setLine(1.0f - cue.line, 0);
        } else {
            textAlignment.setLine((-cue.line) - 1.0f, 1);
        }
        int r4 = cue.lineAnchor;
        if (r4 == 0) {
            textAlignment.setLineAnchor(2);
        } else if (r4 == 2) {
            textAlignment.setLineAnchor(0);
        }
        return textAlignment.build();
    }
}
