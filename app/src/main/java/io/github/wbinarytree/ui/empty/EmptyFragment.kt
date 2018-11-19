package io.github.wbinarytree.ui.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.example.rally.R
import io.github.wbinarytree.base.BaseFragment

class EmptyFragment:BaseFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_empty,container,false)
    }
}