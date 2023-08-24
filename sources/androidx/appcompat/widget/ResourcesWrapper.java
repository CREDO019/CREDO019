package androidx.appcompat.widget;

import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import androidx.appcompat.resources.Compatibility;
import androidx.core.content.res.ResourcesCompat;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ResourcesWrapper extends Resources {
    private final Resources mResources;

    public ResourcesWrapper(Resources resources) {
        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        this.mResources = resources;
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int r2) throws Resources.NotFoundException {
        return this.mResources.getText(r2);
    }

    @Override // android.content.res.Resources
    public CharSequence getQuantityText(int r2, int r3) throws Resources.NotFoundException {
        return this.mResources.getQuantityText(r2, r3);
    }

    @Override // android.content.res.Resources
    public String getString(int r2) throws Resources.NotFoundException {
        return this.mResources.getString(r2);
    }

    @Override // android.content.res.Resources
    public String getString(int r2, Object... objArr) throws Resources.NotFoundException {
        return this.mResources.getString(r2, objArr);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int r2, int r3, Object... objArr) throws Resources.NotFoundException {
        return this.mResources.getQuantityString(r2, r3, objArr);
    }

    @Override // android.content.res.Resources
    public String getQuantityString(int r2, int r3) throws Resources.NotFoundException {
        return this.mResources.getQuantityString(r2, r3);
    }

    @Override // android.content.res.Resources
    public CharSequence getText(int r2, CharSequence charSequence) {
        return this.mResources.getText(r2, charSequence);
    }

    @Override // android.content.res.Resources
    public CharSequence[] getTextArray(int r2) throws Resources.NotFoundException {
        return this.mResources.getTextArray(r2);
    }

    @Override // android.content.res.Resources
    public String[] getStringArray(int r2) throws Resources.NotFoundException {
        return this.mResources.getStringArray(r2);
    }

    @Override // android.content.res.Resources
    public int[] getIntArray(int r2) throws Resources.NotFoundException {
        return this.mResources.getIntArray(r2);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainTypedArray(int r2) throws Resources.NotFoundException {
        return this.mResources.obtainTypedArray(r2);
    }

    @Override // android.content.res.Resources
    public float getDimension(int r2) throws Resources.NotFoundException {
        return this.mResources.getDimension(r2);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelOffset(int r2) throws Resources.NotFoundException {
        return this.mResources.getDimensionPixelOffset(r2);
    }

    @Override // android.content.res.Resources
    public int getDimensionPixelSize(int r2) throws Resources.NotFoundException {
        return this.mResources.getDimensionPixelSize(r2);
    }

    @Override // android.content.res.Resources
    public float getFraction(int r2, int r3, int r4) {
        return this.mResources.getFraction(r2, r3, r4);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int r2) throws Resources.NotFoundException {
        return this.mResources.getDrawable(r2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Drawable getDrawableCanonical(int r1) throws Resources.NotFoundException {
        return super.getDrawable(r1);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawable(int r2, Resources.Theme theme) throws Resources.NotFoundException {
        return ResourcesCompat.getDrawable(this.mResources, r2, theme);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int r3, int r4) throws Resources.NotFoundException {
        return ResourcesCompat.getDrawableForDensity(this.mResources, r3, r4, null);
    }

    @Override // android.content.res.Resources
    public Drawable getDrawableForDensity(int r2, int r3, Resources.Theme theme) {
        return ResourcesCompat.getDrawableForDensity(this.mResources, r2, r3, theme);
    }

    @Override // android.content.res.Resources
    public Movie getMovie(int r2) throws Resources.NotFoundException {
        return this.mResources.getMovie(r2);
    }

    @Override // android.content.res.Resources
    public int getColor(int r2) throws Resources.NotFoundException {
        return this.mResources.getColor(r2);
    }

    @Override // android.content.res.Resources
    public ColorStateList getColorStateList(int r2) throws Resources.NotFoundException {
        return this.mResources.getColorStateList(r2);
    }

    @Override // android.content.res.Resources
    public boolean getBoolean(int r2) throws Resources.NotFoundException {
        return this.mResources.getBoolean(r2);
    }

    @Override // android.content.res.Resources
    public int getInteger(int r2) throws Resources.NotFoundException {
        return this.mResources.getInteger(r2);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getLayout(int r2) throws Resources.NotFoundException {
        return this.mResources.getLayout(r2);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getAnimation(int r2) throws Resources.NotFoundException {
        return this.mResources.getAnimation(r2);
    }

    @Override // android.content.res.Resources
    public XmlResourceParser getXml(int r2) throws Resources.NotFoundException {
        return this.mResources.getXml(r2);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int r2) throws Resources.NotFoundException {
        return this.mResources.openRawResource(r2);
    }

    @Override // android.content.res.Resources
    public InputStream openRawResource(int r2, TypedValue typedValue) throws Resources.NotFoundException {
        return this.mResources.openRawResource(r2, typedValue);
    }

    @Override // android.content.res.Resources
    public AssetFileDescriptor openRawResourceFd(int r2) throws Resources.NotFoundException {
        return this.mResources.openRawResourceFd(r2);
    }

    @Override // android.content.res.Resources
    public void getValue(int r2, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        this.mResources.getValue(r2, typedValue, z);
    }

    @Override // android.content.res.Resources
    public void getValueForDensity(int r2, int r3, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        Compatibility.Api15Impl.getValueForDensity(this.mResources, r2, r3, typedValue, z);
    }

    @Override // android.content.res.Resources
    public void getValue(String str, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        this.mResources.getValue(str, typedValue, z);
    }

    @Override // android.content.res.Resources
    public TypedArray obtainAttributes(AttributeSet attributeSet, int[] r3) {
        return this.mResources.obtainAttributes(attributeSet, r3);
    }

    @Override // android.content.res.Resources
    public void updateConfiguration(Configuration configuration, DisplayMetrics displayMetrics) {
        super.updateConfiguration(configuration, displayMetrics);
        Resources resources = this.mResources;
        if (resources != null) {
            resources.updateConfiguration(configuration, displayMetrics);
        }
    }

    @Override // android.content.res.Resources
    public DisplayMetrics getDisplayMetrics() {
        return this.mResources.getDisplayMetrics();
    }

    @Override // android.content.res.Resources
    public Configuration getConfiguration() {
        return this.mResources.getConfiguration();
    }

    @Override // android.content.res.Resources
    public int getIdentifier(String str, String str2, String str3) {
        return this.mResources.getIdentifier(str, str2, str3);
    }

    @Override // android.content.res.Resources
    public String getResourceName(int r2) throws Resources.NotFoundException {
        return this.mResources.getResourceName(r2);
    }

    @Override // android.content.res.Resources
    public String getResourcePackageName(int r2) throws Resources.NotFoundException {
        return this.mResources.getResourcePackageName(r2);
    }

    @Override // android.content.res.Resources
    public String getResourceTypeName(int r2) throws Resources.NotFoundException {
        return this.mResources.getResourceTypeName(r2);
    }

    @Override // android.content.res.Resources
    public String getResourceEntryName(int r2) throws Resources.NotFoundException {
        return this.mResources.getResourceEntryName(r2);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtras(XmlResourceParser xmlResourceParser, Bundle bundle) throws XmlPullParserException, IOException {
        this.mResources.parseBundleExtras(xmlResourceParser, bundle);
    }

    @Override // android.content.res.Resources
    public void parseBundleExtra(String str, AttributeSet attributeSet, Bundle bundle) throws XmlPullParserException {
        this.mResources.parseBundleExtra(str, attributeSet, bundle);
    }
}
