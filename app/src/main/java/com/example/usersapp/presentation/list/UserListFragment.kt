package com.example.usersapp.presentation.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersapp.R
import com.example.usersapp.databinding.FragmentUserListBinding
import com.example.usersapp.presentation.common.recycler.SpaceItemDecoration
import com.example.usersapp.presentation.di.DiProvider
import javax.inject.Inject

class UserListFragment: Fragment(R.layout.fragment_user_list) {

    @Inject
    lateinit var vmFactory: UserListViewModelFactory

    private val viewModel by viewModels<UserListViewModel> { vmFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DiProvider.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserListBinding.bind(view)

        val adapter = UserAdapter { uuid ->

        }
        val decoration = SpaceItemDecoration(
            spaceSize = resources.getDimensionPixelSize(
                R.dimen.padding_12
            )
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = adapter

        viewModel.users.observe(viewLifecycleOwner) {
            adapter.items = it
        }
    }
}