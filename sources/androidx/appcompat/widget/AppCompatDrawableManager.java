package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.C0079R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.ResourceManagerInternal;
import androidx.core.graphics.ColorUtils;

/* loaded from: classes.dex */
public final class AppCompatDrawableManager {
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
    private static AppCompatDrawableManager INSTANCE = null;
    private static final String TAG = "AppCompatDrawableManag";
    private ResourceManagerInternal mResourceManager;

    public static synchronized void preload() {
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                AppCompatDrawableManager appCompatDrawableManager = new AppCompatDrawableManager();
                INSTANCE = appCompatDrawableManager;
                appCompatDrawableManager.mResourceManager = ResourceManagerInternal.get();
                INSTANCE.mResourceManager.setHooks(new ResourceManagerInternal.ResourceManagerHooks() { // from class: androidx.appcompat.widget.AppCompatDrawableManager.1
                    private final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL = {C0079R.C0081drawable.abc_textfield_search_default_mtrl_alpha, C0079R.C0081drawable.abc_textfield_default_mtrl_alpha, C0079R.C0081drawable.abc_ab_share_pack_mtrl_alpha};
                    private final int[] TINT_COLOR_CONTROL_NORMAL = {C0079R.C0081drawable.abc_ic_commit_search_api_mtrl_alpha, C0079R.C0081drawable.abc_seekbar_tick_mark_material, C0079R.C0081drawable.abc_ic_menu_share_mtrl_alpha, C0079R.C0081drawable.abc_ic_menu_copy_mtrl_am_alpha, C0079R.C0081drawable.abc_ic_menu_cut_mtrl_alpha, C0079R.C0081drawable.abc_ic_menu_selectall_mtrl_alpha, C0079R.C0081drawable.abc_ic_menu_paste_mtrl_am_alpha};
                    private final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED = {C0079R.C0081drawable.abc_textfield_activated_mtrl_alpha, C0079R.C0081drawable.abc_textfield_search_activated_mtrl_alpha, C0079R.C0081drawable.abc_cab_background_top_mtrl_alpha, C0079R.C0081drawable.abc_text_cursor_material, C0079R.C0081drawable.abc_text_select_handle_left_mtrl, C0079R.C0081drawable.abc_text_select_handle_middle_mtrl, C0079R.C0081drawable.abc_text_select_handle_right_mtrl};
                    private final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY = {C0079R.C0081drawable.abc_popup_background_mtrl_mult, C0079R.C0081drawable.abc_cab_background_internal_bg, C0079R.C0081drawable.abc_menu_hardkey_panel_mtrl_mult};
                    private final int[] TINT_COLOR_CONTROL_STATE_LIST = {C0079R.C0081drawable.abc_tab_indicator_material, C0079R.C0081drawable.abc_textfield_search_material};
                    private final int[] TINT_CHECKABLE_BUTTON_LIST = {C0079R.C0081drawable.abc_btn_check_material, C0079R.C0081drawable.abc_btn_radio_material, C0079R.C0081drawable.abc_btn_check_material_anim, C0079R.C0081drawable.abc_btn_radio_material_anim};

                    private ColorStateList createDefaultButtonColorStateList(Context context) {
                        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorButtonNormal));
                    }

                    private ColorStateList createBorderlessButtonColorStateList(Context context) {
                        return createButtonColorStateList(context, 0);
                    }

                    private ColorStateList createColoredButtonColorStateList(Context context) {
                        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorAccent));
                    }

                    private ColorStateList createButtonColorStateList(Context context, int r7) {
                        int themeAttrColor = ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlHighlight);
                        return new ColorStateList(new int[][]{ThemeUtils.DISABLED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{ThemeUtils.getDisabledThemeAttrColor(context, C0079R.attr.colorButtonNormal), ColorUtils.compositeColors(themeAttrColor, r7), ColorUtils.compositeColors(themeAttrColor, r7), r7});
                    }

                    private ColorStateList createSwitchThumbColorStateList(Context context) {
                        int[][] r1 = new int[3];
                        int[] r0 = new int[3];
                        ColorStateList themeAttrColorStateList = ThemeUtils.getThemeAttrColorStateList(context, C0079R.attr.colorSwitchThumbNormal);
                        if (themeAttrColorStateList != null && themeAttrColorStateList.isStateful()) {
                            r1[0] = ThemeUtils.DISABLED_STATE_SET;
                            r0[0] = themeAttrColorStateList.getColorForState(r1[0], 0);
                            r1[1] = ThemeUtils.CHECKED_STATE_SET;
                            r0[1] = ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlActivated);
                            r1[2] = ThemeUtils.EMPTY_STATE_SET;
                            r0[2] = themeAttrColorStateList.getDefaultColor();
                        } else {
                            r1[0] = ThemeUtils.DISABLED_STATE_SET;
                            r0[0] = ThemeUtils.getDisabledThemeAttrColor(context, C0079R.attr.colorSwitchThumbNormal);
                            r1[1] = ThemeUtils.CHECKED_STATE_SET;
                            r0[1] = ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlActivated);
                            r1[2] = ThemeUtils.EMPTY_STATE_SET;
                            r0[2] = ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorSwitchThumbNormal);
                        }
                        return new ColorStateList(r1, r0);
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public Drawable createDrawableFor(ResourceManagerInternal resourceManagerInternal, Context context, int r6) {
                        if (r6 == C0079R.C0081drawable.abc_cab_background_top_material) {
                            return new LayerDrawable(new Drawable[]{resourceManagerInternal.getDrawable(context, C0079R.C0081drawable.abc_cab_background_internal_bg), resourceManagerInternal.getDrawable(context, C0079R.C0081drawable.abc_cab_background_top_mtrl_alpha)});
                        }
                        if (r6 == C0079R.C0081drawable.abc_ratingbar_material) {
                            return getRatingBarLayerDrawable(resourceManagerInternal, context, C0079R.dimen.abc_star_big);
                        }
                        if (r6 == C0079R.C0081drawable.abc_ratingbar_indicator_material) {
                            return getRatingBarLayerDrawable(resourceManagerInternal, context, C0079R.dimen.abc_star_medium);
                        }
                        if (r6 == C0079R.C0081drawable.abc_ratingbar_small_material) {
                            return getRatingBarLayerDrawable(resourceManagerInternal, context, C0079R.dimen.abc_star_small);
                        }
                        return null;
                    }

                    private LayerDrawable getRatingBarLayerDrawable(ResourceManagerInternal resourceManagerInternal, Context context, int r7) {
                        BitmapDrawable bitmapDrawable;
                        BitmapDrawable bitmapDrawable2;
                        BitmapDrawable bitmapDrawable3;
                        int dimensionPixelSize = context.getResources().getDimensionPixelSize(r7);
                        Drawable drawable = resourceManagerInternal.getDrawable(context, C0079R.C0081drawable.abc_star_black_48dp);
                        Drawable drawable2 = resourceManagerInternal.getDrawable(context, C0079R.C0081drawable.abc_star_half_black_48dp);
                        if ((drawable instanceof BitmapDrawable) && drawable.getIntrinsicWidth() == dimensionPixelSize && drawable.getIntrinsicHeight() == dimensionPixelSize) {
                            bitmapDrawable = (BitmapDrawable) drawable;
                            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
                        } else {
                            Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                            Canvas canvas = new Canvas(createBitmap);
                            drawable.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                            drawable.draw(canvas);
                            bitmapDrawable = new BitmapDrawable(createBitmap);
                            bitmapDrawable2 = new BitmapDrawable(createBitmap);
                        }
                        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
                        if ((drawable2 instanceof BitmapDrawable) && drawable2.getIntrinsicWidth() == dimensionPixelSize && drawable2.getIntrinsicHeight() == dimensionPixelSize) {
                            bitmapDrawable3 = (BitmapDrawable) drawable2;
                        } else {
                            Bitmap createBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
                            Canvas canvas2 = new Canvas(createBitmap2);
                            drawable2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
                            drawable2.draw(canvas2);
                            bitmapDrawable3 = new BitmapDrawable(createBitmap2);
                        }
                        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
                        layerDrawable.setId(0, 16908288);
                        layerDrawable.setId(1, 16908303);
                        layerDrawable.setId(2, 16908301);
                        return layerDrawable;
                    }

                    private void setPorterDuffColorFilter(Drawable drawable, int r3, PorterDuff.Mode mode) {
                        if (DrawableUtils.canSafelyMutateDrawable(drawable)) {
                            drawable = drawable.mutate();
                        }
                        if (mode == null) {
                            mode = AppCompatDrawableManager.DEFAULT_MODE;
                        }
                        drawable.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(r3, mode));
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public boolean tintDrawable(Context context, int r7, Drawable drawable) {
                        if (r7 == C0079R.C0081drawable.abc_seekbar_track_material) {
                            LayerDrawable layerDrawable = (LayerDrawable) drawable;
                            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                            setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                            return true;
                        } else if (r7 == C0079R.C0081drawable.abc_ratingbar_material || r7 == C0079R.C0081drawable.abc_ratingbar_indicator_material || r7 == C0079R.C0081drawable.abc_ratingbar_small_material) {
                            LayerDrawable layerDrawable2 = (LayerDrawable) drawable;
                            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor(context, C0079R.attr.colorControlNormal), AppCompatDrawableManager.DEFAULT_MODE);
                            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                            setPorterDuffColorFilter(layerDrawable2.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor(context, C0079R.attr.colorControlActivated), AppCompatDrawableManager.DEFAULT_MODE);
                            return true;
                        } else {
                            return false;
                        }
                    }

                    private boolean arrayContains(int[] r5, int r6) {
                        for (int r3 : r5) {
                            if (r3 == r6) {
                                return true;
                            }
                        }
                        return false;
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public ColorStateList getTintListForDrawableRes(Context context, int r3) {
                        if (r3 == C0079R.C0081drawable.abc_edit_text_material) {
                            return AppCompatResources.getColorStateList(context, C0079R.C0080color.abc_tint_edittext);
                        }
                        if (r3 == C0079R.C0081drawable.abc_switch_track_mtrl_alpha) {
                            return AppCompatResources.getColorStateList(context, C0079R.C0080color.abc_tint_switch_track);
                        }
                        if (r3 == C0079R.C0081drawable.abc_switch_thumb_material) {
                            return createSwitchThumbColorStateList(context);
                        }
                        if (r3 == C0079R.C0081drawable.abc_btn_default_mtrl_shape) {
                            return createDefaultButtonColorStateList(context);
                        }
                        if (r3 == C0079R.C0081drawable.abc_btn_borderless_material) {
                            return createBorderlessButtonColorStateList(context);
                        }
                        if (r3 == C0079R.C0081drawable.abc_btn_colored_material) {
                            return createColoredButtonColorStateList(context);
                        }
                        if (r3 == C0079R.C0081drawable.abc_spinner_mtrl_am_alpha || r3 == C0079R.C0081drawable.abc_spinner_textfield_background_material) {
                            return AppCompatResources.getColorStateList(context, C0079R.C0080color.abc_tint_spinner);
                        }
                        if (arrayContains(this.TINT_COLOR_CONTROL_NORMAL, r3)) {
                            return ThemeUtils.getThemeAttrColorStateList(context, C0079R.attr.colorControlNormal);
                        }
                        if (arrayContains(this.TINT_COLOR_CONTROL_STATE_LIST, r3)) {
                            return AppCompatResources.getColorStateList(context, C0079R.C0080color.abc_tint_default);
                        }
                        if (arrayContains(this.TINT_CHECKABLE_BUTTON_LIST, r3)) {
                            return AppCompatResources.getColorStateList(context, C0079R.C0080color.abc_tint_btn_checkable);
                        }
                        if (r3 == C0079R.C0081drawable.abc_seekbar_thumb_material) {
                            return AppCompatResources.getColorStateList(context, C0079R.C0080color.abc_tint_seek_thumb);
                        }
                        return null;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
                    /* JADX WARN: Removed duplicated region for block: B:28:0x0061 A[RETURN] */
                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public boolean tintDrawableUsingColorFilter(android.content.Context r7, int r8, android.graphics.drawable.Drawable r9) {
                        /*
                            r6 = this;
                            android.graphics.PorterDuff$Mode r0 = androidx.appcompat.widget.AppCompatDrawableManager.access$000()
                            int[] r1 = r6.COLORFILTER_TINT_COLOR_CONTROL_NORMAL
                            boolean r1 = r6.arrayContains(r1, r8)
                            r2 = 16842801(0x1010031, float:2.3693695E-38)
                            r3 = -1
                            r4 = 0
                            r5 = 1
                            if (r1 == 0) goto L17
                            int r2 = androidx.appcompat.C0079R.attr.colorControlNormal
                        L14:
                            r8 = -1
                        L15:
                            r1 = 1
                            goto L44
                        L17:
                            int[] r1 = r6.COLORFILTER_COLOR_CONTROL_ACTIVATED
                            boolean r1 = r6.arrayContains(r1, r8)
                            if (r1 == 0) goto L22
                            int r2 = androidx.appcompat.C0079R.attr.colorControlActivated
                            goto L14
                        L22:
                            int[] r1 = r6.COLORFILTER_COLOR_BACKGROUND_MULTIPLY
                            boolean r1 = r6.arrayContains(r1, r8)
                            if (r1 == 0) goto L2d
                            android.graphics.PorterDuff$Mode r0 = android.graphics.PorterDuff.Mode.MULTIPLY
                            goto L14
                        L2d:
                            int r1 = androidx.appcompat.C0079R.C0081drawable.abc_list_divider_mtrl_alpha
                            if (r8 != r1) goto L3c
                            r2 = 16842800(0x1010030, float:2.3693693E-38)
                            r8 = 1109603123(0x42233333, float:40.8)
                            int r8 = java.lang.Math.round(r8)
                            goto L15
                        L3c:
                            int r1 = androidx.appcompat.C0079R.C0081drawable.abc_dialog_material_background
                            if (r8 != r1) goto L41
                            goto L14
                        L41:
                            r8 = -1
                            r1 = 0
                            r2 = 0
                        L44:
                            if (r1 == 0) goto L61
                            boolean r1 = androidx.appcompat.widget.DrawableUtils.canSafelyMutateDrawable(r9)
                            if (r1 == 0) goto L50
                            android.graphics.drawable.Drawable r9 = r9.mutate()
                        L50:
                            int r7 = androidx.appcompat.widget.ThemeUtils.getThemeAttrColor(r7, r2)
                            android.graphics.PorterDuffColorFilter r7 = androidx.appcompat.widget.AppCompatDrawableManager.getPorterDuffColorFilter(r7, r0)
                            r9.setColorFilter(r7)
                            if (r8 == r3) goto L60
                            r9.setAlpha(r8)
                        L60:
                            return r5
                        L61:
                            return r4
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatDrawableManager.C01401.tintDrawableUsingColorFilter(android.content.Context, int, android.graphics.drawable.Drawable):boolean");
                    }

                    @Override // androidx.appcompat.widget.ResourceManagerInternal.ResourceManagerHooks
                    public PorterDuff.Mode getTintModeForDrawableRes(int r2) {
                        if (r2 == C0079R.C0081drawable.abc_switch_thumb_material) {
                            return PorterDuff.Mode.MULTIPLY;
                        }
                        return null;
                    }
                });
            }
        }
    }

    public static synchronized AppCompatDrawableManager get() {
        AppCompatDrawableManager appCompatDrawableManager;
        synchronized (AppCompatDrawableManager.class) {
            if (INSTANCE == null) {
                preload();
            }
            appCompatDrawableManager = INSTANCE;
        }
        return appCompatDrawableManager;
    }

    public synchronized Drawable getDrawable(Context context, int r3) {
        return this.mResourceManager.getDrawable(context, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Drawable getDrawable(Context context, int r3, boolean z) {
        return this.mResourceManager.getDrawable(context, r3, z);
    }

    public synchronized void onConfigurationChanged(Context context) {
        this.mResourceManager.onConfigurationChanged(context);
    }

    synchronized Drawable onDrawableLoadedFromResources(Context context, VectorEnabledTintResources vectorEnabledTintResources, int r4) {
        return this.mResourceManager.onDrawableLoadedFromResources(context, vectorEnabledTintResources, r4);
    }

    boolean tintDrawableUsingColorFilter(Context context, int r3, Drawable drawable) {
        return this.mResourceManager.tintDrawableUsingColorFilter(context, r3, drawable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized ColorStateList getTintList(Context context, int r3) {
        return this.mResourceManager.getTintList(context, r3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void tintDrawable(Drawable drawable, TintInfo tintInfo, int[] r2) {
        ResourceManagerInternal.tintDrawable(drawable, tintInfo, r2);
    }

    public static synchronized PorterDuffColorFilter getPorterDuffColorFilter(int r1, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        synchronized (AppCompatDrawableManager.class) {
            porterDuffColorFilter = ResourceManagerInternal.getPorterDuffColorFilter(r1, mode);
        }
        return porterDuffColorFilter;
    }
}
