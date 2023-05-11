package com.example.onlinenote.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.onlinenote.R
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.data.models.Note
import com.example.onlinenote.databinding.FragmentAddBinding
import com.example.onlinenote.presentation.MainViewModel
import com.example.onlinenote.presentation.NetworkViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: NetworkViewModel
    var noteId: Int = -1
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

        viewModel = ViewModelProvider(this)[NetworkViewModel::class.java]

//        val bundle = arguments
//        if (bundle != null && bundle.containsKey("noteId"))
//        noteId = bundle.getInt("noteId")
        if (noteId == -1) {
            addNote()
        } else {
//            updateNote()
        }

        backButton()

    }

    @SuppressLint("SimpleDateFormat")
    private fun addNote() {
        binding.ivSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val text = binding.etText.text.toString()
            if (title.isNotEmpty() && text.isNotEmpty()) {
                val sdf = SimpleDateFormat("dd.MM.yyyy")
                val currentDate = sdf.format(Date())
                val note = NetworkNote(title, text, currentDate)
                lifecycleScope.launch {
                    viewModel.addNote(note)
                    Toast.makeText(requireContext(), "Successfully saved!", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
                }
            } else {
                Toast.makeText(requireContext(), "Fill in empty fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun backButton() {
        binding.ivBack.setOnClickListener {
            findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
        }
    }

//    @SuppressLint("SimpleDateFormat")
//    private fun updateNote() {
//
//        viewModel.getAllNotesLiveData.observe(requireActivity()) {
//
//            if (it.isNotEmpty()) {
//                val note = it.first()
//                binding.etTitle.setText(note.title)
//                binding.etText.setText(note.text)
//
//                binding.ivSave.setOnClickListener {
//                    val title = binding.etTitle.text.toString()
//                    val text = binding.etText.text.toString()
//                    if (title.isNotEmpty() && text.isNotEmpty()) {
//                        val sdf = SimpleDateFormat("dd.MM.yyyy")
//                        val currentDate = sdf.format(Date())
//                        note.title = title
//                        note.text = text
//                        note.lastUpdatedData = currentDate.toString()
//                        lifecycleScope.launch {
//                            viewModel.updateNote(note)
//                            Toast.makeText(
//                                requireContext(),
//                                "Successfully updated!",
//                                Toast.LENGTH_SHORT
//                            )
//                                .show()
//                            findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
//                        }
//                    } else {
//                        Toast.makeText(requireContext(), "Fill in empty fields", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//                }
//
//                binding.ivDelete.setOnClickListener {
//                    lifecycleScope.launch {
//                        viewModel.deleteNote(note)
//                        Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
//                        findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
//                    }
//                }
//            }
//        }
//
//        lifecycleScope.launch {
//            noteId.let { viewModel.getNoteById(it) }
//        }
//    }
}