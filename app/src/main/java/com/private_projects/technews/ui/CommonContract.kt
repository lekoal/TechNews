package com.private_projects.technews.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.private_projects.technews.utils.ViewBindingFragment

interface CommonContract {

    abstract class CommonFragment<T: ViewBinding>(
        inflateBinding: (
            inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
        ) -> T
    ) : ViewBindingFragment<T>(inflateBinding) {

    }
}