package com.swmansion.rnscreens;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.widget.SearchView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.textinput.ReactTextInputShadowNode;
import com.facebook.react.views.view.ReactViewGroup;
import com.reactcommunity.rndatetimepicker.Common;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SearchBarView.kt */
@Metadata(m184d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0002LMB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010:\u001a\u00020;H\u0002J\u0010\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\fH\u0002J\b\u0010>\u001a\u00020;H\u0002J\u0012\u0010?\u001a\u00020;2\b\u0010@\u001a\u0004\u0018\u00010%H\u0002J\u0012\u0010A\u001a\u00020;2\b\u0010@\u001a\u0004\u0018\u00010%H\u0002J\b\u0010B\u001a\u00020;H\u0014J\u0006\u0010C\u001a\u00020;J\u001a\u0010D\u001a\u00020;2\u0006\u0010E\u001a\u00020%2\b\u0010F\u001a\u0004\u0018\u00010GH\u0002J\u0010\u0010H\u001a\u00020;2\u0006\u0010I\u001a\u00020JH\u0002J\b\u0010K\u001a\u00020;H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0016\u0010*\u001a\u0004\u0018\u00010+8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u000e\"\u0004\b0\u0010\u0010R\u001a\u00101\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u000e\"\u0004\b3\u0010\u0010R\u001e\u00104\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b5\u0010\u0014\"\u0004\b6\u0010\u0016R\u001e\u00107\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b8\u0010\u0014\"\u0004\b9\u0010\u0016¨\u0006N"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView;", "Lcom/facebook/react/views/view/ReactViewGroup;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "autoCapitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "getAutoCapitalize", "()Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "setAutoCapitalize", "(Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;)V", "autoFocus", "", "getAutoFocus", "()Z", "setAutoFocus", "(Z)V", "headerIconColor", "", "getHeaderIconColor", "()Ljava/lang/Integer;", "setHeaderIconColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "hintTextColor", "getHintTextColor", "setHintTextColor", "inputType", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "getInputType", "()Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "setInputType", "(Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;)V", "mAreListenersSet", "mSearchViewFormatter", "Lcom/swmansion/rnscreens/SearchViewFormatter;", ReactTextInputShadowNode.PROP_PLACEHOLDER, "", "getPlaceholder", "()Ljava/lang/String;", "setPlaceholder", "(Ljava/lang/String;)V", "screenStackFragment", "Lcom/swmansion/rnscreens/ScreenStackFragment;", "getScreenStackFragment", "()Lcom/swmansion/rnscreens/ScreenStackFragment;", "shouldOverrideBackButton", "getShouldOverrideBackButton", "setShouldOverrideBackButton", "shouldShowHintSearchIcon", "getShouldShowHintSearchIcon", "setShouldShowHintSearchIcon", Common.TEXT_COLOR, "getTextColor", "setTextColor", "tintColor", "getTintColor", "setTintColor", "handleClose", "", "handleFocusChange", "hasFocus", "handleOpen", "handleTextChange", "newText", "handleTextSubmit", "onAttachedToWindow", "onUpdate", "sendEvent", "eventName", "eventContent", "Lcom/facebook/react/bridge/WritableMap;", "setSearchViewListeners", "searchView", "Landroidx/appcompat/widget/SearchView;", "setSearchViewProps", "SearchBarAutoCapitalize", "SearchBarInputTypes", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
/* loaded from: classes4.dex */
public final class SearchBarView extends ReactViewGroup {
    private SearchBarAutoCapitalize autoCapitalize;
    private boolean autoFocus;
    private Integer headerIconColor;
    private Integer hintTextColor;
    private SearchBarInputTypes inputType;
    private boolean mAreListenersSet;
    private SearchViewFormatter mSearchViewFormatter;
    private String placeholder;
    private boolean shouldOverrideBackButton;
    private boolean shouldShowHintSearchIcon;
    private Integer textColor;
    private Integer tintColor;

    /* compiled from: SearchBarView.kt */
    @Metadata(m184d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "", "(Ljava/lang/String;I)V", "NONE", "WORDS", "SENTENCES", "CHARACTERS", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public enum SearchBarAutoCapitalize {
        NONE,
        WORDS,
        SENTENCES,
        CHARACTERS
    }

    public SearchBarView(ReactContext reactContext) {
        super(reactContext);
        this.inputType = SearchBarInputTypes.TEXT;
        this.autoCapitalize = SearchBarAutoCapitalize.NONE;
        this.placeholder = "";
        this.shouldOverrideBackButton = true;
        this.shouldShowHintSearchIcon = true;
    }

    public final SearchBarInputTypes getInputType() {
        return this.inputType;
    }

    public final void setInputType(SearchBarInputTypes searchBarInputTypes) {
        Intrinsics.checkNotNullParameter(searchBarInputTypes, "<set-?>");
        this.inputType = searchBarInputTypes;
    }

    public final SearchBarAutoCapitalize getAutoCapitalize() {
        return this.autoCapitalize;
    }

    public final void setAutoCapitalize(SearchBarAutoCapitalize searchBarAutoCapitalize) {
        Intrinsics.checkNotNullParameter(searchBarAutoCapitalize, "<set-?>");
        this.autoCapitalize = searchBarAutoCapitalize;
    }

    public final Integer getTextColor() {
        return this.textColor;
    }

    public final void setTextColor(Integer num) {
        this.textColor = num;
    }

    public final Integer getTintColor() {
        return this.tintColor;
    }

    public final void setTintColor(Integer num) {
        this.tintColor = num;
    }

    public final Integer getHeaderIconColor() {
        return this.headerIconColor;
    }

    public final void setHeaderIconColor(Integer num) {
        this.headerIconColor = num;
    }

    public final Integer getHintTextColor() {
        return this.hintTextColor;
    }

    public final void setHintTextColor(Integer num) {
        this.hintTextColor = num;
    }

    public final String getPlaceholder() {
        return this.placeholder;
    }

    public final void setPlaceholder(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.placeholder = str;
    }

    public final boolean getShouldOverrideBackButton() {
        return this.shouldOverrideBackButton;
    }

    public final void setShouldOverrideBackButton(boolean z) {
        this.shouldOverrideBackButton = z;
    }

    public final boolean getAutoFocus() {
        return this.autoFocus;
    }

    public final void setAutoFocus(boolean z) {
        this.autoFocus = z;
    }

    public final boolean getShouldShowHintSearchIcon() {
        return this.shouldShowHintSearchIcon;
    }

    public final void setShouldShowHintSearchIcon(boolean z) {
        this.shouldShowHintSearchIcon = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ScreenStackFragment getScreenStackFragment() {
        ScreenStackHeaderConfig config;
        ViewParent parent = getParent();
        if (!(parent instanceof ScreenStackHeaderSubview) || (config = ((ScreenStackHeaderSubview) parent).getConfig()) == null) {
            return null;
        }
        return config.getScreenFragment();
    }

    public final void onUpdate() {
        setSearchViewProps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setSearchViewProps() {
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        CustomSearchView searchView = screenStackFragment != null ? screenStackFragment.getSearchView() : null;
        if (searchView != null) {
            if (!this.mAreListenersSet) {
                setSearchViewListeners(searchView);
                this.mAreListenersSet = true;
            }
            searchView.setInputType(this.inputType.toAndroidInputType(this.autoCapitalize));
            SearchViewFormatter searchViewFormatter = this.mSearchViewFormatter;
            if (searchViewFormatter != null) {
                searchViewFormatter.setTextColor(this.textColor);
            }
            SearchViewFormatter searchViewFormatter2 = this.mSearchViewFormatter;
            if (searchViewFormatter2 != null) {
                searchViewFormatter2.setTintColor(this.tintColor);
            }
            SearchViewFormatter searchViewFormatter3 = this.mSearchViewFormatter;
            if (searchViewFormatter3 != null) {
                searchViewFormatter3.setHeaderIconColor(this.headerIconColor);
            }
            SearchViewFormatter searchViewFormatter4 = this.mSearchViewFormatter;
            if (searchViewFormatter4 != null) {
                searchViewFormatter4.setHintTextColor(this.hintTextColor);
            }
            SearchViewFormatter searchViewFormatter5 = this.mSearchViewFormatter;
            if (searchViewFormatter5 != null) {
                searchViewFormatter5.setPlaceholder(this.placeholder, this.shouldShowHintSearchIcon);
            }
            searchView.setOverrideBackAction(this.shouldOverrideBackButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ScreenStackFragment screenStackFragment = getScreenStackFragment();
        if (screenStackFragment == null) {
            return;
        }
        screenStackFragment.setOnSearchViewCreate(new Function1<CustomSearchView, Unit>() { // from class: com.swmansion.rnscreens.SearchBarView$onAttachedToWindow$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CustomSearchView customSearchView) {
                invoke2(customSearchView);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:7:0x0026, code lost:
                r3 = r2.this$0.getScreenStackFragment();
             */
            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void invoke2(com.swmansion.rnscreens.CustomSearchView r3) {
                /*
                    r2 = this;
                    java.lang.String r0 = "newSearchView"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                    com.swmansion.rnscreens.SearchBarView r0 = com.swmansion.rnscreens.SearchBarView.this
                    com.swmansion.rnscreens.SearchViewFormatter r0 = com.swmansion.rnscreens.SearchBarView.access$getMSearchViewFormatter$p(r0)
                    if (r0 != 0) goto L19
                    com.swmansion.rnscreens.SearchBarView r0 = com.swmansion.rnscreens.SearchBarView.this
                    com.swmansion.rnscreens.SearchViewFormatter r1 = new com.swmansion.rnscreens.SearchViewFormatter
                    androidx.appcompat.widget.SearchView r3 = (androidx.appcompat.widget.SearchView) r3
                    r1.<init>(r3)
                    com.swmansion.rnscreens.SearchBarView.access$setMSearchViewFormatter$p(r0, r1)
                L19:
                    com.swmansion.rnscreens.SearchBarView r3 = com.swmansion.rnscreens.SearchBarView.this
                    com.swmansion.rnscreens.SearchBarView.access$setSearchViewProps(r3)
                    com.swmansion.rnscreens.SearchBarView r3 = com.swmansion.rnscreens.SearchBarView.this
                    boolean r3 = r3.getAutoFocus()
                    if (r3 == 0) goto L37
                    com.swmansion.rnscreens.SearchBarView r3 = com.swmansion.rnscreens.SearchBarView.this
                    com.swmansion.rnscreens.ScreenStackFragment r3 = com.swmansion.rnscreens.SearchBarView.access$getScreenStackFragment(r3)
                    if (r3 == 0) goto L37
                    com.swmansion.rnscreens.CustomSearchView r3 = r3.getSearchView()
                    if (r3 == 0) goto L37
                    r3.focus()
                L37:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.SearchBarView$onAttachedToWindow$1.invoke2(com.swmansion.rnscreens.CustomSearchView):void");
            }
        });
    }

    private final void setSearchViewListeners(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.swmansion.rnscreens.SearchBarView$setSearchViewListeners$1
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                SearchBarView.this.handleTextChange(str);
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                SearchBarView.this.handleTextSubmit(str);
                return true;
            }
        });
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.swmansion.rnscreens.SearchBarView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                SearchBarView.m1560setSearchViewListeners$lambda0(SearchBarView.this, view, z);
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() { // from class: com.swmansion.rnscreens.SearchBarView$$ExternalSyntheticLambda2
            @Override // androidx.appcompat.widget.SearchView.OnCloseListener
            public final boolean onClose() {
                boolean m1561setSearchViewListeners$lambda1;
                m1561setSearchViewListeners$lambda1 = SearchBarView.m1561setSearchViewListeners$lambda1(SearchBarView.this);
                return m1561setSearchViewListeners$lambda1;
            }
        });
        searchView.setOnSearchClickListener(new View.OnClickListener() { // from class: com.swmansion.rnscreens.SearchBarView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchBarView.m1562setSearchViewListeners$lambda2(SearchBarView.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setSearchViewListeners$lambda-0  reason: not valid java name */
    public static final void m1560setSearchViewListeners$lambda0(SearchBarView this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleFocusChange(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setSearchViewListeners$lambda-1  reason: not valid java name */
    public static final boolean m1561setSearchViewListeners$lambda1(SearchBarView this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleClose();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setSearchViewListeners$lambda-2  reason: not valid java name */
    public static final void m1562setSearchViewListeners$lambda2(SearchBarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleOpen();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleTextChange(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("text", str);
        sendEvent("onChangeText", createMap);
    }

    private final void handleFocusChange(boolean z) {
        sendEvent(z ? "onFocus" : "onBlur", null);
    }

    private final void handleClose() {
        sendEvent("onClose", null);
    }

    private final void handleOpen() {
        sendEvent("onOpen", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleTextSubmit(String str) {
        WritableMap createMap = Arguments.createMap();
        createMap.putString("text", str);
        sendEvent("onSearchButtonPress", createMap);
    }

    private final void sendEvent(String str, WritableMap writableMap) {
        Context context = getContext();
        Objects.requireNonNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        RCTEventEmitter rCTEventEmitter = (RCTEventEmitter) ((ReactContext) context).getJSModule(RCTEventEmitter.class);
        if (rCTEventEmitter != null) {
            rCTEventEmitter.receiveEvent(getId(), str, writableMap);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: SearchBarView.kt */
    @Metadata(m184d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "", "(Ljava/lang/String;I)V", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "TEXT", "PHONE", "NUMBER", "EMAIL", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
    /* loaded from: classes4.dex */
    public static final class SearchBarInputTypes {
        public static final SearchBarInputTypes TEXT = new TEXT("TEXT", 0);
        public static final SearchBarInputTypes PHONE = new PHONE("PHONE", 1);
        public static final SearchBarInputTypes NUMBER = new NUMBER("NUMBER", 2);
        public static final SearchBarInputTypes EMAIL = new EMAIL("EMAIL", 3);
        private static final /* synthetic */ SearchBarInputTypes[] $VALUES = $values();

        private static final /* synthetic */ SearchBarInputTypes[] $values() {
            return new SearchBarInputTypes[]{TEXT, PHONE, NUMBER, EMAIL};
        }

        public /* synthetic */ SearchBarInputTypes(String str, int r2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, r2);
        }

        public static SearchBarInputTypes valueOf(String str) {
            return (SearchBarInputTypes) Enum.valueOf(SearchBarInputTypes.class, str);
        }

        public static SearchBarInputTypes[] values() {
            return (SearchBarInputTypes[]) $VALUES.clone();
        }

        public abstract int toAndroidInputType(SearchBarAutoCapitalize searchBarAutoCapitalize);

        /* compiled from: SearchBarView.kt */
        @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$TEXT;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
        /* loaded from: classes4.dex */
        static final class TEXT extends SearchBarInputTypes {

            /* compiled from: SearchBarView.kt */
            @Metadata(m182k = 3, m181mv = {1, 6, 0}, m179xi = 48)
            /* loaded from: classes4.dex */
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] r0 = new int[SearchBarAutoCapitalize.values().length];
                    r0[SearchBarAutoCapitalize.NONE.ordinal()] = 1;
                    r0[SearchBarAutoCapitalize.WORDS.ordinal()] = 2;
                    r0[SearchBarAutoCapitalize.SENTENCES.ordinal()] = 3;
                    r0[SearchBarAutoCapitalize.CHARACTERS.ordinal()] = 4;
                    $EnumSwitchMapping$0 = r0;
                }
            }

            TEXT(String str, int r3) {
                super(str, r3, null);
            }

            @Override // com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes
            public int toAndroidInputType(SearchBarAutoCapitalize capitalize) {
                Intrinsics.checkNotNullParameter(capitalize, "capitalize");
                int r2 = WhenMappings.$EnumSwitchMapping$0[capitalize.ordinal()];
                if (r2 != 1) {
                    if (r2 != 2) {
                        if (r2 != 3) {
                            if (r2 == 4) {
                                return 4096;
                            }
                            throw new NoWhenBranchMatchedException();
                        }
                        return 16384;
                    }
                    return 8192;
                }
                return 1;
            }
        }

        private SearchBarInputTypes(String str, int r2) {
        }

        /* compiled from: SearchBarView.kt */
        @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$PHONE;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
        /* loaded from: classes4.dex */
        static final class PHONE extends SearchBarInputTypes {
            @Override // com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes
            public int toAndroidInputType(SearchBarAutoCapitalize capitalize) {
                Intrinsics.checkNotNullParameter(capitalize, "capitalize");
                return 3;
            }

            PHONE(String str, int r3) {
                super(str, r3, null);
            }
        }

        /* compiled from: SearchBarView.kt */
        @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$NUMBER;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
        /* loaded from: classes4.dex */
        static final class NUMBER extends SearchBarInputTypes {
            @Override // com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes
            public int toAndroidInputType(SearchBarAutoCapitalize capitalize) {
                Intrinsics.checkNotNullParameter(capitalize, "capitalize");
                return 2;
            }

            NUMBER(String str, int r3) {
                super(str, r3, null);
            }
        }

        /* compiled from: SearchBarView.kt */
        @Metadata(m184d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, m183d2 = {"Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes$EMAIL;", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarInputTypes;", "toAndroidInputType", "", "capitalize", "Lcom/swmansion/rnscreens/SearchBarView$SearchBarAutoCapitalize;", "react-native-screens_release"}, m182k = 1, m181mv = {1, 6, 0}, m179xi = 48)
        /* loaded from: classes4.dex */
        static final class EMAIL extends SearchBarInputTypes {
            @Override // com.swmansion.rnscreens.SearchBarView.SearchBarInputTypes
            public int toAndroidInputType(SearchBarAutoCapitalize capitalize) {
                Intrinsics.checkNotNullParameter(capitalize, "capitalize");
                return 32;
            }

            EMAIL(String str, int r3) {
                super(str, r3, null);
            }
        }
    }
}
