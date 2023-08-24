package org.apache.commons.fileupload;

import java.io.IOException;

/* loaded from: classes5.dex */
public interface FileItemIterator {
    boolean hasNext() throws FileUploadException, IOException;

    FileItemStream next() throws FileUploadException, IOException;
}
