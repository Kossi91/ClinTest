package com.example.presentation.ui.fragments

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentFilmsBinding
import com.example.presentation.state.UIState
import com.example.presentation.ui.adapter.FilmsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilmsFragment : BaseFragment<FragmentFilmsBinding, FilmsViewModel>(
    R.layout.fragment_films
) {

    override val binding by viewBinding(FragmentFilmsBinding::bind)
    override val viewModel: FilmsViewModel by viewModels()
    private val adapter = FilmsAdapter()

    override fun initialize() {
        binding.filmsRv.adapter = adapter
    }

    override fun setupSubscribes() {
        collectState()
    }

    private fun collectState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.filmState.collect {
                    when (it) {
                        is UIState.Error -> {
                            Log.e("error", it.error)
                        }
                        is UIState.Loading -> {
                        }
                        is UIState.Success -> {
                            adapter.submitList(it.data)
                        }
                    }
                }
            }
        }
    }
}