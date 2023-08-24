package expo.modules.documentpicker;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p023io.Closeable;

/* compiled from: DocumentDetailsReader.kt */
@Metadata(m184d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m183d2 = {"Lexpo/modules/documentpicker/DocumentDetailsReader;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "read", "Lexpo/modules/documentpicker/DocumentDetails;", "uri", "Landroid/net/Uri;", "expo-document-picker_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class DocumentDetailsReader {
    private final Context context;

    public DocumentDetailsReader(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final DocumentDetails read(Uri uri) {
        Integer num;
        Intrinsics.checkNotNullParameter(uri, "uri");
        Cursor query = this.context.getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            return null;
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            cursor2.moveToFirst();
            String name = cursor2.getString(cursor2.getColumnIndex("_display_name"));
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
            int columnIndex = cursor2.getColumnIndex("_size");
            if (!cursor2.isNull(columnIndex)) {
                num = Integer.valueOf(cursor2.getInt(columnIndex));
            } else {
                Integer num2 = null;
                num = null;
            }
            String type = this.context.getContentResolver().getType(uri);
            Intrinsics.checkNotNullExpressionValue(name, "name");
            DocumentDetails documentDetails = new DocumentDetails(name, uri2, num, type);
            Closeable.closeFinally(cursor, null);
            return documentDetails;
        } finally {
        }
    }
}
