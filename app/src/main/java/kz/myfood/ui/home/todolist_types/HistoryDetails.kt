package kz.myfood.ui.home.todolist_types


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kz.myfood.R

/**
 * A simple [Fragment] subclass.
 */
class HistoryDetails : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history_details, container, false)
    }


}