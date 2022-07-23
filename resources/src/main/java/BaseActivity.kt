import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.makeevrserg.resources.R

abstract class BaseActivity<T : ViewBinding>(private val bindingFactory: (LayoutInflater) -> T) :
    AppCompatActivity() {
    var binding: T? = null
    abstract val toolBarTitle: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingFactory(layoutInflater)
        binding?.root?.let(::setContentView)
        binding?.let(::setupObservables)
        actionBar?.title = toolBarTitle
        supportActionBar?.title = toolBarTitle
    }

    abstract fun setupObservables(binding: T)

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}