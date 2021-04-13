package com.example.contactshw2.ContactDetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.contactshw2.Contact
import com.example.contactshw2.databinding.DetailsFragmentBinding
import com.example.contactshw2.databinding.DetailsItemBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: DetailsFragmentBinding

    companion object KEYS {
        const val CONTACT_KEY = "contact"

        fun newInstance(contact: Contact): DetailsFragment {
            val args = Bundle()
            args.putParcelable(CONTACT_KEY, contact)
            val fragment = DetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailsFragmentBinding.inflate(layoutInflater)
        val contact = arguments?.getParcelable<Contact>(CONTACT_KEY)

        contact?.let {
            binding.name.text = contact.name
            contact.phone.forEach { phone ->
                val item = DetailsItemBinding.inflate(inflater).root
                item.text = phone
                binding.linearLayout.addView(item)
                item.setOnClickListener { callTo(phone) }
            }
            contact.email.forEach { email ->
                val item = DetailsItemBinding.inflate(inflater).root
                item.text = email
                binding.linearLayout.addView(item)
                item.setOnClickListener { composeEmail(email) }
            }
        }
        return binding.root
    }

    private fun composeEmail(email: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, email)
        }
        intent.resolveActivity(requireContext().packageManager)?.let {
            startActivity(intent)
        }
    }

    private fun callTo(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        intent.resolveActivity(requireContext().packageManager)?.let {
            startActivity(intent)
        }
    }
}