package com.huxq17.example.floatball.floatball;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;


public class FloatBallUtil {
    public static boolean inSingleActivity;

    public static WindowManager.LayoutParams getLayoutParams() {
        return getLayoutParams(null, false);
    }

    public static WindowManager.LayoutParams getLayoutParams(Activity activity) {
        return getLayoutParams(activity, false);
    }

    public static WindowManager.LayoutParams getLayoutParams(Activity activity, boolean listenBackEvent) {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        if (activity == null) {
            final int sdkInt = Build.VERSION.SDK_INT;
            if (sdkInt < Build.VERSION_CODES.KITKAT) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else if (sdkInt < Build.VERSION_CODES.N_MR1) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            } else if (sdkInt < Build.VERSION_CODES.O) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {//8.0以后
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
            IBinder windowToken = activity.getWindow().getDecorView().getWindowToken();
            layoutParams.token = windowToken;
        }
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        if (listenBackEvent) {
            layoutParams.flags = layoutParams.flags & ~WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        }

        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.TOP | Gravity.LEFT;
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return layoutParams;
    }

    public static WindowManager.LayoutParams getLayoutParams(boolean listenBackEvent) {
        return getLayoutParams(null, listenBackEvent);
    }

    public static WindowManager.LayoutParams getStatusBarLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        final int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt < Build.VERSION_CODES.KITKAT) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else if (sdkInt < Build.VERSION_CODES.N_MR1) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
        } else if (sdkInt < Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        } else {//8.0以后
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
//        layoutParams.format = PixelFormat.TRANSLUCENT;
        return layoutParams;
    }
}
