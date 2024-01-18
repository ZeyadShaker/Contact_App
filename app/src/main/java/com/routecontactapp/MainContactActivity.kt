package com.routecontactapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.routecontactapp.databinding.ActivityMainContactBinding

class MainContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainContactBinding
    val contacts: MutableList<Contact> = mutableListOf()
    lateinit var contactAdapter: ContactAdapter
    lateinit var name: String
    lateinit var phone: String
    lateinit var description: String


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        onSaveButtonClick()

    }

    private fun initRecyclerView() {
        contactAdapter = ContactAdapter(contacts)
        binding.rvContacts.adapter = contactAdapter
        contactAdapter.itemOnClickListener = object : RecyclerViewInterface {
            override fun onContactClick(item: Contact, position: Int) {
                val intent = Intent(this@MainContactActivity, ContactDetailsActivity::class.java)
                intent.putExtra("contact", item)
                startActivity(intent)
            }

        }


    }

    private fun onSaveButtonClick() {
        binding.saveBtn.setOnClickListener {
            if (!validateTextFields()) {
                return@setOnClickListener
            }
            name = binding.nameEdit.text?.trim().toString()
            phone = binding.phoneEdit.text?.trim().toString()
            description = binding.descriptionEdit.text?.trim().toString()
            val contact = Contact(name, phone, description, R.drawable.ic_person)
            contacts.add(contact)
            contactAdapter.notifyItemInserted(contacts.size - 1)
            onSaveButtonClickClear()

        }
    }

    private fun onSaveButtonClickClear() {
        binding.nameEdit.text?.clear()
        binding.phoneEdit.text?.clear()
        binding.descriptionEdit.text?.clear()

    }

    private fun validateTextFields(): Boolean {
        name = binding.nameEdit.text?.trim().toString()
        phone = binding.phoneEdit.text?.trim().toString()
        binding.nameTil.error = validateName(name)
        binding.phoneTil.error = validatePhone(phone)
        return validateName(name) == null && validatePhone(phone) == null

    }

    fun validateName(name: String): String? {
        if (name.isEmpty())
            return "required"
        if (name.trim().length < 3)
            return "name can't be less than 3 characters"

        return null
    }

    fun validatePhone(phone: String): String? {
        if (phone.isEmpty())
            return "required"
        if (phone.trim().length != 11)
            return "phone must be 11 digits"
        return null
    }
}





