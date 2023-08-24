package org.apache.commons.fileupload;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

@Deprecated
/* loaded from: classes5.dex */
public class DiskFileUpload extends FileUploadBase {
    private DefaultFileItemFactory fileItemFactory;

    @Deprecated
    public DiskFileUpload() {
        this.fileItemFactory = new DefaultFileItemFactory();
    }

    @Deprecated
    public DiskFileUpload(DefaultFileItemFactory defaultFileItemFactory) {
        this.fileItemFactory = defaultFileItemFactory;
    }

    @Override // org.apache.commons.fileupload.FileUploadBase
    @Deprecated
    public FileItemFactory getFileItemFactory() {
        return this.fileItemFactory;
    }

    @Override // org.apache.commons.fileupload.FileUploadBase
    @Deprecated
    public void setFileItemFactory(FileItemFactory fileItemFactory) {
        this.fileItemFactory = (DefaultFileItemFactory) fileItemFactory;
    }

    @Deprecated
    public int getSizeThreshold() {
        return this.fileItemFactory.getSizeThreshold();
    }

    @Deprecated
    public void setSizeThreshold(int r2) {
        this.fileItemFactory.setSizeThreshold(r2);
    }

    @Deprecated
    public String getRepositoryPath() {
        return this.fileItemFactory.getRepository().getPath();
    }

    @Deprecated
    public void setRepositoryPath(String str) {
        this.fileItemFactory.setRepository(new File(str));
    }

    @Deprecated
    public List<FileItem> parseRequest(HttpServletRequest httpServletRequest, int r2, long j, String str) throws FileUploadException {
        setSizeThreshold(r2);
        setSizeMax(j);
        setRepositoryPath(str);
        return parseRequest(httpServletRequest);
    }
}
