package tech.yeswecode.reporteciudadano.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.yeswecode.reporteciudadano.databinding.FragmentProfileBinding
import tech.yeswecode.reporteciudadano.models.User
import tech.yeswecode.reporteciudadano.utilities.ExtrasConstants

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getSerializable(ExtrasConstants.USER) as User?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        user?.let {
            binding.profileNameTxt.setText(it.name)
            binding.profileEmailTxt.setText(it.email)
        }
        binding.profileNameTxt.isEnabled = false
        binding.profileEmailTxt.isEnabled = false
        /* TODO: Complete the profile features
            Edit the profile, change password, etc
            New Feature: Change the profile pic,
            upload one and set it to the user
         */
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User?) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ExtrasConstants.USER, user)
            }
        }
    }
}