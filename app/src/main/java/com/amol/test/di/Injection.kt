import androidx.lifecycle.ViewModelProvider
import com.amol.test.repository.UserListRepository
import com.amol.test.callback.UserDataSource
import com.amol.test.viewfactory.ViewModelFactory

object Injection {

    private val userDataSource: UserDataSource = UserRemoteDataSource(ApiClientNew)
    private val userRepository = UserListRepository(userDataSource)
    private val userViewModelFactory = ViewModelFactory(userRepository)

    fun providerRepository(): UserDataSource {
        return userDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return userViewModelFactory
    }
}