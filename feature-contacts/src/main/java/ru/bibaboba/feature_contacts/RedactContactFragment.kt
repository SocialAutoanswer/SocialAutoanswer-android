package ru.bibaboba.feature_contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.bibaboba.feature_contacts.databinding.FragmentRedactContactBinding


class RedactContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRedactContactBinding.inflate(inflater, container, false)



        return binding.root
    }

}