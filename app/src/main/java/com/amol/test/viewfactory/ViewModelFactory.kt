package com.amol.test.viewfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amol.test.repository.UserListRepository
import com.amol.test.viewmodel.UserLIstViewModel

class ViewModelFactory(private val repository: UserListRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserLIstViewModel(repository) as T
    }
}