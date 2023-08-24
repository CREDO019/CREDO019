package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.core.internal.view.SupportMenu;

/* loaded from: classes.dex */
public class MenuWrapperICS extends BaseMenuWrapper implements Menu {
    private final SupportMenu mWrappedObject;

    public MenuWrapperICS(Context context, SupportMenu supportMenu) {
        super(context);
        if (supportMenu == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = supportMenu;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return getMenuItemWrapper(this.mWrappedObject.add(charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int r2) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2));
    }

    @Override // android.view.Menu
    public MenuItem add(int r2, int r3, int r4, CharSequence charSequence) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2, r3, r4, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int r2, int r3, int r4, int r5) {
        return getMenuItemWrapper(this.mWrappedObject.add(r2, r3, r4, r5));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r2) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r2, int r3, int r4, CharSequence charSequence) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2, r3, r4, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int r2, int r3, int r4, int r5) {
        return getSubMenuWrapper(this.mWrappedObject.addSubMenu(r2, r3, r4, r5));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int r13, int r14, int r15, ComponentName componentName, Intent[] intentArr, Intent intent, int r19, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = this.mWrappedObject.addIntentOptions(r13, r14, r15, componentName, intentArr, intent, r19, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int r4 = 0; r4 < length; r4++) {
                menuItemArr[r4] = getMenuItemWrapper(menuItemArr2[r4]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public void removeItem(int r2) {
        internalRemoveItem(r2);
        this.mWrappedObject.removeItem(r2);
    }

    @Override // android.view.Menu
    public void removeGroup(int r2) {
        internalRemoveGroup(r2);
        this.mWrappedObject.removeGroup(r2);
    }

    @Override // android.view.Menu
    public void clear() {
        internalClear();
        this.mWrappedObject.clear();
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int r2, boolean z, boolean z2) {
        this.mWrappedObject.setGroupCheckable(r2, z, z2);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int r2, boolean z) {
        this.mWrappedObject.setGroupVisible(r2, z);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int r2, boolean z) {
        this.mWrappedObject.setGroupEnabled(r2, z);
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return this.mWrappedObject.hasVisibleItems();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int r2) {
        return getMenuItemWrapper(this.mWrappedObject.findItem(r2));
    }

    @Override // android.view.Menu
    public int size() {
        return this.mWrappedObject.size();
    }

    @Override // android.view.Menu
    public MenuItem getItem(int r2) {
        return getMenuItemWrapper(this.mWrappedObject.getItem(r2));
    }

    @Override // android.view.Menu
    public void close() {
        this.mWrappedObject.close();
    }

    @Override // android.view.Menu
    public boolean performShortcut(int r2, KeyEvent keyEvent, int r4) {
        return this.mWrappedObject.performShortcut(r2, keyEvent, r4);
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int r2, KeyEvent keyEvent) {
        return this.mWrappedObject.isShortcutKey(r2, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int r2, int r3) {
        return this.mWrappedObject.performIdentifierAction(r2, r3);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.mWrappedObject.setQwertyMode(z);
    }
}
