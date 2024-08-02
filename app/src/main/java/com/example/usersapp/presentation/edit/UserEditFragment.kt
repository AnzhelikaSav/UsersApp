package com.example.usersapp.presentation.edit

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.domain.models.User
import com.example.usersapp.R
import com.example.usersapp.databinding.FragmentUserEditBinding
import com.example.usersapp.presentation.di.DiProvider
import com.example.usersapp.presentation.navigation.Router
import javax.inject.Inject

class UserEditFragment: Fragment(R.layout.fragment_user_edit) {
    private val arg: UserEditFragmentArgs by navArgs()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var vmFactoryAssisted: UserEditViewModelFactoryAssisted

    private val viewModel: UserEditViewModel by viewModels {
        vmFactoryAssisted.create(arg.uuid)
    }

    private lateinit var binding: FragmentUserEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserEditBinding.bind(view)

        viewModel.user.observe(viewLifecycleOwner) { user ->
            setUserInfo(user)
        }

        viewModel.errors.observe(viewLifecycleOwner) { errors ->
            setErrors(errors)
        }

        setListeners()
    }

    private fun setUserInfo(user: User) {
        with(binding) {
            etName.setText(user.name)
            etEmail.setText(user.email)
            etAge.setText(user.age.toString())
        }
    }

    private fun setListeners() {
        binding.ivBack.setOnClickListener {
            router.back()
        }

        binding.btnSave.setOnClickListener {
            viewModel.onSaveClick(
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etAge.text.toString()
            )
            router.back()
        }
    }

    private fun setErrors(errors: List<Error>) {
        for (error in errors) {
            val message = resources.getString(error.messageResId)
            when (error) {
                Error.NameError -> binding.etName.error = message
                Error.EmailError -> binding.etEmail.error = message
                Error.AgeError -> binding.etAge.error = message
            }
        }
    }
}