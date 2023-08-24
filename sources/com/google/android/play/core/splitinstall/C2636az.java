package com.google.android.play.core.splitinstall;

import android.util.Log;
import com.amplitude.api.Constants;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* renamed from: com.google.android.play.core.splitinstall.az */
/* loaded from: classes3.dex */
final class C2636az {

    /* renamed from: a */
    private final XmlPullParser f983a;

    /* renamed from: b */
    private final C2642g f984b = new C2642g();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2636az(XmlPullParser xmlPullParser) {
        this.f983a = xmlPullParser;
    }

    /* renamed from: a */
    private final String m534a(String str) {
        for (int r0 = 0; r0 < this.f983a.getAttributeCount(); r0++) {
            if (this.f983a.getAttributeName(r0).equals(str)) {
                return this.f983a.getAttributeValue(r0);
            }
        }
        return null;
    }

    /* renamed from: b */
    private final void m533b() throws IOException, XmlPullParserException {
        int r0 = 1;
        while (r0 != 0) {
            int next = this.f983a.next();
            if (next == 2) {
                r0++;
            } else if (next == 3) {
                r0--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final C2643h m535a() {
        String m534a;
        while (this.f983a.next() != 1) {
            try {
                if (this.f983a.getEventType() == 2) {
                    if (this.f983a.getName().equals("splits")) {
                        while (this.f983a.next() != 3) {
                            if (this.f983a.getEventType() == 2) {
                                if (!this.f983a.getName().equals("module") || (m534a = m534a("name")) == null) {
                                    m533b();
                                } else {
                                    while (this.f983a.next() != 3) {
                                        if (this.f983a.getEventType() == 2) {
                                            if (this.f983a.getName().equals(Constants.AMP_TRACKING_OPTION_LANGUAGE)) {
                                                while (this.f983a.next() != 3) {
                                                    if (this.f983a.getEventType() == 2) {
                                                        if (this.f983a.getName().equals("entry")) {
                                                            String m534a2 = m534a("key");
                                                            String m534a3 = m534a("split");
                                                            m533b();
                                                            if (m534a2 != null && m534a3 != null) {
                                                                this.f984b.m528a(m534a, m534a2, m534a3);
                                                            }
                                                        } else {
                                                            m533b();
                                                        }
                                                    }
                                                }
                                            } else {
                                                m533b();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        m533b();
                    }
                }
            } catch (IOException | IllegalStateException | XmlPullParserException e) {
                Log.e("SplitInstall", "Error while parsing splits.xml", e);
                return null;
            }
        }
        return this.f984b.m529a();
    }
}
