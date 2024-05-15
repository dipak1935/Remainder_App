package com.example.remainderapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.remainderapp.databinding.DialogEditReminderBinding
import com.example.remainderapp.databinding.FragmentGeneralInfoBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GeneralInfoFragment:Fragment() {
    private lateinit var binding: FragmentGeneralInfoBinding

    private val preferences by lazy { requireActivity().getSharedPreferences("GeneralInfo",Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentGeneralInfoBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayValues()

        binding.cardViewBinDay.setOnClickListener { showEditDialog(PREF_BIN_DAY) }
        binding.cardViewAdharNumber.setOnClickListener { showEditDialog(PREF_ADHAR_NUMBER) }
        binding.cardViewBirthdayDate.setOnClickListener { showEditDialog(PREF_BIRTHDAY_DATE) }

    }

    private fun showEditDialog(preferenceKey:String) {

        val dialogBinding=DialogEditReminderBinding.inflate(requireActivity().layoutInflater)

        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey,null))

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update Value")
            .setView(dialogBinding.root)
            .setPositiveButton("SAVE"){_,_->

                preferences.edit { putString(preferenceKey,dialogBinding.editTextValue.text?.toString()) }
                displayValues()

            }
            .setNegativeButton("CANCEL"){_,_->

            }
            .show()
    }

    private fun displayValues() {

        binding.textViewBinDayValue.text=preferences.getString(PREF_BIN_DAY,null)
        binding.textViewAdharNumberValue.text=preferences.getString(PREF_ADHAR_NUMBER,null)
        binding.textViewBirthdayDateValue.text=preferences.getString(PREF_BIRTHDAY_DATE,null)
    }

    companion object{

        const val PREF_BIN_DAY="pref_bin_day"
        const val PREF_ADHAR_NUMBER="pref_adhar_number"
        const val PREF_BIRTHDAY_DATE="pref_birthday_date"

    }
}