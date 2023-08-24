package expo.modules.camera.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FileSystemUtils.kt */
@Metadata(m184d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u001e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, m183d2 = {"Lexpo/modules/camera/utils/FileSystemUtils;", "", "()V", "ensureDirExists", "Ljava/io/File;", "dir", "generateOutputPath", "", "internalDirectory", "dirName", "extension", "expo-camera_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class FileSystemUtils {
    public static final FileSystemUtils INSTANCE = new FileSystemUtils();

    private FileSystemUtils() {
    }

    public final File ensureDirExists(File dir) throws IOException {
        Intrinsics.checkNotNullParameter(dir, "dir");
        if (dir.isDirectory() || dir.mkdirs()) {
            return dir;
        }
        throw new IOException("Couldn't create directory '" + dir + "'");
    }

    public final String generateOutputPath(File internalDirectory, String dirName, String extension) throws IOException {
        Intrinsics.checkNotNullParameter(internalDirectory, "internalDirectory");
        Intrinsics.checkNotNullParameter(dirName, "dirName");
        Intrinsics.checkNotNullParameter(extension, "extension");
        String file = internalDirectory.toString();
        String str = File.separator;
        File file2 = new File(file + str + dirName);
        ensureDirExists(file2);
        String str2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(str2, "randomUUID().toString()");
        String file3 = file2.toString();
        String str3 = File.separator;
        return file3 + str3 + str2 + extension;
    }
}
