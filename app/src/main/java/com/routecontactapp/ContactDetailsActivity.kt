package com.routecontactapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.content.IntentCompat
import com.routecontactapp.databinding.ActivityContactDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    lateinit var name: TextView
    lateinit var binding: ActivityContactDetailsBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contact = IntentCompat.getParcelableExtra(intent, "contact", Contact::class.java)
        contact?.let { contact ->
            binding.nameValue.text = contact.avatarName
            binding.phoneValue.text = contact.avatarPhoneNumber
            binding.descriptionValue.text = contact.avatarDescription
            contact.avatarLogo.let {
                if (it != null) {
                    binding.logoContactDetails.setImageResource(it)
                }
            }

        }
        backNavigation()
    }

    private fun backNavigation() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
