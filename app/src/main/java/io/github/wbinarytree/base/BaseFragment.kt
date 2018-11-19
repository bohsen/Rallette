package io.github.wbinarytree.base

import android.os.Bundle
import android.util.Log
import android.view.View


/**
 * Created by yaoda on 1/30/18.
 */
open class BaseFragment : androidx.fragment.app.Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(this.javaClass.name, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(this.javaClass.name, "onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(this.javaClass.name, "onStart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(this.javaClass.name, "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(this.javaClass.name, "onDestroyView")
    }


}