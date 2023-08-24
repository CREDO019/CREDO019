package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class FragmentTransaction {
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_MAX_LIFECYCLE = 10;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    public static final int TRANSIT_ENTER_MASK = 4096;
    public static final int TRANSIT_EXIT_MASK = 8192;
    public static final int TRANSIT_FRAGMENT_CLOSE = 8194;
    public static final int TRANSIT_FRAGMENT_FADE = 4099;
    public static final int TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE = 8197;
    public static final int TRANSIT_FRAGMENT_MATCH_ACTIVITY_OPEN = 4100;
    public static final int TRANSIT_FRAGMENT_OPEN = 4097;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    private final ClassLoader mClassLoader;
    ArrayList<Runnable> mCommitRunnables;
    int mEnterAnim;
    int mExitAnim;
    private final FragmentFactory mFragmentFactory;
    String mName;
    ArrayList<C0425Op> mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    int mTransition;

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    @Deprecated
    public FragmentTransaction setTransitionStyle(int r1) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.fragment.app.FragmentTransaction$Op */
    /* loaded from: classes.dex */
    public static final class C0425Op {
        int mCmd;
        Lifecycle.State mCurrentMaxState;
        int mEnterAnim;
        int mExitAnim;
        Fragment mFragment;
        boolean mFromExpandedOp;
        Lifecycle.State mOldMaxState;
        int mPopEnterAnim;
        int mPopExitAnim;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0425Op() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0425Op(int r1, Fragment fragment) {
            this.mCmd = r1;
            this.mFragment = fragment;
            this.mFromExpandedOp = false;
            this.mOldMaxState = Lifecycle.State.RESUMED;
            this.mCurrentMaxState = Lifecycle.State.RESUMED;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0425Op(int r1, Fragment fragment, boolean z) {
            this.mCmd = r1;
            this.mFragment = fragment;
            this.mFromExpandedOp = z;
            this.mOldMaxState = Lifecycle.State.RESUMED;
            this.mCurrentMaxState = Lifecycle.State.RESUMED;
        }

        C0425Op(int r1, Fragment fragment, Lifecycle.State state) {
            this.mCmd = r1;
            this.mFragment = fragment;
            this.mFromExpandedOp = false;
            this.mOldMaxState = fragment.mMaxState;
            this.mCurrentMaxState = state;
        }

        C0425Op(C0425Op c0425Op) {
            this.mCmd = c0425Op.mCmd;
            this.mFragment = c0425Op.mFragment;
            this.mFromExpandedOp = c0425Op.mFromExpandedOp;
            this.mEnterAnim = c0425Op.mEnterAnim;
            this.mExitAnim = c0425Op.mExitAnim;
            this.mPopEnterAnim = c0425Op.mPopEnterAnim;
            this.mPopExitAnim = c0425Op.mPopExitAnim;
            this.mOldMaxState = c0425Op.mOldMaxState;
            this.mCurrentMaxState = c0425Op.mCurrentMaxState;
        }
    }

    @Deprecated
    public FragmentTransaction() {
        this.mOps = new ArrayList<>();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = null;
        this.mClassLoader = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentTransaction(FragmentFactory fragmentFactory, ClassLoader classLoader) {
        this.mOps = new ArrayList<>();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = fragmentFactory;
        this.mClassLoader = classLoader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentTransaction(FragmentFactory fragmentFactory, ClassLoader classLoader, FragmentTransaction fragmentTransaction) {
        this(fragmentFactory, classLoader);
        Iterator<C0425Op> it = fragmentTransaction.mOps.iterator();
        while (it.hasNext()) {
            this.mOps.add(new C0425Op(it.next()));
        }
        this.mEnterAnim = fragmentTransaction.mEnterAnim;
        this.mExitAnim = fragmentTransaction.mExitAnim;
        this.mPopEnterAnim = fragmentTransaction.mPopEnterAnim;
        this.mPopExitAnim = fragmentTransaction.mPopExitAnim;
        this.mTransition = fragmentTransaction.mTransition;
        this.mAddToBackStack = fragmentTransaction.mAddToBackStack;
        this.mAllowAddToBackStack = fragmentTransaction.mAllowAddToBackStack;
        this.mName = fragmentTransaction.mName;
        this.mBreadCrumbShortTitleRes = fragmentTransaction.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = fragmentTransaction.mBreadCrumbShortTitleText;
        this.mBreadCrumbTitleRes = fragmentTransaction.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = fragmentTransaction.mBreadCrumbTitleText;
        if (fragmentTransaction.mSharedElementSourceNames != null) {
            ArrayList<String> arrayList = new ArrayList<>();
            this.mSharedElementSourceNames = arrayList;
            arrayList.addAll(fragmentTransaction.mSharedElementSourceNames);
        }
        if (fragmentTransaction.mSharedElementTargetNames != null) {
            ArrayList<String> arrayList2 = new ArrayList<>();
            this.mSharedElementTargetNames = arrayList2;
            arrayList2.addAll(fragmentTransaction.mSharedElementTargetNames);
        }
        this.mReorderingAllowed = fragmentTransaction.mReorderingAllowed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addOp(C0425Op c0425Op) {
        this.mOps.add(c0425Op);
        c0425Op.mEnterAnim = this.mEnterAnim;
        c0425Op.mExitAnim = this.mExitAnim;
        c0425Op.mPopEnterAnim = this.mPopEnterAnim;
        c0425Op.mPopExitAnim = this.mPopExitAnim;
    }

    private Fragment createFragment(Class<? extends Fragment> cls, Bundle bundle) {
        FragmentFactory fragmentFactory = this.mFragmentFactory;
        if (fragmentFactory == null) {
            throw new IllegalStateException("Creating a Fragment requires that this FragmentTransaction was built with FragmentManager.beginTransaction()");
        }
        ClassLoader classLoader = this.mClassLoader;
        if (classLoader == null) {
            throw new IllegalStateException("The FragmentManager must be attached to itshost to create a Fragment");
        }
        Fragment instantiate = fragmentFactory.instantiate(classLoader, cls.getName());
        if (bundle != null) {
            instantiate.setArguments(bundle);
        }
        return instantiate;
    }

    public final FragmentTransaction add(Class<? extends Fragment> cls, Bundle bundle, String str) {
        return add(createFragment(cls, bundle), str);
    }

    public FragmentTransaction add(Fragment fragment, String str) {
        doAddOp(0, fragment, str, 1);
        return this;
    }

    public final FragmentTransaction add(int r1, Class<? extends Fragment> cls, Bundle bundle) {
        return add(r1, createFragment(cls, bundle));
    }

    public FragmentTransaction add(int r3, Fragment fragment) {
        doAddOp(r3, fragment, null, 1);
        return this;
    }

    public final FragmentTransaction add(int r1, Class<? extends Fragment> cls, Bundle bundle, String str) {
        return add(r1, createFragment(cls, bundle), str);
    }

    public FragmentTransaction add(int r2, Fragment fragment, String str) {
        doAddOp(r2, fragment, str, 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentTransaction add(ViewGroup viewGroup, Fragment fragment, String str) {
        fragment.mContainer = viewGroup;
        return add(viewGroup.getId(), fragment, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void doAddOp(int r4, Fragment fragment, String str, int r7) {
        if (fragment.mPreviousWho != null) {
            FragmentStrictMode.onFragmentReuse(fragment, fragment.mPreviousWho);
        }
        Class<?> cls = fragment.getClass();
        int modifiers = cls.getModifiers();
        if (cls.isAnonymousClass() || !Modifier.isPublic(modifiers) || (cls.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + cls.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if (str != null) {
            if (fragment.mTag != null && !str.equals(fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + str);
            }
            fragment.mTag = str;
        }
        if (r4 != 0) {
            if (r4 == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + str + " to container view with no id");
            } else if (fragment.mFragmentId != 0 && fragment.mFragmentId != r4) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + r4);
            } else {
                fragment.mFragmentId = r4;
                fragment.mContainerId = r4;
            }
        }
        addOp(new C0425Op(r7, fragment));
    }

    public final FragmentTransaction replace(int r2, Class<? extends Fragment> cls, Bundle bundle) {
        return replace(r2, cls, bundle, null);
    }

    public FragmentTransaction replace(int r2, Fragment fragment) {
        return replace(r2, fragment, (String) null);
    }

    public final FragmentTransaction replace(int r1, Class<? extends Fragment> cls, Bundle bundle, String str) {
        return replace(r1, createFragment(cls, bundle), str);
    }

    public FragmentTransaction replace(int r2, Fragment fragment, String str) {
        if (r2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        doAddOp(r2, fragment, str, 2);
        return this;
    }

    public FragmentTransaction remove(Fragment fragment) {
        addOp(new C0425Op(3, fragment));
        return this;
    }

    public FragmentTransaction hide(Fragment fragment) {
        addOp(new C0425Op(4, fragment));
        return this;
    }

    public FragmentTransaction show(Fragment fragment) {
        addOp(new C0425Op(5, fragment));
        return this;
    }

    public FragmentTransaction detach(Fragment fragment) {
        addOp(new C0425Op(6, fragment));
        return this;
    }

    public FragmentTransaction attach(Fragment fragment) {
        addOp(new C0425Op(7, fragment));
        return this;
    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment) {
        addOp(new C0425Op(8, fragment));
        return this;
    }

    public FragmentTransaction setMaxLifecycle(Fragment fragment, Lifecycle.State state) {
        addOp(new C0425Op(10, fragment, state));
        return this;
    }

    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    public FragmentTransaction setCustomAnimations(int r2, int r3) {
        return setCustomAnimations(r2, r3, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int r1, int r2, int r3, int r4) {
        this.mEnterAnim = r1;
        this.mExitAnim = r2;
        this.mPopEnterAnim = r3;
        this.mPopExitAnim = r4;
        return this;
    }

    public FragmentTransaction addSharedElement(View view, String str) {
        if (FragmentTransition.supportsTransition()) {
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList<>();
                this.mSharedElementTargetNames = new ArrayList<>();
            } else if (this.mSharedElementTargetNames.contains(str)) {
                throw new IllegalArgumentException("A shared element with the target name '" + str + "' has already been added to the transaction.");
            } else if (this.mSharedElementSourceNames.contains(transitionName)) {
                throw new IllegalArgumentException("A shared element with the source name '" + transitionName + "' has already been added to the transaction.");
            }
            this.mSharedElementSourceNames.add(transitionName);
            this.mSharedElementTargetNames.add(str);
        }
        return this;
    }

    public FragmentTransaction setTransition(int r1) {
        this.mTransition = r1;
        return this;
    }

    public FragmentTransaction addToBackStack(String str) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = str;
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(int r1) {
        this.mBreadCrumbTitleRes = r1;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(CharSequence charSequence) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = charSequence;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(int r1) {
        this.mBreadCrumbShortTitleRes = r1;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charSequence) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = charSequence;
        return this;
    }

    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.mReorderingAllowed = z;
        return this;
    }

    @Deprecated
    public FragmentTransaction setAllowOptimization(boolean z) {
        return setReorderingAllowed(z);
    }

    public FragmentTransaction runOnCommit(Runnable runnable) {
        disallowAddToBackStack();
        if (this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList<>();
        }
        this.mCommitRunnables.add(runnable);
        return this;
    }
}
