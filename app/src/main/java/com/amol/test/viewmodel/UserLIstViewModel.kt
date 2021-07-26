package com.amol.test.viewmodel

import com.amol.test.callback.OperationCallback
import com.amol.test.model.UserEntity
import com.amol.test.repository.UserListRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserLIstViewModel(private val repository: UserListRepository) : ViewModel() {

    private val _userList = MutableLiveData<List<UserEntity>>().apply { value = emptyList() }
    val userList: LiveData<List<UserEntity>> = _userList
    fun loadUserList(excludingUserWithID: String) {
        repository.fetchUserList(object : OperationCallback<UserEntity> {
            override fun onError(error: String?) {
            }

            override fun onSuccess(data: List<UserEntity>?) {
                if (data.isNullOrEmpty()) {
                } else {
                    _userList.value = data
                }
            }
        },excludingUserWithID)
    }

}