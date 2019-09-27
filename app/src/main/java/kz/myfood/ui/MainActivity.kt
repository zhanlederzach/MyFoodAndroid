package kz.myfood.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kz.myfood.R
import kz.myfood.utils.views.BaseFragment
import java.util.*

class MainActivity : AppCompatActivity(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemReselectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {

    // overall back stack of containers
    private val backStack = Stack<Int>()

    // list of base destination containers
    private val fragments = listOf(
        BaseFragment.newInstance(R.layout.content_home_base, R.id.toolbar_home, R.id.nav_host_home),
        BaseFragment.newInstance(R.layout.content_news_base, R.id.toolbar_news, R.id.nav_host_news),
        BaseFragment.newInstance(R.layout.content_profile_base, R.id.toolbar_profile, R.id.nav_host_profile)
    )

    // map of navigation_id to container index
    private val indexToPage = mapOf(0 to R.id.home, 1 to R.id.news, 2 to R.id.profile)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = BaseFragment.newInstance(R.layout.content_login_base, R.id.nav_host_login)
        setViewPager()
    }

    private fun setViewPager() {
        // setup main view pager
        mainPager.addOnPageChangeListener(this)
        mainPager.adapter = ViewPagerAdapter()
        mainPager.post(this::checkDeepLink)
        mainPager.offscreenPageLimit = fragments.size

        // set bottom nav
        bottomNav.setOnNavigationItemSelectedListener(this)
        bottomNav.setOnNavigationItemReselectedListener(this)

        // initialize backStack with elements
//        if (backStack.empty()) backStack.push(0)
    }

    /// BottomNavigationView ItemSelected Implementation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val position = indexToPage.values.indexOf(item.itemId)
        if (mainPager.currentItem != position) setItem(position)
        return true
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        val position = indexToPage.values.indexOf(item.itemId)
        val fragment = fragments[position]
        fragment.popToRoot()
    }

    override fun onBackPressed() {
        val fragment = fragments[mainPager.currentItem]
        val hadNestedFragments = fragment.onBackPressed()
        // if no fragments were popped
        if (!hadNestedFragments) {
            if (backStack.size > 1) {
                // remove current position from stack
                backStack.pop()
                // set the next item in stack as current
                mainPager.currentItem = backStack.peek()

            } else super.onBackPressed()
        }
    }

    /// OnPageSelected Listener Implementation
    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

    override fun onPageSelected(page: Int) {
        val itemId = indexToPage[page] ?: R.id.home
        if (bottomNav.selectedItemId != itemId) bottomNav.selectedItemId = itemId
    }

    private fun setItem(position: Int) {
        mainPager.currentItem = position
        backStack.push(position)
    }

    private fun checkDeepLink() {
        fragments.forEachIndexed { index, fragment ->
            val hasDeepLink = fragment.handleDeepLink(intent)
            if (hasDeepLink) setItem(index)
        }
    }

    inner class ViewPagerAdapter : FragmentPagerAdapter(supportFragmentManager) {

        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size

    }
}
