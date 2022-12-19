package com.example.numberfactstesttask.screens.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numberfactstesttask.R
import com.example.numberfactstesttask.adapters.NumbersAdapter
import com.example.common.model.NumberData
import com.example.common.model.NumberFactsData
import com.example.numberfactstesttask.BaseApplication
import com.example.numberfactstesttask.databinding.FragmentHomeBinding
import com.example.numberfactstesttask.di.HomeViewModelFactory
import com.example.numberfactstesttask.model.NumberFactsArgs
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    lateinit var viewModel: HomeViewModel

    private lateinit var adapter: NumbersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().applicationContext as BaseApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = NumbersAdapter { navigateToDetails(it) }
        binding.recyclerView.adapter = adapter

        binding.getFactButton.setOnClickListener {
            getFactsButtonPressed()
        }

        binding.getFactAboutRandomNumberButton.setOnClickListener {
            getFactAboutRandomNumberButtonPressed()
        }

        viewModel.getAllNumberFacts().observe(viewLifecycleOwner) { list ->
            updateUI(list)
        }

        observeShowMessage()
        observeGetInfoEvent()
    }

    private fun getFactsButtonPressed() {
        val number = binding.numberEditText.text.toString()
        val numberData = NumberData(number)

        viewModel.fetchFactsByNumber(numberData)
    }

    private fun getFactAboutRandomNumberButtonPressed() {
        viewModel.fetchFactsByRandomNumber()
    }

    private fun observeShowMessage() = viewModel.messageShowEvent
        .observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
            }
        }

    private fun observeGetInfoEvent() = viewModel.getInfoEvent
        .observe(viewLifecycleOwner) { event ->
            event.getContentIfNotHandled()?.let {

                viewModel.addNumberFacts(it)
                viewModel.getAllNumberFacts().observe(viewLifecycleOwner) { list ->
                    updateUI(list)
                }
            }
        }

    private fun updateUI(list: List<NumberFactsData>) {
        adapter.diffAsync.submitList(list)
    }

    private fun navigateToDetails(number: Int) {
        viewModel.getFactsByNumber(number).observe(viewLifecycleOwner) {
            val args = NumberFactsArgs(it.number, it.fact)
            val bundle = bundleOf(FACTS_KEY to args)
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val FACTS_KEY = "FACTS"
    }
}