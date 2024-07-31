package com.example.usersapp.presentation.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.domain.models.User
import com.example.usersapp.R
import com.example.usersapp.databinding.FragmentUserDetailsBinding
import com.example.usersapp.presentation.di.DiProvider
import javax.inject.Inject

class UserDetailsFragment: Fragment(R.layout.fragment_user_details) {

    val arg: UserDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var vmFactoryAssisted: UserDetailsViewModelFactoryAssisted

    private val viewModel: UserDetailsViewModel by viewModels {
        vmFactoryAssisted.create(arg.uuid)
    }

    private lateinit var binding: FragmentUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserDetailsBinding.bind(view)

        viewModel.user.observe(viewLifecycleOwner) {
            setUserInfo(it)
        }
    }

    private fun setUserInfo(user: User) {
        Glide.with(binding.root)
            .load(user.imageUrl)
            .circleCrop()
            .into(binding.ivAvatar)
        binding.tvName.text = user.name
        binding.tvEmail.text = user.email
    }
}