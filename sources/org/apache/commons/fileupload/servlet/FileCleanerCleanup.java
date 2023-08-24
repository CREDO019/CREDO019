package org.apache.commons.fileupload.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.p028io.FileCleaningTracker;

/* loaded from: classes5.dex */
public class FileCleanerCleanup implements ServletContextListener {
    public static final String FILE_CLEANING_TRACKER_ATTRIBUTE = FileCleanerCleanup.class.getName() + ".FileCleaningTracker";

    public static FileCleaningTracker getFileCleaningTracker(ServletContext servletContext) {
        return (FileCleaningTracker) servletContext.getAttribute(FILE_CLEANING_TRACKER_ATTRIBUTE);
    }

    public static void setFileCleaningTracker(ServletContext servletContext, FileCleaningTracker fileCleaningTracker) {
        servletContext.setAttribute(FILE_CLEANING_TRACKER_ATTRIBUTE, fileCleaningTracker);
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        setFileCleaningTracker(servletContextEvent.getServletContext(), new FileCleaningTracker());
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        getFileCleaningTracker(servletContextEvent.getServletContext()).exitWhenFinished();
    }
}
