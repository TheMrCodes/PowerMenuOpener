package at.apiglmann.powermenuopener

import android.accessibilityservice.AccessibilityService
import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.localbroadcastmanager.content.LocalBroadcastManager


class PowerMenuActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = ComponentName(this, PowerMenuService::class.java)
        this.packageManager.setComponentEnabledSetting(
            component, PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )

        val intent = Intent(POWER_MENU_TRIGGER_ACTION).apply {
            putExtra("action", AccessibilityService.GLOBAL_ACTION_POWER_DIALOG)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        finish()
    }

}