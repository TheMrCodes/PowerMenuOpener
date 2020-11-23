package at.apiglmann.powermenuopener

import android.accessibilityservice.AccessibilityService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager


const val POWER_MENU_TRIGGER_ACTION = "at.apiglmann.shortcuttest.POWER_MENU_TRIGGER_ACTION"

class PowerMenuService: AccessibilityService() {

    private val powerMenuReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            Log.d(this::class.qualifiedName, "Broadcast Incoming")
            if (!performGlobalAction(intent.getIntExtra("action", -1)))
                Toast.makeText(context, "Not supported", Toast.LENGTH_SHORT).show()
        }
    }


    private val localBM get() = LocalBroadcastManager.getInstance(this)
    override fun onCreate() {
        super.onCreate()
        localBM.registerReceiver(powerMenuReceiver, IntentFilter(POWER_MENU_TRIGGER_ACTION))
    }

    override fun onDestroy() {
        localBM.unregisterReceiver(powerMenuReceiver)
    }


    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}

    override fun onInterrupt() {}

}