package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes2.dex */
public class AccountChangeEventsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<AccountChangeEventsRequest> CREATOR = new zzb();
    private final int zze;
    @Deprecated
    private String zzg;
    private int zzi;
    private Account zzk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountChangeEventsRequest(int r1, int r2, String str, Account account) {
        this.zze = r1;
        this.zzi = r2;
        this.zzg = str;
        if (account == null && !TextUtils.isEmpty(str)) {
            this.zzk = new Account(str, "com.google");
        } else {
            this.zzk = account;
        }
    }

    public AccountChangeEventsRequest() {
        this.zze = 1;
    }

    public AccountChangeEventsRequest setEventIndex(int r1) {
        this.zzi = r1;
        return this;
    }

    @Deprecated
    public AccountChangeEventsRequest setAccountName(String str) {
        this.zzg = str;
        return this;
    }

    @Deprecated
    public String getAccountName() {
        return this.zzg;
    }

    public AccountChangeEventsRequest setAccount(Account account) {
        this.zzk = account;
        return this;
    }

    public Account getAccount() {
        return this.zzk;
    }

    public int getEventIndex() {
        return this.zzi;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r6) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeInt(parcel, 2, this.zzi);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzk, r6, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
