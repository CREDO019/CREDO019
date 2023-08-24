package androidx.core.text;

import android.text.Html;
import android.text.Spanned;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m184d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a/\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0086\b\u001a\u0017\u0010\t\u001a\u00020\u0002*\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u0004H\u0086\b¨\u0006\u000b"}, m183d2 = {"parseAsHtml", "Landroid/text/Spanned;", "", "flags", "", "imageGetter", "Landroid/text/Html$ImageGetter;", "tagHandler", "Landroid/text/Html$TagHandler;", "toHtml", "option", "core-ktx_release"}, m182k = 2, m181mv = {1, 5, 1}, m179xi = 48)
/* renamed from: androidx.core.text.HtmlKt */
/* loaded from: classes.dex */
public final class Html {
    public static /* synthetic */ Spanned parseAsHtml$default(String str, int r2, Html.ImageGetter imageGetter, Html.TagHandler tagHandler, int r5, Object obj) {
        if ((r5 & 1) != 0) {
            r2 = 0;
        }
        if ((r5 & 2) != 0) {
            imageGetter = null;
        }
        if ((r5 & 4) != 0) {
            tagHandler = null;
        }
        Intrinsics.checkNotNullParameter(str, "<this>");
        Spanned fromHtml = HtmlCompat.fromHtml(str, r2, imageGetter, tagHandler);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
        return fromHtml;
    }

    public static final Spanned parseAsHtml(String str, int r2, Html.ImageGetter imageGetter, Html.TagHandler tagHandler) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Spanned fromHtml = HtmlCompat.fromHtml(str, r2, imageGetter, tagHandler);
        Intrinsics.checkNotNullExpressionValue(fromHtml, "fromHtml(this, flags, imageGetter, tagHandler)");
        return fromHtml;
    }

    public static /* synthetic */ String toHtml$default(Spanned spanned, int r1, int r2, Object obj) {
        if ((r2 & 1) != 0) {
            r1 = 0;
        }
        Intrinsics.checkNotNullParameter(spanned, "<this>");
        String html = HtmlCompat.toHtml(spanned, r1);
        Intrinsics.checkNotNullExpressionValue(html, "toHtml(this, option)");
        return html;
    }

    public static final String toHtml(Spanned spanned, int r2) {
        Intrinsics.checkNotNullParameter(spanned, "<this>");
        String html = HtmlCompat.toHtml(spanned, r2);
        Intrinsics.checkNotNullExpressionValue(html, "toHtml(this, option)");
        return html;
    }
}
