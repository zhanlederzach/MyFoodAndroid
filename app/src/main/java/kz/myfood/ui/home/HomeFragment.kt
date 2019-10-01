package kz.myfood.ui.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kz.myfood.R
import kz.myfood.ui.login.AuthViewModel
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var btnLogout: Button
    private val viewModel: AuthViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("HomeFragment", "onViewCreated: ");
        bindViews(view)
        setData()
    }

    private fun bindViews(view: View) {
        btnLogout = view.findViewById(R.id.btnLogout)

        btnLogout.setOnClickListener {
            viewModel.unregisterUser()
            activity?.finish()
            findNavController().navigate(R.id.logout)
        }
    }

    private fun setData() {

    }

}
