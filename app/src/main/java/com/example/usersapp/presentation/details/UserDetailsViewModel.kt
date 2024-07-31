package com.example.usersapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.models.User
import com.example.domain.usecase.GetUserByUUIDUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.util.UUID

class UserDetailsViewModel(
    private val uuid: UUID,
    private val getUserByUUIDUseCase: GetUserByUUIDUseCase
) : ViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    init {
        viewModelScope.launch {
            _user.value = getUserByUUIDUseCase.execute(uuid)
        }
    }
}

@AssistedFactory
interface UserDetailsViewModelFactoryAssisted {
    fun create(uuid: UUID): UserDetailsViewModelFactory
}

class UserDetailsViewModelFactory @AssistedInject constructor(
    @Assisted private val uuid: UUID,
    private val getUserByUUIDUseCase: GetUserByUUIDUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return UserDetailsViewModel(uuid, getUserByUUIDUseCase) as T
    }
}