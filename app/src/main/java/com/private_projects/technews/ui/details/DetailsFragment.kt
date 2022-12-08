package com.private_projects.technews.ui.details

import android.os.Bundle
import com.private_projects.technews.databinding.FragmentDetailsBinding
import com.private_projects.technews.utils.ViewBindingFragment

class DetailsFragment :
    ViewBindingFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    companion object {
        private const val DOMAIN_NAME = "domain_name"
        private const val URL_LINK = "url_link"
        fun newInstance(domain: String, url: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(DOMAIN_NAME, domain)
                    putString(URL_LINK, url)
                }
            }
    }
}