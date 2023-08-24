package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.LinearLayoutCompat;

/* loaded from: classes.dex */
public class ActionMenuView extends LinearLayoutCompat implements MenuBuilder.ItemInvoker, MenuView {
    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private MenuPresenter.Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    /* loaded from: classes.dex */
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    /* loaded from: classes.dex */
    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem menuItem);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    public ActionMenuView(Context context) {
        this(context, null);
    }

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setBaselineAligned(false);
        float f = context.getResources().getDisplayMetrics().density;
        this.mMinCellSize = (int) (56.0f * f);
        this.mGeneratedItemPadding = (int) (f * 4.0f);
        this.mPopupContext = context;
        this.mPopupTheme = 0;
    }

    public void setPopupTheme(int r3) {
        if (this.mPopupTheme != r3) {
            this.mPopupTheme = r3;
            if (r3 == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), r3);
            }
        }
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter) {
        this.mPresenter = actionMenuPresenter;
        actionMenuPresenter.setMenuView(this);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.updateMenuView(false);
            if (this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int r6, int r7) {
        MenuBuilder menuBuilder;
        boolean z = this.mFormatItems;
        boolean z2 = View.MeasureSpec.getMode(r6) == 1073741824;
        this.mFormatItems = z2;
        if (z != z2) {
            this.mFormatItemsWidth = 0;
        }
        int size = View.MeasureSpec.getSize(r6);
        if (this.mFormatItems && (menuBuilder = this.mMenu) != null && size != this.mFormatItemsWidth) {
            this.mFormatItemsWidth = size;
            menuBuilder.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (this.mFormatItems && childCount > 0) {
            onMeasureExactFormat(r6, r7);
            return;
        }
        for (int r1 = 0; r1 < childCount; r1++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(r1).getLayoutParams();
            layoutParams.rightMargin = 0;
            layoutParams.leftMargin = 0;
        }
        super.onMeasure(r6, r7);
    }

    /* JADX WARN: Type inference failed for: r14v12 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9, types: [int, boolean] */
    private void onMeasureExactFormat(int r30, int r31) {
        int r11;
        int r26;
        boolean z;
        int r24;
        boolean z2;
        boolean z3;
        int r20;
        ?? r14;
        int mode = View.MeasureSpec.getMode(r31);
        int size = View.MeasureSpec.getSize(r30);
        int size2 = View.MeasureSpec.getSize(r31);
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int childMeasureSpec = getChildMeasureSpec(r31, paddingTop, -2);
        int r2 = size - paddingLeft;
        int r4 = this.mMinCellSize;
        int r7 = r2 / r4;
        int r8 = r2 % r4;
        if (r7 == 0) {
            setMeasuredDimension(r2, 0);
            return;
        }
        int r42 = r4 + (r8 / r7);
        int childCount = getChildCount();
        int r10 = 0;
        int r12 = 0;
        boolean z4 = false;
        int r142 = 0;
        int r15 = 0;
        int r16 = 0;
        long j = 0;
        while (r12 < childCount) {
            View childAt = getChildAt(r12);
            int r19 = size2;
            if (childAt.getVisibility() != 8) {
                boolean z5 = childAt instanceof ActionMenuItemView;
                int r143 = r142 + 1;
                if (z5) {
                    int r9 = this.mGeneratedItemPadding;
                    r20 = r143;
                    r14 = 0;
                    childAt.setPadding(r9, 0, r9, 0);
                } else {
                    r20 = r143;
                    r14 = 0;
                }
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                layoutParams.expanded = r14;
                layoutParams.extraPixels = r14;
                layoutParams.cellsUsed = r14;
                layoutParams.expandable = r14;
                layoutParams.leftMargin = r14;
                layoutParams.rightMargin = r14;
                layoutParams.preventEdgeOffset = z5 && ((ActionMenuItemView) childAt).hasText();
                int measureChildForCells = measureChildForCells(childAt, r42, layoutParams.isOverflowButton ? 1 : r7, childMeasureSpec, paddingTop);
                r15 = Math.max(r15, measureChildForCells);
                if (layoutParams.expandable) {
                    r16++;
                }
                if (layoutParams.isOverflowButton) {
                    z4 = true;
                }
                r7 -= measureChildForCells;
                r10 = Math.max(r10, childAt.getMeasuredHeight());
                if (measureChildForCells == 1) {
                    j |= 1 << r12;
                    r10 = r10;
                }
                r142 = r20;
            }
            r12++;
            size2 = r19;
        }
        int r192 = size2;
        boolean z6 = z4 && r142 == 2;
        boolean z7 = false;
        while (r16 > 0 && r7 > 0) {
            int r3 = 0;
            int r112 = 0;
            int r122 = Integer.MAX_VALUE;
            long j2 = 0;
            while (r112 < childCount) {
                boolean z8 = z7;
                LayoutParams layoutParams2 = (LayoutParams) getChildAt(r112).getLayoutParams();
                int r242 = r10;
                if (layoutParams2.expandable) {
                    if (layoutParams2.cellsUsed < r122) {
                        r122 = layoutParams2.cellsUsed;
                        j2 = 1 << r112;
                        r3 = 1;
                    } else if (layoutParams2.cellsUsed == r122) {
                        r3++;
                        j2 |= 1 << r112;
                    }
                }
                r112++;
                r10 = r242;
                z7 = z8;
            }
            z = z7;
            r24 = r10;
            j |= j2;
            if (r3 > r7) {
                r11 = mode;
                r26 = r2;
                break;
            }
            int r123 = r122 + 1;
            int r32 = 0;
            while (r32 < childCount) {
                View childAt2 = getChildAt(r32);
                LayoutParams layoutParams3 = (LayoutParams) childAt2.getLayoutParams();
                int r262 = r2;
                int r113 = mode;
                long j3 = 1 << r32;
                if ((j2 & j3) == 0) {
                    if (layoutParams3.cellsUsed == r123) {
                        j |= j3;
                    }
                    z3 = z6;
                } else {
                    if (z6 && layoutParams3.preventEdgeOffset && r7 == 1) {
                        int r22 = this.mGeneratedItemPadding;
                        z3 = z6;
                        childAt2.setPadding(r22 + r42, 0, r22, 0);
                    } else {
                        z3 = z6;
                    }
                    layoutParams3.cellsUsed++;
                    layoutParams3.expanded = true;
                    r7--;
                }
                r32++;
                mode = r113;
                r2 = r262;
                z6 = z3;
            }
            r10 = r24;
            z7 = true;
        }
        r11 = mode;
        r26 = r2;
        z = z7;
        r24 = r10;
        boolean z9 = !z4 && r142 == 1;
        if (r7 <= 0 || j == 0 || (r7 >= r142 - 1 && !z9 && r15 <= 1)) {
            z2 = z;
        } else {
            float bitCount = Long.bitCount(j);
            if (!z9) {
                if ((j & 1) != 0 && !((LayoutParams) getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                    bitCount -= 0.5f;
                }
                int r23 = childCount - 1;
                if ((j & (1 << r23)) != 0 && !((LayoutParams) getChildAt(r23).getLayoutParams()).preventEdgeOffset) {
                    bitCount -= 0.5f;
                }
            }
            int r1 = bitCount > 0.0f ? (int) ((r7 * r42) / bitCount) : 0;
            z2 = z;
            for (int r25 = 0; r25 < childCount; r25++) {
                if ((j & (1 << r25)) != 0) {
                    View childAt3 = getChildAt(r25);
                    LayoutParams layoutParams4 = (LayoutParams) childAt3.getLayoutParams();
                    if (childAt3 instanceof ActionMenuItemView) {
                        layoutParams4.extraPixels = r1;
                        layoutParams4.expanded = true;
                        if (r25 == 0 && !layoutParams4.preventEdgeOffset) {
                            layoutParams4.leftMargin = (-r1) / 2;
                        }
                    } else if (layoutParams4.isOverflowButton) {
                        layoutParams4.extraPixels = r1;
                        layoutParams4.expanded = true;
                        layoutParams4.rightMargin = (-r1) / 2;
                    } else {
                        if (r25 != 0) {
                            layoutParams4.leftMargin = r1 / 2;
                        }
                        if (r25 != childCount - 1) {
                            layoutParams4.rightMargin = r1 / 2;
                        }
                    }
                    z2 = true;
                }
            }
        }
        if (z2) {
            for (int r92 = 0; r92 < childCount; r92++) {
                View childAt4 = getChildAt(r92);
                LayoutParams layoutParams5 = (LayoutParams) childAt4.getLayoutParams();
                if (layoutParams5.expanded) {
                    childAt4.measure(View.MeasureSpec.makeMeasureSpec((layoutParams5.cellsUsed * r42) + layoutParams5.extraPixels, 1073741824), childMeasureSpec);
                }
            }
        }
        setMeasuredDimension(r26, r11 != 1073741824 ? r24 : r192);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int measureChildForCells(View view, int r6, int r7, int r8, int r9) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r8) - r9, View.MeasureSpec.getMode(r8));
        ActionMenuItemView actionMenuItemView = view instanceof ActionMenuItemView ? (ActionMenuItemView) view : null;
        boolean z = true;
        boolean z2 = actionMenuItemView != null && actionMenuItemView.hasText();
        int r3 = 2;
        if (r7 <= 0 || (z2 && r7 < 2)) {
            r3 = 0;
        } else {
            view.measure(View.MeasureSpec.makeMeasureSpec(r7 * r6, Integer.MIN_VALUE), makeMeasureSpec);
            int measuredWidth = view.getMeasuredWidth();
            int r4 = measuredWidth / r6;
            if (measuredWidth % r6 != 0) {
                r4++;
            }
            if (!z2 || r4 >= 2) {
                r3 = r4;
            }
        }
        layoutParams.expandable = (layoutParams.isOverflowButton || !z2) ? false : false;
        layoutParams.cellsUsed = r3;
        view.measure(View.MeasureSpec.makeMeasureSpec(r6 * r3, 1073741824), makeMeasureSpec);
        return r3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int r19, int r20, int r21, int r22) {
        int width;
        int r15;
        if (!this.mFormatItems) {
            super.onLayout(z, r19, r20, r21, r22);
            return;
        }
        int childCount = getChildCount();
        int r2 = (r22 - r20) / 2;
        int dividerWidth = getDividerWidth();
        int r4 = r21 - r19;
        int paddingRight = (r4 - getPaddingRight()) - getPaddingLeft();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        int r9 = 0;
        int r10 = 0;
        for (int r8 = 0; r8 < childCount; r8++) {
            View childAt = getChildAt(r8);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isOverflowButton) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (hasSupportDividerBeforeChildAt(r8)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (isLayoutRtl) {
                        r15 = getPaddingLeft() + layoutParams.leftMargin;
                        width = r15 + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - layoutParams.rightMargin;
                        r15 = width - measuredWidth;
                    }
                    int r7 = r2 - (measuredHeight / 2);
                    childAt.layout(r15, r7, width, measuredHeight + r7);
                    paddingRight -= measuredWidth;
                    r9 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + layoutParams.leftMargin) + layoutParams.rightMargin;
                    hasSupportDividerBeforeChildAt(r8);
                    r10++;
                }
            }
        }
        if (childCount == 1 && r9 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int r42 = (r4 / 2) - (measuredWidth2 / 2);
            int r23 = r2 - (measuredHeight2 / 2);
            childAt2.layout(r42, r23, measuredWidth2 + r42, measuredHeight2 + r23);
            return;
        }
        int r102 = r10 - (r9 ^ 1);
        int max = Math.max(0, r102 > 0 ? paddingRight / r102 : 0);
        if (isLayoutRtl) {
            int width2 = getWidth() - getPaddingRight();
            for (int r72 = 0; r72 < childCount; r72++) {
                View childAt3 = getChildAt(r72);
                LayoutParams layoutParams2 = (LayoutParams) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !layoutParams2.isOverflowButton) {
                    int r5 = width2 - layoutParams2.rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int r103 = r2 - (measuredHeight3 / 2);
                    childAt3.layout(r5 - measuredWidth3, r103, r5, measuredHeight3 + r103);
                    width2 = r5 - ((measuredWidth3 + layoutParams2.leftMargin) + max);
                }
            }
            return;
        }
        int paddingLeft = getPaddingLeft();
        for (int r73 = 0; r73 < childCount; r73++) {
            View childAt4 = getChildAt(r73);
            LayoutParams layoutParams3 = (LayoutParams) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !layoutParams3.isOverflowButton) {
                int r52 = paddingLeft + layoutParams3.leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int r104 = r2 - (measuredHeight4 / 2);
                childAt4.layout(r52, r104, r52 + measuredWidth4, measuredHeight4 + r104);
                paddingLeft = r52 + measuredWidth4 + layoutParams3.rightMargin + max;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dismissPopupMenus();
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        this.mPresenter.setOverflowIcon(drawable);
    }

    public Drawable getOverflowIcon() {
        getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        return layoutParams;
    }

    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        if (layoutParams != null) {
            if (layoutParams instanceof LayoutParams) {
                layoutParams2 = new LayoutParams((LayoutParams) layoutParams);
            } else {
                layoutParams2 = new LayoutParams(layoutParams);
            }
            if (layoutParams2.gravity <= 0) {
                layoutParams2.gravity = 16;
            }
            return layoutParams2;
        }
        return generateDefaultLayoutParams();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.widget.LinearLayoutCompat, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams generateDefaultLayoutParams = generateDefaultLayoutParams();
        generateDefaultLayoutParams.isOverflowButton = true;
        return generateDefaultLayoutParams;
    }

    @Override // androidx.appcompat.view.menu.MenuBuilder.ItemInvoker
    public boolean invokeItem(MenuItemImpl menuItemImpl) {
        return this.mMenu.performItemAction(menuItemImpl, 0);
    }

    @Override // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder) {
        this.mMenu = menuBuilder;
    }

    public Menu getMenu() {
        if (this.mMenu == null) {
            Context context = getContext();
            MenuBuilder menuBuilder = new MenuBuilder(context);
            this.mMenu = menuBuilder;
            menuBuilder.setCallback(new MenuBuilderCallback());
            ActionMenuPresenter actionMenuPresenter = new ActionMenuPresenter(context);
            this.mPresenter = actionMenuPresenter;
            actionMenuPresenter.setReserveOverflow(true);
            ActionMenuPresenter actionMenuPresenter2 = this.mPresenter;
            MenuPresenter.Callback callback = this.mActionMenuPresenterCallback;
            if (callback == null) {
                callback = new ActionMenuPresenterCallback();
            }
            actionMenuPresenter2.setCallback(callback);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    public void setMenuCallbacks(MenuPresenter.Callback callback, MenuBuilder.Callback callback2) {
        this.mActionMenuPresenterCallback = callback;
        this.mMenuBuilderCallback = callback2;
    }

    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public boolean showOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.showOverflowMenu();
    }

    public boolean hideOverflowMenu() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.hideOverflowMenu();
    }

    public boolean isOverflowMenuShowing() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowPending() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        return actionMenuPresenter != null && actionMenuPresenter.isOverflowMenuShowPending();
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter = this.mPresenter;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.dismissPopupMenus();
        }
    }

    protected boolean hasSupportDividerBeforeChildAt(int r5) {
        boolean z = false;
        if (r5 == 0) {
            return false;
        }
        View childAt = getChildAt(r5 - 1);
        View childAt2 = getChildAt(r5);
        if (r5 < getChildCount() && (childAt instanceof ActionMenuChildView)) {
            z = false | ((ActionMenuChildView) childAt).needsDividerAfter();
        }
        return (r5 <= 0 || !(childAt2 instanceof ActionMenuChildView)) ? z : z | ((ActionMenuChildView) childAt2).needsDividerBefore();
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.setExpandedActionViewsExclusive(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class MenuBuilderCallback implements MenuBuilder.Callback {
        MenuBuilderCallback() {
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
            return ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(menuItem);
        }

        @Override // androidx.appcompat.view.menu.MenuBuilder.Callback
        public void onMenuModeChange(MenuBuilder menuBuilder) {
            if (ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ActionMenuPresenterCallback implements MenuPresenter.Callback {
        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        @Override // androidx.appcompat.view.menu.MenuPresenter.Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }

        ActionMenuPresenterCallback() {
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.LayoutParams) layoutParams);
            this.isOverflowButton = layoutParams.isOverflowButton;
        }

        public LayoutParams(int r1, int r2) {
            super(r1, r2);
            this.isOverflowButton = false;
        }

        LayoutParams(int r1, int r2, boolean z) {
            super(r1, r2);
            this.isOverflowButton = z;
        }
    }
}
