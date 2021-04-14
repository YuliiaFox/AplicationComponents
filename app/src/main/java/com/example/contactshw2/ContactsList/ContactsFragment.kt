package com.example.contactshw2.ContactsList

import android.Manifest
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactshw2.ContactsViewModel
import com.example.contactshw2.Navigation
import com.example.contactshw2.R
import com.example.contactshw2.databinding.ContactsFragmentBinding


class ContactsFragment : Fragment() {
    private lateinit var binding: ContactsFragmentBinding
    private val viewModel: ContactsViewModel by viewModels()
    private lateinit var navigation: Navigation
    private lateinit var batteryStatusReceiver: BroadcastReceiver

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                viewModel.loadContacts(requireContext())
                showDataAfterPermissionAllowed()
            } else {
                showPermissionDeniedText()
            }
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigation = context as Navigation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        viewModel.getContacts().observe(viewLifecycleOwner, { contacts ->
            val adapter = binding.contactsListRecyclerView.adapter as ContactAdapter
            adapter.dataSet = contacts
            adapter.notifyDataSetChanged()
            binding.contactsNumberLabel.text = getString(R.string.contactsQty, adapter.itemCount)
        })
    }

    override fun onResume() {
        super.onResume()
        requestPermission()
        registerBatteryStatusReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterBatteryStatusReceiver()
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) == PermissionChecker.PERMISSION_GRANTED
        ) {
            viewModel.loadContacts(requireContext())
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                showAlertDialog()
            } else {
                requestPermission.launch(Manifest.permission.READ_CONTACTS)
            }
        }
    }

    private fun initView() {
        val recyclerView = binding.contactsListRecyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        val adapter = ContactAdapter { position ->
            val adapter = recyclerView.adapter as ContactAdapter
            navigation.openContactDetails(adapter.dataSet[position])
        }
        recyclerView.apply {
            setLayoutManager(layoutManager)
            setAdapter(adapter)
        }
    }

    private fun showPermissionDeniedText() {
        binding.apply {
            recyclerViewGroup.visibility = INVISIBLE
            requestPermissionDeniedText.visibility = VISIBLE
        }
    }

    private fun showDataAfterPermissionAllowed() {
        binding.apply {
            recyclerViewGroup.visibility = VISIBLE
            requestPermissionDeniedText.visibility = INVISIBLE
        }
    }

    private fun showAlertDialog() {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    R.string.positiveButton
                ) { _, _ ->
                    requestPermission.launch(Manifest.permission.READ_CONTACTS)
                }
                setNegativeButton(
                    R.string.negativeButton
                ) { _, _ ->
                    showPermissionDeniedText()
                }
                setMessage(R.string.requestPermisisonReason)
            }
            builder.show()
        }
    }

    private fun registerBatteryStatusReceiver() {
        batteryStatusReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                when (intent?.getIntExtra(EXTRA_PLUGGED, -1) ?: -1) {
                    BATTERY_PLUGGED_AC,
                    BATTERY_PLUGGED_USB,
                    BATTERY_PLUGGED_WIRELESS ->
                        binding.chargeStatus.text = getString(R.string.IsCharging)
                    else -> binding.chargeStatus.text = getString(R.string.NotCharging)
                }
            }
        }
        requireContext()
            .registerReceiver(batteryStatusReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
    }

    private fun unregisterBatteryStatusReceiver() {
        requireContext()
            .unregisterReceiver(batteryStatusReceiver)
    }
}