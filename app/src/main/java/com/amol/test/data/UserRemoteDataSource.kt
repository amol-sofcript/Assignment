import com.amol.test.model.UserEntity
import com.amol.test.callback.OperationCallback
import com.amol.test.callback.UserDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRemoteDataSource(apiClient: ApiClientNew) : UserDataSource {

    private var call: Call<List<UserEntity>>? = null
    private val service = apiClient.build()

    override fun getAllUser(callback: OperationCallback<UserEntity>, excludingUserWithID: String) {
        call = service?.userList()
        call?.enqueue(object : Callback<List<UserEntity>> {
            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(
                call: Call<List<UserEntity>>,
                response: Response<List<UserEntity>>
            ) {
                response.body()?.let {
                    if (response.isSuccessful) {

                        if (!excludingUserWithID.isNullOrEmpty()) {
                            var templist: List<UserEntity>
                            templist = it!!.filterNot { it.id == excludingUserWithID.toInt() }
                            callback.onSuccess(templist.asReversed())
                        } else {
                            callback.onSuccess(it.asReversed())
                        }

                    } else {
                        //callback.onError()
                    }
                }
            }
        })


    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}