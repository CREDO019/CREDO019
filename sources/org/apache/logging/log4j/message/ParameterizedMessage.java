package org.apache.logging.log4j.message;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class ParameterizedMessage implements Message {
    private static final char DELIM_START = '{';
    private static final char DELIM_STOP = '}';
    public static final String ERROR_MSG_SEPARATOR = ":";
    public static final String ERROR_PREFIX = "[!!!";
    public static final String ERROR_SEPARATOR = "=>";
    public static final String ERROR_SUFFIX = "!!!]";
    private static final char ESCAPE_CHAR = '\\';
    private static final int HASHVAL = 31;
    public static final String RECURSION_PREFIX = "[...";
    public static final String RECURSION_SUFFIX = "...]";
    private static final long serialVersionUID = -665975803997290697L;
    private transient Object[] argArray;
    private transient String formattedMessage;
    private final String messagePattern;
    private final String[] stringArgs;
    private transient Throwable throwable;

    public ParameterizedMessage(String str, String[] strArr, Throwable th) {
        this.messagePattern = str;
        this.stringArgs = strArr;
        this.throwable = th;
    }

    public ParameterizedMessage(String str, Object[] objArr, Throwable th) {
        this.messagePattern = str;
        this.throwable = th;
        this.stringArgs = argumentsToStrings(objArr);
    }

    public ParameterizedMessage(String str, Object[] objArr) {
        this.messagePattern = str;
        this.stringArgs = argumentsToStrings(objArr);
    }

    public ParameterizedMessage(String str, Object obj) {
        this(str, new Object[]{obj});
    }

    public ParameterizedMessage(String str, Object obj, Object obj2) {
        this(str, new Object[]{obj, obj2});
    }

    private String[] argumentsToStrings(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        int countArgumentPlaceholders = countArgumentPlaceholders(this.messagePattern);
        int length = objArr.length;
        if (countArgumentPlaceholders < objArr.length && this.throwable == null && (objArr[objArr.length - 1] instanceof Throwable)) {
            this.throwable = (Throwable) objArr[objArr.length - 1];
            length--;
        }
        Object[] objArr2 = new Object[length];
        this.argArray = objArr2;
        System.arraycopy(objArr, 0, objArr2, 0, length);
        if (countArgumentPlaceholders == 1 && this.throwable == null && objArr.length > 1) {
            return new String[]{deepToString(objArr)};
        }
        String[] strArr = new String[length];
        for (int r4 = 0; r4 < length; r4++) {
            strArr[r4] = deepToString(objArr[r4]);
        }
        return strArr;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormattedMessage() {
        if (this.formattedMessage == null) {
            this.formattedMessage = formatMessage(this.messagePattern, this.stringArgs);
        }
        return this.formattedMessage;
    }

    @Override // org.apache.logging.log4j.message.Message
    public String getFormat() {
        return this.messagePattern;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Object[] getParameters() {
        Object[] objArr = this.argArray;
        return objArr != null ? objArr : this.stringArgs;
    }

    @Override // org.apache.logging.log4j.message.Message
    public Throwable getThrowable() {
        return this.throwable;
    }

    protected String formatMessage(String str, String[] strArr) {
        return format(str, strArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ParameterizedMessage parameterizedMessage = (ParameterizedMessage) obj;
        String str = this.messagePattern;
        if (str == null ? parameterizedMessage.messagePattern == null : str.equals(parameterizedMessage.messagePattern)) {
            return Arrays.equals(this.stringArgs, parameterizedMessage.stringArgs);
        }
        return false;
    }

    public int hashCode() {
        String str = this.messagePattern;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String[] strArr = this.stringArgs;
        return hashCode + (strArr != null ? Arrays.hashCode(strArr) : 0);
    }

    public static String format(String str, Object[] objArr) {
        if (str == null || objArr == null || objArr.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
        while (r2 < str.length()) {
            char charAt = str.charAt(r2);
            if (charAt == '\\') {
                r3++;
            } else {
                if (charAt == '{' && r2 < str.length() - 1) {
                    int r9 = r2 + 1;
                    if (str.charAt(r9) == '}') {
                        int r22 = r3 / 2;
                        for (int r5 = 0; r5 < r22; r5++) {
                            sb.append('\\');
                        }
                        if (r3 % 2 == 1) {
                            sb.append(DELIM_START);
                            sb.append(DELIM_STOP);
                        } else {
                            if (r4 < objArr.length) {
                                sb.append(objArr[r4]);
                            } else {
                                sb.append(DELIM_START);
                                sb.append(DELIM_STOP);
                            }
                            r4++;
                        }
                        r2 = r9;
                        r3 = 0;
                    }
                }
                if (r3 > 0) {
                    for (int r8 = 0; r8 < r3; r8++) {
                        sb.append('\\');
                    }
                    r3 = 0;
                }
                sb.append(charAt);
            }
            r2++;
        }
        return sb.toString();
    }

    public static int countArgumentPlaceholders(String str) {
        if (str == null || str.indexOf(123) == -1) {
            return 0;
        }
        int r2 = 0;
        int r3 = 0;
        boolean z = false;
        while (r2 < str.length()) {
            char charAt = str.charAt(r2);
            if (charAt == '\\') {
                z = !z;
            } else {
                if (charAt == '{' && !z && r2 < str.length() - 1) {
                    int r4 = r2 + 1;
                    if (str.charAt(r4) == '}') {
                        r3++;
                        r2 = r4;
                    }
                }
                z = false;
            }
            r2++;
        }
        return r3;
    }

    public static String deepToString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        StringBuilder sb = new StringBuilder();
        recursiveDeepToString(obj, sb, new HashSet());
        return sb.toString();
    }

    private static void recursiveDeepToString(Object obj, StringBuilder sb, Set<String> set) {
        Object[] objArr;
        if (obj == null) {
            sb.append("null");
        } else if (obj instanceof String) {
            sb.append(obj);
        } else {
            Class<?> cls = obj.getClass();
            boolean z = true;
            if (cls.isArray()) {
                if (cls == byte[].class) {
                    sb.append(Arrays.toString((byte[]) obj));
                } else if (cls == short[].class) {
                    sb.append(Arrays.toString((short[]) obj));
                } else if (cls == int[].class) {
                    sb.append(Arrays.toString((int[]) obj));
                } else if (cls == long[].class) {
                    sb.append(Arrays.toString((long[]) obj));
                } else if (cls == float[].class) {
                    sb.append(Arrays.toString((float[]) obj));
                } else if (cls == double[].class) {
                    sb.append(Arrays.toString((double[]) obj));
                } else if (cls == boolean[].class) {
                    sb.append(Arrays.toString((boolean[]) obj));
                } else if (cls == char[].class) {
                    sb.append(Arrays.toString((char[]) obj));
                } else {
                    String identityToString = identityToString(obj);
                    if (set.contains(identityToString)) {
                        sb.append(RECURSION_PREFIX);
                        sb.append(identityToString);
                        sb.append(RECURSION_SUFFIX);
                        return;
                    }
                    set.add(identityToString);
                    sb.append('[');
                    for (Object obj2 : (Object[]) obj) {
                        if (z) {
                            z = false;
                        } else {
                            sb.append(", ");
                        }
                        recursiveDeepToString(obj2, sb, new HashSet(set));
                    }
                    sb.append(']');
                }
            } else if (obj instanceof Map) {
                String identityToString2 = identityToString(obj);
                if (set.contains(identityToString2)) {
                    sb.append(RECURSION_PREFIX);
                    sb.append(identityToString2);
                    sb.append(RECURSION_SUFFIX);
                    return;
                }
                set.add(identityToString2);
                sb.append(DELIM_START);
                for (Map.Entry entry : ((Map) obj).entrySet()) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    recursiveDeepToString(key, sb, new HashSet(set));
                    sb.append('=');
                    recursiveDeepToString(value, sb, new HashSet(set));
                }
                sb.append(DELIM_STOP);
            } else if (obj instanceof Collection) {
                String identityToString3 = identityToString(obj);
                if (set.contains(identityToString3)) {
                    sb.append(RECURSION_PREFIX);
                    sb.append(identityToString3);
                    sb.append(RECURSION_SUFFIX);
                    return;
                }
                set.add(identityToString3);
                sb.append('[');
                for (Object obj3 : (Collection) obj) {
                    if (z) {
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    recursiveDeepToString(obj3, sb, new HashSet(set));
                }
                sb.append(']');
            } else if (obj instanceof Date) {
                sb.append(new SimpleDateFormat(StdDateFormat.DATE_FORMAT_STR_ISO8601).format((Date) obj));
            } else {
                try {
                    sb.append(obj.toString());
                } catch (Throwable th) {
                    sb.append(ERROR_PREFIX);
                    sb.append(identityToString(obj));
                    sb.append(ERROR_SEPARATOR);
                    String message = th.getMessage();
                    String name = th.getClass().getName();
                    sb.append(name);
                    if (!name.equals(message)) {
                        sb.append(ERROR_MSG_SEPARATOR);
                        sb.append(message);
                    }
                    sb.append(ERROR_SUFFIX);
                }
            }
        }
    }

    public static String identityToString(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
    }

    public String toString() {
        return "ParameterizedMessage[messagePattern=" + this.messagePattern + ", stringArgs=" + Arrays.toString(this.stringArgs) + ", throwable=" + this.throwable + ']';
    }
}
