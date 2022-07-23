import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.makeevrserg.domain.rick_and_morty.RickAndMortApiBuilder
import com.makeevrserg.domain.rick_and_morty.RickAndMortyDataSource

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val rickAndMortyDataSource: RickAndMortyDataSource = RickAndMortApiBuilder.dataSource
}