package com.example.roomdatabasekotlion.fragment.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabasekotlion.R
import com.example.roomdatabasekotlion.databinding.FragmentAddBinding
import com.example.roomdatabasekotlion.model.User
import com.example.roomdatabasekotlion.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: UserViewModel

    private var updatedUser: User? = null
    private var editMode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val bundle = arguments
        if (bundle != null) {
            updatedUser = bundle.getParcelable("data_update")
            editMode = bundle.getString("mode")
        }

        if (editMode != null && editMode == "edit") {
            setupEditMode()
        } else {
            setOnClickListener()
        }

        return binding.root
    }

    private fun setupEditMode() {
        binding.textView.text = "Update User"
        val user = updatedUser!!

        with(binding) {
            editTextNumber.setText(user.age.toString())
            firstNameEditText.setText(user.firstName)
            lastNameEditText.setText(user.lastName)
        }

        binding.addUserButton.setOnClickListener {
            updateUser()
        }
    }

    private fun setOnClickListener() {
        binding.addUserButton.setOnClickListener {
            insertUser()
        }
    }

    private fun updateUser() {
        val id = updatedUser!!.id
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = binding.editTextNumber.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            viewModel.updateUser(User(id, firstName, lastName, age.toInt()))
            showToast("Successfully Editing!")
            navigateToListFragment()
        } else {
            showToast("Please fill out all fields!")
        }
    }

    private fun insertUser() {
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = binding.editTextNumber.text.toString()

        if (inputCheck(firstName, lastName, age)) {
            val user = User(0, firstName, lastName, age.toInt())
            viewModel.addUser(user)
            showToast("Successfully added!")
            navigateToListFragment()
        } else {
            showToast("Please fill out all fields!")
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(age))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToListFragment() {
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }
}
