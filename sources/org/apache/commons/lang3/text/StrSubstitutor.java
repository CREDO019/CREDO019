package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

@Deprecated
/* loaded from: classes5.dex */
public class StrSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
    public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher("}");
    public static final StrMatcher DEFAULT_VALUE_DELIMITER = StrMatcher.stringMatcher(":-");
    private boolean enableSubstitutionInVariables;
    private char escapeChar;
    private StrMatcher prefixMatcher;
    private boolean preserveEscapes;
    private StrMatcher suffixMatcher;
    private StrMatcher valueDelimiterMatcher;
    private StrLookup<?> variableResolver;

    public static <V> String replace(Object obj, Map<String, V> map) {
        return new StrSubstitutor(map).replace(obj);
    }

    public static <V> String replace(Object obj, Map<String, V> map, String str, String str2) {
        return new StrSubstitutor(map, str, str2).replace(obj);
    }

    public static String replace(Object obj, Properties properties) {
        if (properties == null) {
            return obj.toString();
        }
        HashMap hashMap = new HashMap();
        Enumeration<?> propertyNames = properties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            hashMap.put(str, properties.getProperty(str));
        }
        return replace(obj, hashMap);
    }

    public static String replaceSystemProperties(Object obj) {
        return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(obj);
    }

    public StrSubstitutor() {
        this((StrLookup<?>) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public <V> StrSubstitutor(Map<String, V> map) {
        this((StrLookup<?>) StrLookup.mapLookup(map), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2) {
        this((StrLookup<?>) StrLookup.mapLookup(map), str, str2, '$');
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2, char c) {
        this(StrLookup.mapLookup(map), str, str2, c);
    }

    public <V> StrSubstitutor(Map<String, V> map, String str, String str2, char c, String str3) {
        this(StrLookup.mapLookup(map), str, str2, c, str3);
    }

    public StrSubstitutor(StrLookup<?> strLookup) {
        this(strLookup, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public StrSubstitutor(StrLookup<?> strLookup, String str, String str2, char c) {
        this.preserveEscapes = false;
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
        setValueDelimiterMatcher(DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> strLookup, String str, String str2, char c, String str3) {
        this.preserveEscapes = false;
        setVariableResolver(strLookup);
        setVariablePrefix(str);
        setVariableSuffix(str2);
        setEscapeChar(c);
        setValueDelimiter(str3);
    }

    public StrSubstitutor(StrLookup<?> strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c) {
        this(strLookup, strMatcher, strMatcher2, c, DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> strLookup, StrMatcher strMatcher, StrMatcher strMatcher2, char c, StrMatcher strMatcher3) {
        this.preserveEscapes = false;
        setVariableResolver(strLookup);
        setVariablePrefixMatcher(strMatcher);
        setVariableSuffixMatcher(strMatcher2);
        setEscapeChar(c);
        setValueDelimiterMatcher(strMatcher3);
    }

    public String replace(String str) {
        if (str == null) {
            return null;
        }
        StrBuilder strBuilder = new StrBuilder(str);
        return !substitute(strBuilder, 0, str.length()) ? str : strBuilder.toString();
    }

    public String replace(String str, int r4, int r5) {
        if (str == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(r5).append(str, r4, r5);
        if (!substitute(append, 0, r5)) {
            return str.substring(r4, r5 + r4);
        }
        return append.toString();
    }

    public String replace(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(cArr.length).append(cArr);
        substitute(append, 0, cArr.length);
        return append.toString();
    }

    public String replace(char[] cArr, int r3, int r4) {
        if (cArr == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(r4).append(cArr, r3, r4);
        substitute(append, 0, r4);
        return append.toString();
    }

    public String replace(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(stringBuffer.length()).append(stringBuffer);
        substitute(append, 0, append.length());
        return append.toString();
    }

    public String replace(StringBuffer stringBuffer, int r3, int r4) {
        if (stringBuffer == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(r4).append(stringBuffer, r3, r4);
        substitute(append, 0, r4);
        return append.toString();
    }

    public String replace(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return replace(charSequence, 0, charSequence.length());
    }

    public String replace(CharSequence charSequence, int r3, int r4) {
        if (charSequence == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(r4).append(charSequence, r3, r4);
        substitute(append, 0, r4);
        return append.toString();
    }

    public String replace(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(strBuilder.length()).append(strBuilder);
        substitute(append, 0, append.length());
        return append.toString();
    }

    public String replace(StrBuilder strBuilder, int r3, int r4) {
        if (strBuilder == null) {
            return null;
        }
        StrBuilder append = new StrBuilder(r4).append(strBuilder, r3, r4);
        substitute(append, 0, r4);
        return append.toString();
    }

    public String replace(Object obj) {
        if (obj == null) {
            return null;
        }
        StrBuilder append = new StrBuilder().append(obj);
        substitute(append, 0, append.length());
        return append.toString();
    }

    public boolean replaceIn(StringBuffer stringBuffer) {
        if (stringBuffer == null) {
            return false;
        }
        return replaceIn(stringBuffer, 0, stringBuffer.length());
    }

    public boolean replaceIn(StringBuffer stringBuffer, int r5, int r6) {
        if (stringBuffer == null) {
            return false;
        }
        StrBuilder append = new StrBuilder(r6).append(stringBuffer, r5, r6);
        if (substitute(append, 0, r6)) {
            stringBuffer.replace(r5, r6 + r5, append.toString());
            return true;
        }
        return false;
    }

    public boolean replaceIn(StringBuilder sb) {
        if (sb == null) {
            return false;
        }
        return replaceIn(sb, 0, sb.length());
    }

    public boolean replaceIn(StringBuilder sb, int r5, int r6) {
        if (sb == null) {
            return false;
        }
        StrBuilder append = new StrBuilder(r6).append(sb, r5, r6);
        if (substitute(append, 0, r6)) {
            sb.replace(r5, r6 + r5, append.toString());
            return true;
        }
        return false;
    }

    public boolean replaceIn(StrBuilder strBuilder) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, 0, strBuilder.length());
    }

    public boolean replaceIn(StrBuilder strBuilder, int r2, int r3) {
        if (strBuilder == null) {
            return false;
        }
        return substitute(strBuilder, r2, r3);
    }

    protected boolean substitute(StrBuilder strBuilder, int r3, int r4) {
        return substitute(strBuilder, r3, r4, null) > 0;
    }

    private int substitute(StrBuilder strBuilder, int r27, int r28, List<String> list) {
        StrMatcher strMatcher;
        StrMatcher strMatcher2;
        char c;
        boolean z;
        String str;
        int isMatch;
        StrMatcher variablePrefixMatcher = getVariablePrefixMatcher();
        StrMatcher variableSuffixMatcher = getVariableSuffixMatcher();
        char escapeChar = getEscapeChar();
        StrMatcher valueDelimiterMatcher = getValueDelimiterMatcher();
        boolean isEnableSubstitutionInVariables = isEnableSubstitutionInVariables();
        boolean z2 = list == null;
        int r15 = r27;
        int r14 = r27 + r28;
        int r16 = 0;
        int r17 = 0;
        char[] cArr = strBuilder.buffer;
        List<String> list2 = list;
        while (r15 < r14) {
            int isMatch2 = variablePrefixMatcher.isMatch(cArr, r15, r27, r14);
            if (isMatch2 != 0) {
                if (r15 > r27) {
                    int r10 = r15 - 1;
                    if (cArr[r10] == escapeChar) {
                        if (this.preserveEscapes) {
                            r15++;
                        } else {
                            strBuilder.deleteCharAt(r10);
                            r16--;
                            r14--;
                            strMatcher = variablePrefixMatcher;
                            strMatcher2 = variableSuffixMatcher;
                            c = escapeChar;
                            cArr = strBuilder.buffer;
                            z = z2;
                            r17 = 1;
                        }
                    }
                }
                int r9 = r15 + isMatch2;
                int r102 = r9;
                int r19 = 0;
                while (true) {
                    if (r102 >= r14) {
                        strMatcher = variablePrefixMatcher;
                        strMatcher2 = variableSuffixMatcher;
                        c = escapeChar;
                        z = z2;
                        r15 = r102;
                        break;
                    } else if (!isEnableSubstitutionInVariables || (isMatch = variablePrefixMatcher.isMatch(cArr, r102, r27, r14)) == 0) {
                        int isMatch3 = variableSuffixMatcher.isMatch(cArr, r102, r27, r14);
                        if (isMatch3 == 0) {
                            r102++;
                        } else if (r19 == 0) {
                            strMatcher2 = variableSuffixMatcher;
                            c = escapeChar;
                            String str2 = new String(cArr, r9, (r102 - r15) - isMatch2);
                            if (isEnableSubstitutionInVariables) {
                                StrBuilder strBuilder2 = new StrBuilder(str2);
                                substitute(strBuilder2, 0, strBuilder2.length());
                                str2 = strBuilder2.toString();
                            }
                            int r103 = r102 + isMatch3;
                            if (valueDelimiterMatcher != null) {
                                char[] charArray = str2.toCharArray();
                                z = z2;
                                int r6 = 0;
                                while (r6 < charArray.length && (isEnableSubstitutionInVariables || variablePrefixMatcher.isMatch(charArray, r6, r6, charArray.length) == 0)) {
                                    int isMatch4 = valueDelimiterMatcher.isMatch(charArray, r6);
                                    if (isMatch4 != 0) {
                                        strMatcher = variablePrefixMatcher;
                                        String substring = str2.substring(0, r6);
                                        str = str2.substring(r6 + isMatch4);
                                        str2 = substring;
                                        break;
                                    }
                                    r6++;
                                    variablePrefixMatcher = variablePrefixMatcher;
                                }
                                strMatcher = variablePrefixMatcher;
                            } else {
                                strMatcher = variablePrefixMatcher;
                                z = z2;
                            }
                            str = null;
                            if (list2 == null) {
                                list2 = new ArrayList<>();
                                list2.add(new String(cArr, r27, r28));
                            }
                            checkCyclicSubstitution(str2, list2);
                            list2.add(str2);
                            String resolveVariable = resolveVariable(str2, strBuilder, r15, r103);
                            if (resolveVariable != null) {
                                str = resolveVariable;
                            }
                            if (str != null) {
                                int length = str.length();
                                strBuilder.replace(r15, r103, str);
                                int substitute = (substitute(strBuilder, r15, length, list2) + length) - (r103 - r15);
                                r14 += substitute;
                                r16 += substitute;
                                cArr = strBuilder.buffer;
                                r15 = r103 + substitute;
                                r17 = 1;
                            } else {
                                r15 = r103;
                            }
                            list2.remove(list2.size() - 1);
                        } else {
                            r19--;
                            r102 += isMatch3;
                            escapeChar = escapeChar;
                            variablePrefixMatcher = variablePrefixMatcher;
                        }
                    } else {
                        r19++;
                        r102 += isMatch;
                    }
                }
            } else {
                r15++;
                strMatcher = variablePrefixMatcher;
                strMatcher2 = variableSuffixMatcher;
                c = escapeChar;
                z = z2;
            }
            variableSuffixMatcher = strMatcher2;
            escapeChar = c;
            z2 = z;
            variablePrefixMatcher = strMatcher;
        }
        return z2 ? r17 : r16;
    }

    private void checkCyclicSubstitution(String str, List<String> list) {
        if (list.contains(str)) {
            StrBuilder strBuilder = new StrBuilder(256);
            strBuilder.append("Infinite loop in property interpolation of ");
            strBuilder.append(list.remove(0));
            strBuilder.append(": ");
            strBuilder.appendWithSeparators(list, "->");
            throw new IllegalStateException(strBuilder.toString());
        }
    }

    protected String resolveVariable(String str, StrBuilder strBuilder, int r3, int r4) {
        StrLookup<?> variableResolver = getVariableResolver();
        if (variableResolver == null) {
            return null;
        }
        return variableResolver.lookup(str);
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public void setEscapeChar(char c) {
        this.escapeChar = c;
    }

    public StrMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StrSubstitutor setVariablePrefixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable prefix matcher must not be null!");
        }
        this.prefixMatcher = strMatcher;
        return this;
    }

    public StrSubstitutor setVariablePrefix(char c) {
        return setVariablePrefixMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setVariablePrefix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Variable prefix must not be null!");
        }
        return setVariablePrefixMatcher(StrMatcher.stringMatcher(str));
    }

    public StrMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public StrSubstitutor setVariableSuffixMatcher(StrMatcher strMatcher) {
        if (strMatcher == null) {
            throw new IllegalArgumentException("Variable suffix matcher must not be null!");
        }
        this.suffixMatcher = strMatcher;
        return this;
    }

    public StrSubstitutor setVariableSuffix(char c) {
        return setVariableSuffixMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setVariableSuffix(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Variable suffix must not be null!");
        }
        return setVariableSuffixMatcher(StrMatcher.stringMatcher(str));
    }

    public StrMatcher getValueDelimiterMatcher() {
        return this.valueDelimiterMatcher;
    }

    public StrSubstitutor setValueDelimiterMatcher(StrMatcher strMatcher) {
        this.valueDelimiterMatcher = strMatcher;
        return this;
    }

    public StrSubstitutor setValueDelimiter(char c) {
        return setValueDelimiterMatcher(StrMatcher.charMatcher(c));
    }

    public StrSubstitutor setValueDelimiter(String str) {
        if (StringUtils.isEmpty(str)) {
            setValueDelimiterMatcher(null);
            return this;
        }
        return setValueDelimiterMatcher(StrMatcher.stringMatcher(str));
    }

    public StrLookup<?> getVariableResolver() {
        return this.variableResolver;
    }

    public void setVariableResolver(StrLookup<?> strLookup) {
        this.variableResolver = strLookup;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public void setEnableSubstitutionInVariables(boolean z) {
        this.enableSubstitutionInVariables = z;
    }

    public boolean isPreserveEscapes() {
        return this.preserveEscapes;
    }

    public void setPreserveEscapes(boolean z) {
        this.preserveEscapes = z;
    }
}
