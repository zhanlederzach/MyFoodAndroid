package kz.myfood.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import kz.myfood.R
import kz.myfood.repositories.LocalStorageImpl
import kz.myfood.repositories.LocalStorageImpl.Companion.IS_REGISTERED
import kz.myfood.utils.views.BaseFragment
import androidx.navigation.NavOptions


class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private var isRegistered = true

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        if(isRegistered){
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment, MainHostFragment())
                .commit()
        }else{
            val myNavHostFragment: NavHostFragment = nav_host_fragment as NavHostFragment
            val inflater = myNavHostFragment.navController.navInflater
            val graph = inflater.inflate(R.navigation.main_graph)
            myNavHostFragment.navController.graph = graph
        }

//        val fragment = BaseFragment.newInstance(R.layout.content_login_base, R.id.nav_host_login)
    }

}
