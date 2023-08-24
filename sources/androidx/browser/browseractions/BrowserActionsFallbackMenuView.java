package androidx.browser.browseractions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.browser.C0220R;

@Deprecated
/* loaded from: classes.dex */
public class BrowserActionsFallbackMenuView extends LinearLayout {
    private final int mBrowserActionsMenuMaxWidthPx;
    private final int mBrowserActionsMenuMinPaddingPx;

    public BrowserActionsFallbackMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBrowserActionsMenuMinPaddingPx = getResources().getDimensionPixelOffset(C0220R.dimen.browser_actions_context_menu_min_padding);
        this.mBrowserActionsMenuMaxWidthPx = getResources().getDimensionPixelOffset(C0220R.dimen.browser_actions_context_menu_max_width);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(getResources().getDisplayMetrics().widthPixels - (this.mBrowserActionsMenuMinPaddingPx * 2), this.mBrowserActionsMenuMaxWidthPx), 1073741824), r3);
    }
}