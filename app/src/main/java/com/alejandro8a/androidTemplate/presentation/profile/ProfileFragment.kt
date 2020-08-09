package com.alejandro8a.androidTemplate.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import com.alejandro8a.androidTemplate.R
import com.alejandro8a.androidTemplate.databinding.FragmentProfileBinding
import com.alejandro8a.androidTemplate.extensions.snackbar
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentProfileBinding.bind(view)
        addListeners()
        addObservers()
        viewModel.getCharacter()
    }

    private fun addListeners() {
        binding.characterButton.setOnClickListener {
            viewModel.getCharacter()
        }
        binding.saveButton.setOnClickListener {
            viewModel.saveCharacter()
        }
    }

    private fun addObservers() {
        viewModel.showProgressBar.observe(this, Observer(::handleShowProgressBar))
        viewModel.uiCharacter.observe(this, Observer(::handleUiCharacterResult))
        viewModel.uiAllCharacters.observe(this, Observer(::handleUiAllCharactersResult))
        viewModel.errorMessage.observe(this, Observer(::handleErrorMessageResult))
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
        binding.weaponText.text = character.weapon
        binding.profileImage.load(character.photoUrl)
    }

    private fun handleUiAllCharactersResult(characterList: List<UiProfile>) {
        //No-op
    }

    private fun handleErrorMessageResult(error: String) {
        snackbar(error, binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}