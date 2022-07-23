import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

object AndroidExtensions {
    inline fun <reified T : AppCompatActivity> Context.launchActivity(block: Intent.() -> Unit = {}) {
        val intent = Intent(this, T::class.java).apply(block)
        startActivity(intent)
    }
}