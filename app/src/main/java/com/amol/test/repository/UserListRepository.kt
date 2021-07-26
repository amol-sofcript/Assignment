package com.amol.test.repository

import com.amol.test.callback.OperationCallback
import com.amol.test.callback.UserDataSource
import com.amol.test.model.UserEntity

class UserListRepository(private val userDataSource: UserDataSource) {
    fun fetchUserList(callback: OperationCallback<UserEntity>, excludingUserWithID: String) {
        userDataSource.getAllUser(callback,excludingUserWithID)
    }

    fun cancel() {
        userDataSource.cancel()
    }

}