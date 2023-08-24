package com.google.android.exoplayer2.text.span;

/* loaded from: classes2.dex */
public final class RubySpan implements LanguageFeatureSpan {
    public final int position;
    public final String rubyText;

    public RubySpan(String str, int r2) {
        this.rubyText = str;
        this.position = r2;
    }
}
