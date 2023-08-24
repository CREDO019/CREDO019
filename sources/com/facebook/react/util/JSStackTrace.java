package com.facebook.react.util;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.message.ParameterizedMessage;

/* loaded from: classes.dex */
public class JSStackTrace {
    public static final String COLUMN_KEY = "column";
    private static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");
    public static final String FILE_KEY = "file";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    public static final String METHOD_NAME_KEY = "methodName";

    public static String format(String str, ReadableArray readableArray) {
        StringBuilder sb = new StringBuilder(str);
        sb.append(", stack:\n");
        for (int r5 = 0; r5 < readableArray.size(); r5++) {
            ReadableMap map = readableArray.getMap(r5);
            sb.append(map.getString(METHOD_NAME_KEY));
            sb.append("@");
            sb.append(parseFileId(map));
            if (map.hasKey("lineNumber") && !map.isNull("lineNumber") && map.getType("lineNumber") == ReadableType.Number) {
                sb.append(map.getInt("lineNumber"));
            } else {
                sb.append(-1);
            }
            if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                sb.append(map.getInt("column"));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String parseFileId(ReadableMap readableMap) {
        String string;
        if (!readableMap.hasKey("file") || readableMap.isNull("file") || readableMap.getType("file") != ReadableType.String || (string = readableMap.getString("file")) == null) {
            return "";
        }
        Matcher matcher = FILE_ID_PATTERN.matcher(string);
        if (matcher.find()) {
            return matcher.group(1) + ParameterizedMessage.ERROR_MSG_SEPARATOR;
        }
        return "";
    }
}
