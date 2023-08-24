package org.apache.commons.fileupload.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;

/* loaded from: classes5.dex */
public class ServletFileUpload extends FileUpload {
    private static final String POST_METHOD = "POST";

    public static final boolean isMultipartContent(HttpServletRequest httpServletRequest) {
        if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            return FileUploadBase.isMultipartContent(new ServletRequestContext(httpServletRequest));
        }
        return false;
    }

    public ServletFileUpload() {
    }

    public ServletFileUpload(FileItemFactory fileItemFactory) {
        super(fileItemFactory);
    }

    @Override // org.apache.commons.fileupload.FileUploadBase
    public List<FileItem> parseRequest(HttpServletRequest httpServletRequest) throws FileUploadException {
        return parseRequest(new ServletRequestContext(httpServletRequest));
    }

    public Map<String, List<FileItem>> parseParameterMap(HttpServletRequest httpServletRequest) throws FileUploadException {
        return parseParameterMap(new ServletRequestContext(httpServletRequest));
    }

    public FileItemIterator getItemIterator(HttpServletRequest httpServletRequest) throws FileUploadException, IOException {
        return super.getItemIterator(new ServletRequestContext(httpServletRequest));
    }
}
