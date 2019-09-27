package kz.myfood.utils

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BottomNavigationViewpagerAdapter(val listFramgents: ArrayList<Fragment>, val fm: FragmentManager)
    : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return listFramgents[position]
    }

    override fun getCount() = listFramgents.size

}