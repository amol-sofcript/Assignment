package com.amol.test.callback

import com.amol.test.model.UserEntity

interface UserDataSource {
    fun getAllUser(callback: OperationCallback<UserEntity>, excludingUserWithID: String)
    fun cancel()
}