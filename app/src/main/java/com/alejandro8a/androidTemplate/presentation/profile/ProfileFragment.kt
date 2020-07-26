package com.alejandro8a.androidTemplate.presentation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.alejandro8a.androidTemplate.R
import com.alejandro8a.androidTemplate.databinding.FragmentProfileBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileBinding.bind(view)
        addObservers()
        viewModel.getCharacter()
    }

    private fun addObservers() {
        viewModel.showProgressBar.observe(this, Observer(::handleShowProgressBar))
        viewModel.uiCharacter.observe(this, Observer(::handleUiCharacterResult))
    }

    private fun handleShowProgressBar(status: Boolean) {
        if (status) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun handleUiCharacterResult(character: UiProfile) {
        binding.nameText.text = character.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}