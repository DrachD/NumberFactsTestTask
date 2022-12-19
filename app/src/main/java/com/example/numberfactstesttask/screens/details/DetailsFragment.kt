package com.example.numberfactstesttask.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.common.util.getBaseParcelable
import com.example.numberfactstesttask.R
import com.example.numberfactstesttask.databinding.FragmentDetailsBinding
import com.example.numberfactstesttask.model.NumberFactsArgs

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getBaseParcelable(FACTS_KEY, NumberFactsArgs::class.java)

        binding.numberTextView.text = args?.number.toString()
        binding.factsTextView.text = args?.fact
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val FACTS_KEY = "FACTS"
    }
}