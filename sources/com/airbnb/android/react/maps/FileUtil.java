package com.airbnb.android.react.maps;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/* loaded from: classes.dex */
public class FileUtil extends AsyncTask<String, Void, InputStream> {
    private Context context;

    public FileUtil(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public InputStream doInBackground(String... strArr) {
        try {
            Uri parse = Uri.parse(strArr[0]);
            if (parse.getScheme().startsWith("http")) {
                return getDownloadFileInputStream(this.context, parse);
            }
            return this.context.getContentResolver().openInputStream(parse);
        } catch (Exception e) {
            FLog.m1327e(ReactConstants.TAG, "Could not retrieve file for contentUri " + strArr[0], e);
            return null;
        }
    }

    private InputStream getDownloadFileInputStream(Context context, Uri uri) throws IOException {
        File createTempFile = File.createTempFile("FileUtil", "temp", context.getApplicationContext().getCacheDir());
        createTempFile.deleteOnExit();
        InputStream openStream = new URL(uri.toString()).openStream();
        try {
            ReadableByteChannel newChannel = Channels.newChannel(openStream);
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            try {
                fileOutputStream.getChannel().transferFrom(newChannel, 0L, Long.MAX_VALUE);
                FileInputStream fileInputStream = new FileInputStream(createTempFile);
                newChannel.close();
                return fileInputStream;
            } finally {
                fileOutputStream.close();
            }
        } finally {
            openStream.close();
        }
    }
}
