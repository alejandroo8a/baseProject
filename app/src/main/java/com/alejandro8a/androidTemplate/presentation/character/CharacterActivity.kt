package com.alejandro8a.androidTemplate.presentation.character

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alejandro8a.androidTemplate.R
import com.alejandro8a.androidTemplate.extensions.showFragment

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showFragment(CharacterFragment(), R.id.rootView)
    }

}