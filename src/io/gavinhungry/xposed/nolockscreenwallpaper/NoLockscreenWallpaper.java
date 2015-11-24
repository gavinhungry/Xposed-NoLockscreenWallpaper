package io.gavinhungry.xposed.nolockscreenwallpaper;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodReplacement;

public class NoLockscreenWallpaper implements IXposedHookLoadPackage {
  public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
    if (lpparam.packageName.equals("com.android.systemui")) {
      try {

        final Class<?> PhoneStatusBar = XposedHelpers.findClass("com.android.systemui.statusbar.phone.PhoneStatusBar",
          lpparam.classLoader);

        XposedBridge.hookAllMethods(PhoneStatusBar, "updateMediaMetaData", XC_MethodReplacement.DO_NOTHING);

      } catch(Throwable t) {
        XposedBridge.log(t);
      }
    }
  }
}
