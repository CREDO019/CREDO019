package org.apache.commons.fileupload;

import java.io.File;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

@Deprecated
/* loaded from: classes5.dex */
public class DefaultFileItemFactory extends DiskFileItemFactory {
    @Deprecated
    public DefaultFileItemFactory() {
    }

    @Deprecated
    public DefaultFileItemFactory(int r1, File file) {
        super(r1, file);
    }

    @Override // org.apache.commons.fileupload.disk.DiskFileItemFactory, org.apache.commons.fileupload.FileItemFactory
    @Deprecated
    public FileItem createItem(String str, String str2, boolean z, String str3) {
        return new DefaultFileItem(str, str2, z, str3, getSizeThreshold(), getRepository());
    }
}
