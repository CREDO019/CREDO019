package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.util.JSStackTrace;
import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class StackTraceHelper {
    public static final String COLUMN_KEY = "column";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    private static final Pattern STACK_FRAME_PATTERN1 = Pattern.compile("^(?:(.*?)@)?(.*?)\\:([0-9]+)\\:([0-9]+)$");
    private static final Pattern STACK_FRAME_PATTERN2 = Pattern.compile("\\s*(?:at)\\s*(.+?)\\s*[@(](.*):([0-9]+):([0-9]+)[)]$");

    /* loaded from: classes.dex */
    public static class StackFrameImpl implements StackFrame {
        private final int mColumn;
        private final String mFile;
        private final String mFileName;
        private final boolean mIsCollapsed;
        private final int mLine;
        private final String mMethod;

        private StackFrameImpl(String str, String str2, int r3, int r4, boolean z) {
            this.mFile = str;
            this.mMethod = str2;
            this.mLine = r3;
            this.mColumn = r4;
            this.mFileName = str != null ? new File(str).getName() : "";
            this.mIsCollapsed = z;
        }

        private StackFrameImpl(String str, String str2, int r9, int r10) {
            this(str, str2, r9, r10, false);
        }

        private StackFrameImpl(String str, String str2, String str3, int r4, int r5) {
            this.mFile = str;
            this.mFileName = str2;
            this.mMethod = str3;
            this.mLine = r4;
            this.mColumn = r5;
            this.mIsCollapsed = false;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFile() {
            return this.mFile;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getMethod() {
            return this.mMethod;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getLine() {
            return this.mLine;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public int getColumn() {
            return this.mColumn;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public String getFileName() {
            return this.mFileName;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public boolean isCollapsed() {
            return this.mIsCollapsed;
        }

        @Override // com.facebook.react.devsupport.interfaces.StackFrame
        public JSONObject toJSON() {
            return new JSONObject(MapBuilder.m1257of("file", getFile(), JSStackTrace.METHOD_NAME_KEY, getMethod(), "lineNumber", Integer.valueOf(getLine()), "column", Integer.valueOf(getColumn()), "collapse", Boolean.valueOf(isCollapsed())));
        }
    }

    public static StackFrame[] convertJsStackTrace(ReadableArray readableArray) {
        int size = readableArray != null ? readableArray.size() : 0;
        StackFrame[] stackFrameArr = new StackFrame[size];
        for (int r3 = 0; r3 < size; r3++) {
            ReadableType type = readableArray.getType(r3);
            if (type == ReadableType.Map) {
                ReadableMap map = readableArray.getMap(r3);
                String string = map.getString(JSStackTrace.METHOD_NAME_KEY);
                stackFrameArr[r3] = new StackFrameImpl(map.getString("file"), string, (!map.hasKey("lineNumber") || map.isNull("lineNumber")) ? -1 : map.getInt("lineNumber"), (!map.hasKey("column") || map.isNull("column")) ? -1 : map.getInt("column"), map.hasKey("collapse") && !map.isNull("collapse") && map.getBoolean("collapse"));
            } else if (type == ReadableType.String) {
                stackFrameArr[r3] = new StackFrameImpl((String) null, readableArray.getString(r3), -1, -1);
            }
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(JSONArray jSONArray) {
        int length = jSONArray != null ? jSONArray.length() : 0;
        StackFrame[] stackFrameArr = new StackFrame[length];
        for (int r7 = 0; r7 < length; r7++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(r7);
                stackFrameArr[r7] = new StackFrameImpl(jSONObject.getString("file"), jSONObject.getString(JSStackTrace.METHOD_NAME_KEY), (!jSONObject.has("lineNumber") || jSONObject.isNull("lineNumber")) ? -1 : jSONObject.getInt("lineNumber"), (!jSONObject.has("column") || jSONObject.isNull("column")) ? -1 : jSONObject.getInt("column"), jSONObject.has("collapse") && !jSONObject.isNull("collapse") && jSONObject.getBoolean("collapse"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJsStackTrace(String str) {
        String[] split = str.split("\n");
        StackFrame[] stackFrameArr = new StackFrame[split.length];
        for (int r2 = 0; r2 < split.length; r2++) {
            Matcher matcher = STACK_FRAME_PATTERN1.matcher(split[r2]);
            Matcher matcher2 = STACK_FRAME_PATTERN2.matcher(split[r2]);
            if (matcher2.find()) {
                matcher = matcher2;
            } else if (!matcher.find()) {
                stackFrameArr[r2] = new StackFrameImpl((String) null, split[r2], -1, -1);
            }
            stackFrameArr[r2] = new StackFrameImpl(matcher.group(2), matcher.group(1) == null ? "(unknown)" : matcher.group(1), Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        }
        return stackFrameArr;
    }

    public static StackFrame[] convertJavaStackTrace(Throwable th) {
        StackTraceElement[] stackTrace = th.getStackTrace();
        StackFrame[] stackFrameArr = new StackFrame[stackTrace.length];
        for (int r1 = 0; r1 < stackTrace.length; r1++) {
            stackFrameArr[r1] = new StackFrameImpl(stackTrace[r1].getClassName(), stackTrace[r1].getFileName(), stackTrace[r1].getMethodName(), stackTrace[r1].getLineNumber(), -1);
        }
        return stackFrameArr;
    }

    public static String formatFrameSource(StackFrame stackFrame) {
        StringBuilder sb = new StringBuilder();
        sb.append(stackFrame.getFileName());
        int line = stackFrame.getLine();
        if (line > 0) {
            sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
            sb.append(line);
            int column = stackFrame.getColumn();
            if (column > 0) {
                sb.append(ParameterizedMessage.ERROR_MSG_SEPARATOR);
                sb.append(column);
            }
        }
        return sb.toString();
    }

    public static String formatStackTrace(String str, StackFrame[] stackFrameArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("\n");
        for (StackFrame stackFrame : stackFrameArr) {
            sb.append(stackFrame.getMethod());
            sb.append("\n");
            sb.append("    ");
            sb.append(formatFrameSource(stackFrame));
            sb.append("\n");
        }
        return sb.toString();
    }
}
