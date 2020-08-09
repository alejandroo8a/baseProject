package com.alejandro8a.androidTemplate.presentation.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.alejandro8a.androidTemplate.R
import com.alejandro8a.androidTemplate.databinding.ItemCharacterBinding

class ProfileAdapter() : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    private val profileList = arrayListOf<UiProfile>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(profileList[position])
    }

    override fun getItemCount(): Int = profileList.size

    fun setupData(newProfileData: List<UiProfile>) {
        profileList.clear()
        profileList.addAll(newProfileData)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemCharacterBinding.bind(view)

        fun bind(profileData: UiProfile) = with(itemView) {
            binding.nameText.text = profileData.name
            binding.weaponText.text = profileData.weapon
            binding.characterImage.load(profileData.photoUrl)
        }
    }
}