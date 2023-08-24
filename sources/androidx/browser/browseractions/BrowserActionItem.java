package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.net.Uri;

@Deprecated
/* loaded from: classes.dex */
public class BrowserActionItem {
    private final PendingIntent mAction;
    private int mIconId;
    private Uri mIconUri;
    private Runnable mRunnableAction;
    private final String mTitle;

    public BrowserActionItem(String str, PendingIntent pendingIntent, int r3) {
        this.mTitle = str;
        this.mAction = pendingIntent;
        this.mIconId = r3;
    }

    public BrowserActionItem(String str, PendingIntent pendingIntent, Uri uri) {
        this.mTitle = str;
        this.mAction = pendingIntent;
        this.mIconUri = uri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BrowserActionItem(String str, Runnable runnable) {
        this.mTitle = str;
        this.mAction = null;
        this.mRunnableAction = runnable;
    }

    public BrowserActionItem(String str, PendingIntent pendingIntent) {
        this(str, pendingIntent, 0);
    }

    public int getIconId() {
        return this.mIconId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public PendingIntent getAction() {
        PendingIntent pendingIntent = this.mAction;
        if (pendingIntent != null) {
            return pendingIntent;
        }
        throw new IllegalStateException("Can't call getAction on BrowserActionItem with null action.");
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Runnable getRunnableAction() {
        return this.mRunnableAction;
    }
}
