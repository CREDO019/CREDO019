package io.nlopez.smartlocation.utils;

import android.util.Log;

/* loaded from: classes5.dex */
public class LoggerFactory {
    public static Logger buildLogger(boolean z) {
        return z ? new Blabber() : new Sssht();
    }

    /* loaded from: classes5.dex */
    private static class Sssht implements Logger {
        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: d */
        public void mo195d(String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: d */
        public void mo194d(Throwable th, String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: e */
        public void mo193e(String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: e */
        public void mo192e(Throwable th, String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: i */
        public void mo191i(String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: i */
        public void mo190i(Throwable th, String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: v */
        public void mo189v(String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: v */
        public void mo188v(Throwable th, String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: w */
        public void mo187w(String str, Object... objArr) {
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: w */
        public void mo186w(Throwable th, String str, Object... objArr) {
        }

        private Sssht() {
        }
    }

    /* loaded from: classes5.dex */
    private static class Blabber implements Logger {
        private Blabber() {
        }

        private String getTag() {
            return new Exception().getStackTrace()[3].getMethodName();
        }

        private String formatMessage(String str, Object... objArr) {
            return objArr.length == 0 ? str : String.format(str, objArr);
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: v */
        public void mo189v(String str, Object... objArr) {
            Log.v(getTag(), formatMessage(str, objArr));
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: v */
        public void mo188v(Throwable th, String str, Object... objArr) {
            Log.v(getTag(), formatMessage(str, objArr), th);
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: d */
        public void mo195d(String str, Object... objArr) {
            Log.d(getTag(), formatMessage(str, objArr));
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: d */
        public void mo194d(Throwable th, String str, Object... objArr) {
            Log.d(getTag(), formatMessage(str, objArr), th);
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: i */
        public void mo191i(String str, Object... objArr) {
            Log.i(getTag(), formatMessage(str, objArr));
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: i */
        public void mo190i(Throwable th, String str, Object... objArr) {
            Log.i(getTag(), formatMessage(str, objArr), th);
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: w */
        public void mo187w(String str, Object... objArr) {
            Log.w(getTag(), formatMessage(str, objArr));
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: w */
        public void mo186w(Throwable th, String str, Object... objArr) {
            Log.w(getTag(), formatMessage(str, objArr), th);
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: e */
        public void mo193e(String str, Object... objArr) {
            Log.e(getTag(), formatMessage(str, objArr));
        }

        @Override // io.nlopez.smartlocation.utils.Logger
        /* renamed from: e */
        public void mo192e(Throwable th, String str, Object... objArr) {
            Log.e(getTag(), formatMessage(str, objArr), th);
        }
    }
}
