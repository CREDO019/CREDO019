package org.apache.commons.fileupload.servlet;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.UploadContext;

/* loaded from: classes5.dex */
public class ServletRequestContext implements UploadContext {
    private final HttpServletRequest request;

    public ServletRequestContext(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override // org.apache.commons.fileupload.RequestContext
    public String getCharacterEncoding() {
        return this.request.getCharacterEncoding();
    }

    @Override // org.apache.commons.fileupload.RequestContext
    public String getContentType() {
        return this.request.getContentType();
    }

    @Override // org.apache.commons.fileupload.RequestContext
    @Deprecated
    public int getContentLength() {
        return this.request.getContentLength();
    }

    @Override // org.apache.commons.fileupload.UploadContext
    public long contentLength() {
        try {
            return Long.parseLong(this.request.getHeader(FileUploadBase.CONTENT_LENGTH));
        } catch (NumberFormatException unused) {
            return this.request.getContentLength();
        }
    }

    @Override // org.apache.commons.fileupload.RequestContext
    public InputStream getInputStream() throws IOException {
        return this.request.getInputStream();
    }

    public String toString() {
        return String.format("ContentLength=%s, ContentType=%s", Long.valueOf(contentLength()), getContentType());
    }
}
