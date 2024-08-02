package com.example.usersapp.presentation.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.domain.models.User
import com.example.domain.usecase.GetUserByUUIDUseCase
import com.example.domain.usecase.UpdateUserUseCase
import com.example.usersapp.R
import com.example.usersapp.presentation.common.validation.isValidAge
import com.example.usersapp.presentation.common.validation.isValidEmail
import com.example.usersapp.presentation.common.validation.isValidName
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch
import java.util.UUID

sealed class Error(val messageResId: Int) {
    data object NameError : Error(R.string.incorrect_name)
    data object EmailError : Error(R.string.incorrect_email)
    data object AgeError: Error(R.string.incorrect_age)
}

class UserEditViewModel(
    private val uuid: UUID,
    private val getUserByUUIDUseCase: GetUserByUUIDUseCase,
    private val updateUserUseCase: UpdateUserUseCase
): ViewModel() {
    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private val _errors = MutableLiveData<List<Error>>()
    val errors: LiveData<List<Error>> = _errors

    init {
        viewModelScope.launch {
            getUserByUUIDUseCase.execute(uuid).collect {
                _user.value = it
            }
        }
    }

    fun onSaveClick(name: String, email: String, age: String) {
        validateInfo(name, email, age)
        if (_errors.value.isNullOrEmpty()) {
            viewModelScope.launch {
                updateUserUseCase.execute(
                    User(
                        uuid = uuid,
                        name = name,
                        email = email,
                        age = age.toInt(),
                        imageUrl = user.value?.imageUrl ?: ""
                    )
                )
            }
        }
    }

    private fun validateInfo(name: String, email: String, age: String) {
        val errorsList = mutableListOf<Error>()
        if (!name.isValidName()) {
            errorsList.add(Error.NameError)
        }
        if (!email.isValidEmail()) {
            errorsList.add(Error.EmailError)
        }
        if (!age.isValidAge()) {
            errorsList.add(Error.AgeError)
        }
        _errors.value = errorsList
    }
}

@AssistedFactory
interface UserEditViewModelFactoryAssisted {
    fun create(uuid: UUID): UserEditViewModelFactory
}

class UserEditViewModelFactory @AssistedInject constructor(
    @Assisted private val uuid: UUID,
    private val getUserByUUIDUseCase: GetUserByUUIDUseCase,
    private val updateUserUseCase: UpdateUserUseCase
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return UserEditViewModel(uuid, getUserByUUIDUseCase, updateUserUseCase) as T
    }
}