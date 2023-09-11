package com.example.roomdatabasekotlion.fragment.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasekotlion.R
import com.example.roomdatabasekotlion.databinding.FragmentListBinding
import com.example.roomdatabasekotlion.fragment.add.AddFragment
import com.example.roomdatabasekotlion.interfaces.UpdateClickListener
import com.example.roomdatabasekotlion.model.User
import com.example.roomdatabasekotlion.viewmodel.UserViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ListFragment : Fragment(), UpdateClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: ListAdapter
    private lateinit var viewModel: UserViewModel
    private var userList: ArrayList<User> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        setOnClickListener()
        getDataFromDatabase()
        setUpRec()
        return binding.root
    }

    private fun setOnClickListener() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
    }

    private fun setUpRec() {
        adapter = ListAdapter(userList, this)
        binding.rec.adapter = adapter
        binding.rec.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun getDataFromDatabase() {
        viewModel.readAllData.observe(viewLifecycleOwner, Observer { data ->
            userList.clear()
            userList.addAll(data)
            adapter.notifyDataSetChanged()
        })
    }

    override fun update(user: User) {
        val updatedUser = User(user.id, user.firstName, user.lastName, user.age)
        val addFragment = AddFragment()
        val bundle = Bundle()
        bundle.putParcelable("data_update", updatedUser)
        bundle.putString("mode", "edit")
        addFragment.arguments = bundle
        findNavController().navigate(R.id.action_listFragment_to_addFragment, bundle)
    }

    override fun delete(user: User) {
        createDialog(user)
    }

    private fun createDialog(user: User) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Delete selected User")
            .setMessage("User will be deleted from your list.")
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(activity, "Delete Success!", Toast.LENGTH_LONG).show()
                viewModel.deleteUser(user)
            }
            .setNegativeButton("Decline") { _, _ ->
                Toast.makeText(activity, "Delete Failed!", Toast.LENGTH_LONG).show()
            }
            .show()
    }
}
