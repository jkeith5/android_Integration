package com.example.integration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        setupDrawerContentTransition(this.navigationView)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (toggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContentTransition(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { item ->
            drawMenuItemSelected(item)
            true
        }
    }

    fun drawMenuItemSelected(menuItem: MenuItem) {
        var fragmentClass: Class<*>? = null

        when (menuItem.itemId) {
            R.id.menu_item_home -> fragmentClass = HomeFragment::class.java
            R.id.menu_item_photos -> fragmentClass = PhotosFragment::class.java
            else -> {
            }
        }

        if (fragmentClass != null) {
            try {
                val mFragment = fragmentClass.newInstance() as Fragment
                val fragmentManager = supportFragmentManager
                fragmentManager.beginTransaction().replace(R.id.fragmentContent, mFragment).commit()
                menuItem.isChecked = true
                title = menuItem.title
                drawer_layout.closeDrawers()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}
