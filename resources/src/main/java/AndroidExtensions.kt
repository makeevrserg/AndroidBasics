import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity

object AndroidExtensions {
    inline fun <reified T : AppCompatActivity> Context.launchActivity(block: Intent.() -> Unit = {}) =
        Intent(this, T::class.java).apply(block).also(::startActivity)

    inline fun Context.launchActivity(action: String, uri: Uri, block: Intent.() -> Unit = {}) =
        Intent(action, uri).apply(block).also(::startActivity)

    inline fun AppCompatActivity.launchActivityForResult(action: String,code:Int, block: Intent.() -> Unit = {}) =
        Intent(action).apply(block).also{
            startActivityForResult(it,code)
        }
}