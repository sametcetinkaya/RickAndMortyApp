package com.sametcetinkaya.rickandmortyapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sametcetinkaya.rickandmortyapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var characterAdapter: CharacterAdapter
    private val characterViewModel : CharacterViewModel by viewModels()
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterAdapter = CharacterAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategoriesRecyclerView()
        startSearchJob()
    }

    private fun setupCategoriesRecyclerView() {
        binding.characterRecyclerView.apply {
            adapter = characterAdapter
        }
    }

    private fun startSearchJob() {

        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            characterViewModel.getCharacterListData()
                .collectLatest {
                    characterAdapter.submitData(it)
                }
        }
    }
}