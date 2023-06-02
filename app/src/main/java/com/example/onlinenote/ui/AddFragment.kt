package com.example.onlinenote.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.onlinenote.R
import com.example.onlinenote.data.models.NetworkNote
import com.example.onlinenote.databinding.FragmentAddBinding
import com.example.onlinenote.presentation.NetworkViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(R.layout.fragment_add) {
    private lateinit var binding: FragmentAddBinding
    private val viewModel: NetworkViewModel by viewModel()
    private var noteId: String = "-1"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddBinding.bind(view)

//        viewModel = ViewModelProvider(this)[NetworkViewModelImpl::class.java]

        val bundle = arguments
        if (bundle != null && bundle.containsKey("noteId"))
            noteId = bundle.getString("noteId").toString()
        if (noteId == "-1") {
            addNote()
        } else {
            updateNote()

            deleteNote()
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
                val note =
                    NetworkNote(id = "", title = title, text = text, lastUpdatedDate = currentDate)

                lifecycleScope.launch {
                    viewModel.addNote(note)
                    Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT)
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

    @SuppressLint("SimpleDateFormat")
    private fun updateNote() {
        val bundle = arguments
        if (bundle != null) {
            val noteId = bundle.getString("noteId")
            val title = bundle.getString("title")
            val text = bundle.getString("text")
            val lastUpdatedDate = bundle.getString("lastUpdatedDate")
            binding.apply {
                etTitle.setText(title)
                etText.setText(text)
            }

            binding.ivSave.setOnClickListener {
                if (binding.etText.text.toString().isNotEmpty() && binding.etTitle.text.toString()
                        .isNotEmpty()
                ) {
                    val _title = binding.etTitle.text.toString()
                    val _text = binding.etText.text.toString()
                    val _lastUpdatedDate = SimpleDateFormat("dd.MM.yyyy").format(Date())
                    val note = NetworkNote(
                        id = noteId,
                        title = _title,
                        text = _text,
                        lastUpdatedDate = _lastUpdatedDate
                    )
                    lifecycleScope.launch {
                        viewModel.updateNote(note)
                    }
                    Toast.makeText(requireContext(), "Successfully saved!", Toast.LENGTH_SHORT)
                        .show()
                    findNavController().navigate(AddFragmentDirections.actionAddFragmentToMainFragment())
                } else {
                    Toast.makeText(requireContext(), "Fill in empty fields", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun deleteNote() {
        val bundle = arguments
        if (bundle != null) {
            val title = bundle.getString("title", "null").toString()
            val text = bundle.getString("text", "null").toString()
            val lastUpdatedDate = bundle.getString("lastUpdatedDate", "null").toString()
            binding.apply {
                etTitle.setText(title)
                etText.setText(text)
            }

            binding.ivDelete.setOnClickListener {
                val note = NetworkNote(id = noteId, title, text, lastUpdatedDate)
                lifecycleScope.launch {
                    viewModel.deleteNote(note)
                }
                findNavController().navigate(R.id.action_addFragment_to_mainFragment)
            }
        }
    }
}