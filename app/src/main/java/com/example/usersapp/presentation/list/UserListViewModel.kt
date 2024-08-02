package com.example.usersapp.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.domain.models.User
import com.example.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserListViewModel(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    init {
        viewModelScope.launch {
            getUsersUseCase.execute().collect {
                _users.value = it
            }
        }
    }

    fun onSearchChanged(searchString: String) {
       viewModelScope.launch {
           _users.value = getUsersUseCase.execute(searchString).first()
       }
    }
}

class UserListViewModelFactory @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(getUsersUseCase) as T
    }

}