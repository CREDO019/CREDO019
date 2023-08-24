package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.ArrayList;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.p028io.IOUtils;

/* loaded from: classes3.dex */
public final class AddressBookAUResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    public AddressBookParsedResult parse(Result result) {
        String massagedText = getMassagedText(result);
        if (massagedText.contains("MEMORY") && massagedText.contains(IOUtils.LINE_SEPARATOR_WINDOWS)) {
            String matchSinglePrefixedField = matchSinglePrefixedField("NAME1:", massagedText, CharUtils.f1567CR, true);
            String matchSinglePrefixedField2 = matchSinglePrefixedField("NAME2:", massagedText, CharUtils.f1567CR, true);
            String[] matchMultipleValuePrefix = matchMultipleValuePrefix("TEL", 3, massagedText, true);
            String[] matchMultipleValuePrefix2 = matchMultipleValuePrefix("MAIL", 3, massagedText, true);
            String matchSinglePrefixedField3 = matchSinglePrefixedField("MEMORY:", massagedText, CharUtils.f1567CR, false);
            String matchSinglePrefixedField4 = matchSinglePrefixedField("ADD:", massagedText, CharUtils.f1567CR, true);
            return new AddressBookParsedResult(maybeWrap(matchSinglePrefixedField), null, matchSinglePrefixedField2, matchMultipleValuePrefix, null, matchMultipleValuePrefix2, null, null, matchSinglePrefixedField3, matchSinglePrefixedField4 != null ? new String[]{matchSinglePrefixedField4} : null, null, null, null, null, null, null);
        }
        return null;
    }

    private static String[] matchMultipleValuePrefix(String str, int r6, String str2, boolean z) {
        ArrayList arrayList = null;
        for (int r0 = 1; r0 <= r6; r0++) {
            String matchSinglePrefixedField = matchSinglePrefixedField(str + r0 + ':', str2, CharUtils.f1567CR, z);
            if (matchSinglePrefixedField == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(r6);
            }
            arrayList.add(matchSinglePrefixedField);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
