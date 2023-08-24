package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import java.util.ArrayList;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class BackStackRecordState implements Parcelable {
    public static final Parcelable.Creator<BackStackRecordState> CREATOR = new Parcelable.Creator<BackStackRecordState>() { // from class: androidx.fragment.app.BackStackRecordState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackRecordState createFromParcel(Parcel parcel) {
            return new BackStackRecordState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BackStackRecordState[] newArray(int r1) {
            return new BackStackRecordState[r1];
        }
    };
    private static final String TAG = "FragmentManager";
    final int mBreadCrumbShortTitleRes;
    final CharSequence mBreadCrumbShortTitleText;
    final int mBreadCrumbTitleRes;
    final CharSequence mBreadCrumbTitleText;
    final int[] mCurrentMaxLifecycleStates;
    final ArrayList<String> mFragmentWhos;
    final int mIndex;
    final String mName;
    final int[] mOldMaxLifecycleStates;
    final int[] mOps;
    final boolean mReorderingAllowed;
    final ArrayList<String> mSharedElementSourceNames;
    final ArrayList<String> mSharedElementTargetNames;
    final int mTransition;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackStackRecordState(BackStackRecord backStackRecord) {
        int size = backStackRecord.mOps.size();
        this.mOps = new int[size * 6];
        if (!backStackRecord.mAddToBackStack) {
            throw new IllegalStateException("Not on back stack");
        }
        this.mFragmentWhos = new ArrayList<>(size);
        this.mOldMaxLifecycleStates = new int[size];
        this.mCurrentMaxLifecycleStates = new int[size];
        int r1 = 0;
        int r2 = 0;
        while (r1 < size) {
            FragmentTransaction.C0425Op c0425Op = backStackRecord.mOps.get(r1);
            int r5 = r2 + 1;
            this.mOps[r2] = c0425Op.mCmd;
            this.mFragmentWhos.add(c0425Op.mFragment != null ? c0425Op.mFragment.mWho : null);
            int r4 = r5 + 1;
            this.mOps[r5] = c0425Op.mFromExpandedOp ? 1 : 0;
            int r52 = r4 + 1;
            this.mOps[r4] = c0425Op.mEnterAnim;
            int r42 = r52 + 1;
            this.mOps[r52] = c0425Op.mExitAnim;
            int r53 = r42 + 1;
            this.mOps[r42] = c0425Op.mPopEnterAnim;
            this.mOps[r53] = c0425Op.mPopExitAnim;
            this.mOldMaxLifecycleStates[r1] = c0425Op.mOldMaxState.ordinal();
            this.mCurrentMaxLifecycleStates[r1] = c0425Op.mCurrentMaxState.ordinal();
            r1++;
            r2 = r53 + 1;
        }
        this.mTransition = backStackRecord.mTransition;
        this.mName = backStackRecord.mName;
        this.mIndex = backStackRecord.mIndex;
        this.mBreadCrumbTitleRes = backStackRecord.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = backStackRecord.mBreadCrumbTitleText;
        this.mBreadCrumbShortTitleRes = backStackRecord.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = backStackRecord.mBreadCrumbShortTitleText;
        this.mSharedElementSourceNames = backStackRecord.mSharedElementSourceNames;
        this.mSharedElementTargetNames = backStackRecord.mSharedElementTargetNames;
        this.mReorderingAllowed = backStackRecord.mReorderingAllowed;
    }

    BackStackRecordState(Parcel parcel) {
        this.mOps = parcel.createIntArray();
        this.mFragmentWhos = parcel.createStringArrayList();
        this.mOldMaxLifecycleStates = parcel.createIntArray();
        this.mCurrentMaxLifecycleStates = parcel.createIntArray();
        this.mTransition = parcel.readInt();
        this.mName = parcel.readString();
        this.mIndex = parcel.readInt();
        this.mBreadCrumbTitleRes = parcel.readInt();
        this.mBreadCrumbTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mBreadCrumbShortTitleRes = parcel.readInt();
        this.mBreadCrumbShortTitleText = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSharedElementSourceNames = parcel.createStringArrayList();
        this.mSharedElementTargetNames = parcel.createStringArrayList();
        this.mReorderingAllowed = parcel.readInt() != 0;
    }

    public BackStackRecord instantiate(FragmentManager fragmentManager) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        fillInBackStackRecord(backStackRecord);
        backStackRecord.mIndex = this.mIndex;
        for (int r1 = 0; r1 < this.mFragmentWhos.size(); r1++) {
            String str = this.mFragmentWhos.get(r1);
            if (str != null) {
                backStackRecord.mOps.get(r1).mFragment = fragmentManager.findActiveFragment(str);
            }
        }
        backStackRecord.bumpBackStackNesting(1);
        return backStackRecord;
    }

    public BackStackRecord instantiate(FragmentManager fragmentManager, Map<String, Fragment> map) {
        BackStackRecord backStackRecord = new BackStackRecord(fragmentManager);
        fillInBackStackRecord(backStackRecord);
        for (int r4 = 0; r4 < this.mFragmentWhos.size(); r4++) {
            String str = this.mFragmentWhos.get(r4);
            if (str != null) {
                Fragment fragment = map.get(str);
                if (fragment != null) {
                    backStackRecord.mOps.get(r4).mFragment = fragment;
                } else {
                    throw new IllegalStateException("Restoring FragmentTransaction " + this.mName + " failed due to missing saved state for Fragment (" + str + ")");
                }
            }
        }
        return backStackRecord;
    }

    private void fillInBackStackRecord(BackStackRecord backStackRecord) {
        int r1 = 0;
        int r2 = 0;
        while (true) {
            boolean z = true;
            if (r1 < this.mOps.length) {
                FragmentTransaction.C0425Op c0425Op = new FragmentTransaction.C0425Op();
                int r6 = r1 + 1;
                c0425Op.mCmd = this.mOps[r1];
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Instantiate " + backStackRecord + " op #" + r2 + " base fragment #" + this.mOps[r6]);
                }
                c0425Op.mOldMaxState = Lifecycle.State.values()[this.mOldMaxLifecycleStates[r2]];
                c0425Op.mCurrentMaxState = Lifecycle.State.values()[this.mCurrentMaxLifecycleStates[r2]];
                int r5 = r6 + 1;
                if (this.mOps[r6] == 0) {
                    z = false;
                }
                c0425Op.mFromExpandedOp = z;
                int r4 = r5 + 1;
                c0425Op.mEnterAnim = this.mOps[r5];
                int r52 = r4 + 1;
                c0425Op.mExitAnim = this.mOps[r4];
                int r42 = r52 + 1;
                c0425Op.mPopEnterAnim = this.mOps[r52];
                c0425Op.mPopExitAnim = this.mOps[r42];
                backStackRecord.mEnterAnim = c0425Op.mEnterAnim;
                backStackRecord.mExitAnim = c0425Op.mExitAnim;
                backStackRecord.mPopEnterAnim = c0425Op.mPopEnterAnim;
                backStackRecord.mPopExitAnim = c0425Op.mPopExitAnim;
                backStackRecord.addOp(c0425Op);
                r2++;
                r1 = r42 + 1;
            } else {
                backStackRecord.mTransition = this.mTransition;
                backStackRecord.mName = this.mName;
                backStackRecord.mAddToBackStack = true;
                backStackRecord.mBreadCrumbTitleRes = this.mBreadCrumbTitleRes;
                backStackRecord.mBreadCrumbTitleText = this.mBreadCrumbTitleText;
                backStackRecord.mBreadCrumbShortTitleRes = this.mBreadCrumbShortTitleRes;
                backStackRecord.mBreadCrumbShortTitleText = this.mBreadCrumbShortTitleText;
                backStackRecord.mSharedElementSourceNames = this.mSharedElementSourceNames;
                backStackRecord.mSharedElementTargetNames = this.mSharedElementTargetNames;
                backStackRecord.mReorderingAllowed = this.mReorderingAllowed;
                return;
            }
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int r3) {
        parcel.writeIntArray(this.mOps);
        parcel.writeStringList(this.mFragmentWhos);
        parcel.writeIntArray(this.mOldMaxLifecycleStates);
        parcel.writeIntArray(this.mCurrentMaxLifecycleStates);
        parcel.writeInt(this.mTransition);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mIndex);
        parcel.writeInt(this.mBreadCrumbTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbTitleText, parcel, 0);
        parcel.writeInt(this.mBreadCrumbShortTitleRes);
        TextUtils.writeToParcel(this.mBreadCrumbShortTitleText, parcel, 0);
        parcel.writeStringList(this.mSharedElementSourceNames);
        parcel.writeStringList(this.mSharedElementTargetNames);
        parcel.writeInt(this.mReorderingAllowed ? 1 : 0);
    }
}
