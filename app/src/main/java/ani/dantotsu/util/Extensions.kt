package ani.dantotsu.utils

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ani.dantotsu.R


/**
 * Extension functions for MainActivity and other activities
 * Contains all missing utility functions used throughout the app
 */

// ✅ Show Toast message
fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

// ✅ Show Toast message (Int resource)
fun Context.toast(messageRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageRes, duration).show()
}

// ✅ Show Snackbar message
fun AppCompatActivity.snackString(message: String, duration: Int = Snackbar.LENGTH_LONG): Snackbar? {
    return try {
        findViewById<View>(android.R.id.content)?.let {
            Snackbar.make(it, message, duration)
        }?.apply {
            show()
        }
    } catch (e: Exception) {
        null
    }
}

// ✅ Show Snackbar message (Int resource)
fun AppCompatActivity.snackString(messageRes: Int, duration: Int = Snackbar.LENGTH_LONG): Snackbar? {
    return try {
        findViewById<View>(android.R.id.content)?.let {
            Snackbar.make(it, getString(messageRes), duration)
        }?.apply {
            show()
        }
    } catch (e: Exception) {
        null
    }
}

// ✅ Safe function execution with error handling
inline fun <T> tryWith(default: T, block: () -> T): T {
    return try {
        block()
    } catch (e: Exception) {
        default
    }
}

// ✅ Check if device is online
fun isOnline(context: Context): Boolean {
    return try {
        val runtime = Runtime.getRuntime()
        val process = runtime.exec("ping -c 1 8.8.8.8")
        process.waitFor()
        process.exitValue() == 0
    } catch (e: Exception) {
        false
    }
}

// ✅ Convert DP to pixels
fun Int.toPx(context: Context): Int {
    return (this * context.resources.displayMetrics.density).toInt()
}

fun Int.toPx(): Int {
    return (this * android.content.res.Resources.getSystem().displayMetrics.density).toInt()
}

// ✅ Get safe navigation bar height
fun getNavBarHeight(context: Context): Int {
    val resourceId = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    return if (resourceId > 0) {
        context.resources.getDimensionPixelSize(resourceId)
    } else {
        0
    }
}

// ✅ Get safe status bar height
fun getStatusBarHeight(context: Context): Int {
    return try {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    } catch (e: Exception) {
        0
    }
}

// ✅ Safe view visibility
fun View?.setVisibility(isVisible: Boolean) {
    this?.visibility = if (isVisible) View.VISIBLE else View.GONE
}

// ✅ Check Android version
fun isAtLeast(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}

// ✅ Format string with arguments
fun String.format(vararg args: Any?): String {
    return String.format(this, *args)
}

// ✅ Safe context getter
fun Any.getAppContext(): Context? {
    return when (this) {
        is Context -> this
        is androidx.fragment.app.Fragment -> this.requireContext()
        is AppCompatActivity -> this
        else -> null
    }
}
