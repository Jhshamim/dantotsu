package ani.dantotsu.utils

import android.content.Context

/**
 * Global constants used throughout the application
 */

// Status bar and navigation bar heights
var statusBarHeight: Int = 0
var navBarHeight: Int = 0

// Current selected tab in MainActivity
var selectedOption: Int = 1

// Bottom navigation bar reference
var bottomBar: nl.joery.animatedbottombar.AnimatedBottomBar? = null

/**
 * Initialize display metrics
 * Call this from MainActivity.onCreate()
 */
fun initializeDisplayMetrics(context: Context) {
    statusBarHeight = getStatusBarHeight(context)
    navBarHeight = getNavBarHeight(context)
}

/**
 * Get resource integer value
 */
fun Context.getResourceInt(name: String, defValue: Int = 0): Int {
    val resourceId = this.resources.getIdentifier(name, "integer", this.packageName)
    return if (resourceId > 0) this.resources.getInteger(resourceId) else defValue
}

/**
 * Get resource boolean value
 */
fun Context.getResourceBoolean(name: String, defValue: Boolean = false): Boolean {
    val resourceId = this.resources.getIdentifier(name, "bool", this.packageName)
    return if (resourceId > 0) this.resources.getBoolean(resourceId) else defValue
}

/**
 * Get resource color value
 */
fun Context.getResourceColor(name: String, defValue: Int = 0): Int {
    val resourceId = this.resources.getIdentifier(name, "color", this.packageName)
    return if (resourceId > 0) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            this.resources.getColor(resourceId, null)
        } else {
            @Suppress("DEPRECATION")
            this.resources.getColor(resourceId)
        }
    } else {
        defValue
    }
}
