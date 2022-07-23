import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(private val bindingFactory: (LayoutInflater) -> T) :
    AppCompatActivity() {
    var binding: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        binding?.root?.let(::setContentView)
        binding?.let(::setupObservables)
    }

    abstract fun setupObservables(binding: T)

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}