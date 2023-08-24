package expo.modules.splashscreen;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashScreenView.kt */
@Metadata(m184d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, m183d2 = {"Lexpo/modules/splashscreen/SplashScreenView;", "Landroid/widget/RelativeLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "configureImageViewResizeMode", "", ViewProps.RESIZE_MODE, "Lexpo/modules/splashscreen/SplashScreenImageResizeMode;", "expo-splash-screen_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SplashScreenView extends RelativeLayout {
    private final ImageView imageView;

    /* compiled from: SplashScreenView.kt */
    @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] r0 = new int[SplashScreenImageResizeMode.values().length];
            r0[SplashScreenImageResizeMode.NATIVE.ordinal()] = 1;
            r0[SplashScreenImageResizeMode.CONTAIN.ordinal()] = 2;
            r0[SplashScreenImageResizeMode.COVER.ordinal()] = 3;
            $EnumSwitchMapping$0 = r0;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplashScreenView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.imageView = imageView;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        setClickable(true);
        addView(imageView);
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final void configureImageViewResizeMode(SplashScreenImageResizeMode resizeMode) {
        Intrinsics.checkNotNullParameter(resizeMode, "resizeMode");
        this.imageView.setScaleType(resizeMode.getScaleType());
        if (WhenMappings.$EnumSwitchMapping$0[resizeMode.ordinal()] != 2) {
            return;
        }
        this.imageView.setAdjustViewBounds(true);
    }
}
