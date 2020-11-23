package at.apiglmann.powermenuopener

import android.app.Activity
import android.content.Intent
import android.os.Bundle


class CreateShortcutActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupShortcut()
    }

    private fun setupShortcut() {
        // Intent executed on shortcut click
        val shortcutIntent = Intent(this, PowerMenuActivity::class.java)

        // Then, set up the container intent (the response to the caller)
        val iconResource = Intent.ShortcutIconResource.fromContext(this, R.mipmap.ic_launcher)
        setResult(RESULT_OK, Intent().apply {
            putExtra(Intent.EXTRA_SHORTCUT_NAME, getString(R.string.open_power_menu))
            putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
            putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconResource)
        })
        finish()    // don't forget to finish 😬
    }

}