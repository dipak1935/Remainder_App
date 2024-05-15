package com.example.remainderapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.remainderapp.databinding.DialogEditReminderBinding
import com.example.remainderapp.databinding.FragmentPasswordsBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PasswordsFragment:Fragment() {
    private lateinit var binding:FragmentPasswordsBinding

    private val preferences by lazy { requireActivity().getSharedPreferences("passwords",Context.MODE_PRIVATE) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentPasswordsBinding.inflate(inflater,container,false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayValues()


        binding.cardViewWifi.setOnClickListener { showEditDialog(PREF_WIFI) }
        binding.cardViewTabletPin.setOnClickListener { showEditDialog(PREF_TABLET_PIN) }
        binding.cardViewBikeLock.setOnClickListener { showEditDialog(PREF_BIKE_PIN) }
    }

    private fun showEditDialog(preferenceKey: String) {
        /*
        * for setView(), we can pass either R.layout.your_custom_dialog_box or we can
        * use the viewbinding and inflate the custom dialog box and pass that to the method
        *
        */

        val dialogBinding=DialogEditReminderBinding.inflate(requireActivity().layoutInflater)

        dialogBinding.editTextValue.setText(preferences.getString(preferenceKey,null))

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Update Value")
            .setView(dialogBinding.root)
            .setPositiveButton("SAVE"){_,_ ->
                preferences.edit { putString(preferenceKey,dialogBinding.editTextValue.text?.toString()) }
                displayValues()

            }
            .setNegativeButton("CANCEL"){_,_ ->

            }.show()

    }

    private fun displayValues() {


        binding.textViewWifiValue.text=preferences.getString(PREF_WIFI,null)
        binding.textViewTabletPinValue.text=preferences.getString(PREF_TABLET_PIN,null)
        binding.textViewBikeLockValue.text=preferences.getString(PREF_BIKE_PIN,null)


    }

    companion object{

        const val PREF_WIFI="pref_wifi"
        const val PREF_TABLET_PIN="pref_tablet_pin"
        const val PREF_BIKE_PIN="pref_bike_pin"

    }

}

