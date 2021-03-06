package ctprojects.firebase_prototype.log;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

public class LogWrapper {

    private static final String TAG = "com.challengeit.android";
    private static final boolean DEBUG = true;

    public static void debug(Class<?> clazz, String message) {
        if (DEBUG) Log.d(TAG, format(clazz, message));
    }

    public static void info(Class<?> clazz, String message) {
        if (DEBUG) Log.i(TAG, format(clazz, message));
    }

    public static void warning(Class<?> clazz, String message) {
        Log.w(TAG, format(clazz, message));
    }

    public static void error(Class<?> clazz, String message) {
        Log.e(TAG, format(clazz, message));
    }

    public static void error(Class<?> clazz, String message, Exception e) {
        Log.e(TAG, format(clazz, message), e);
    }

    private static String format(Class<?> clazz, String message) {
        return new StringBuilder().append("[").append(clazz.getSimpleName()).append("] ").append(message).toString();
    }

    public static void logAndShowError(Activity activity,  Exception e) {
        String message;
        if (e != null) {
            message = e.getMessage();
            if (message == null) {
                message = e.toString();
            }
            LogWrapper.error(LogWrapper.class, message);
            showMessage(activity, message);
        }

    }

    /**
     * Shows an error alert dialog with the given message.
     *
     * @param activity
     *            activity
     * @param message
     *            message to show or {@code null} for none
     */
    public static void showMessage(final Activity activity, String message) {
        final String errorMessage = message == null ? "Error" : "[Error ] "
                + message;
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, errorMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}