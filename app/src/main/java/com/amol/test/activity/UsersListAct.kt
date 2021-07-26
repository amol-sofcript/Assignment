package com.amol.test.activity

import com.amol.test.model.UserEntity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amol.test.R
import com.amol.test.adapter.UserListAdapter
import com.amol.test.databinding.ActivityUsersListBinding
import com.amol.test.viewmodel.UserLIstViewModel
// git push
class UsersListAct : AppCompatActivity() {
    lateinit var context: Context

    private lateinit var binding: ActivityUsersListBinding
    private lateinit var usersListActViewModel: UserLIstViewModel
    private lateinit var adapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_users_list)

        usersListActViewModel = ViewModelProvider(
            this,
            Injection.provideViewModelFactory()
        ).get(UserLIstViewModel::class.java)


        context = this@UsersListAct
        binding.lifecycleOwner = this
        usersListActViewModel.userList.observe(this, getUserList)
    }

    //observers
    private val getUserList = Observer<List<UserEntity>> {
        binding.progressCircular.visibility = View.GONE
        bindDataToAdpter(it)
    }

    override fun onResume() {
        super.onResume()
        if (isOnline(context)) {
            binding.progressCircular.visibility = View.VISIBLE
            usersListActViewModel.loadUserList("")

        } else {
            Toast.makeText(this, "Network connection is not available", Toast.LENGTH_LONG).show()

        }

    }

    companion object {
        const val TAG = "LTTS"
    }

    private fun bindDataToAdpter(list: List<UserEntity>) {
        binding.userRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            UserListAdapter({ selectedIteam: UserEntity -> listItemClicked(selectedIteam) })
        binding.userRecyclerView.adapter = adapter
        adapter.setData(list)
        adapter.notifyDataSetChanged()
    }

    private fun listItemClicked(userObject: UserEntity) {
        Toast.makeText(this, "selected user is ${userObject.name}", Toast.LENGTH_SHORT).show()
        if (isOnline(context)) {
            binding.progressCircular.visibility = View.VISIBLE
            usersListActViewModel.loadUserList(userObject.id.toString())
        } else {
            Toast.makeText(this, "Network connection is not available", Toast.LENGTH_LONG).show()
        }
        // excludingUserWithID: String? = null  If given a user ID to exclude, filter out the excluded user ID before returning the result
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }
}