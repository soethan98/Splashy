package com.example.soe_than.splashy.ui.ui.activity.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import com.example.soe_than.splashy.R
import com.example.soe_than.splashy.ui.ui.CollectionPhotos.CollectionsFragment
import com.example.soe_than.splashy.ui.ui.FeaturedPhotos.FeaturedFragment
import com.example.soe_than.splashy.ui.ui.NewPhotos.NewFragment
import com.example.soe_than.splashy.ui.utils.ConstantsUtils.CURRENT_TAG
import com.example.soe_than.splashy.ui.utils.ConstantsUtils.TAG_COLLECTON
import com.example.soe_than.splashy.ui.utils.ConstantsUtils.TAG_FEATURED
import com.example.soe_than.splashy.ui.utils.ConstantsUtils.TAG_NEW
import com.example.soe_than.splashy.ui.utils.ConstantsUtils.navItemIndex
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.app_bar_main.*
import android.util.Log
import android.support.v7.app.AppCompatDelegate
import android.support.v7.preference.PreferenceManager
import com.example.soe_than.splashy.ui.ui.activity.setting.SettingActivity


class MainActivity : AppCompatActivity() {


    lateinit var activityTiles: Array<String>
    lateinit var toolbar: Toolbar

    lateinit var mHandler: Handler

    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainActivityViewModel
    lateinit var  sharedPreferences: SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this@MainActivity)

        getThemePref(sharedPreferences.getString("key_theme", ""))




        viewModelFactory = MainViewModelFactory.provideMainViewModelFactory(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)




        toolbar = findViewById(R.id.tbar)
        setSupportActionBar(toolbar)
        mHandler = Handler()
        activityTiles = resources.getStringArray(R.array.nav_item_activity_titles)


        setUpNavigationView()


        savedInstanceState.let {
            navItemIndex = 0;
            CURRENT_TAG = TAG_NEW
            loadHomeFragment()
        }



    }

    fun loadHomeFragment() {
        setToolbarTitle()
        selectNavMenu()


        if (supportFragmentManager.findFragmentByTag(CURRENT_TAG) != null) {
            drawer_layout.closeDrawers();
            return;
        }

        val mPendingRunnable = Runnable {
            // update the main content by replacing fragments
            val fragment = getHomeFragment()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out)
            fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG)
            fragmentTransaction.commitAllowingStateLoss()
        }
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        drawer_layout.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    fun setToolbarTitle() {
        supportActionBar!!.setTitle(activityTiles[navItemIndex])
    }

    fun selectNavMenu() {
        nav_view.getMenu().getItem(navItemIndex).setChecked(true)
    }

    fun getHomeFragment(): Fragment {
        when (navItemIndex) {
            0 -> {
                var newFragment = NewFragment()
                return newFragment
            }
            1 -> {
                var featured = FeaturedFragment()
                return featured
            }
            2 -> {
                var collection = CollectionsFragment()
                return collection
            }
            else -> {
                var newFragment = NewFragment()
                return newFragment
            }
        }

    }

    fun setUpNavigationView() {
        nav_view.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {

            // This method will trigger on item Click of navigation menu
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {

                //Check to see which item was being clicked and perform appropriate action
                when (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    R.id.nav_new -> {
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_NEW;
                    }
                    R.id.nav_featured -> {
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_FEATURED
                    }
                    R.id.nav_collection -> {
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_COLLECTON
                    }
                    R.id.nav_settings -> {
                        var intent = Intent(this@MainActivity, SettingActivity::class.java)

                        startActivity(intent)


                    }

                    else -> navItemIndex = 0

                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false)
                } else {
                    menuItem.setChecked(true)
                }
                menuItem.setChecked(true)

                loadHomeFragment()

                return true
            }
        })
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawer_layout,
                tbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(view: View) {
                super.onDrawerClosed(view)
                //toast("Drawer closed")
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                //toast("Drawer opened")
            }
        }

        //Setting the actionbarToggle to drawer layout
        drawer_layout.setDrawerListener(drawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        drawerToggle.syncState();


    }


    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawers();
            return;
        }

        if (true) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_NEW;
                loadHomeFragment();
                return;
            }
        }
        super.onBackPressed()
    }





    fun getThemePref(theme: String) {
        when (theme) {
            "0" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "1" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


    }

    override fun onResume() {
        super.onResume()
        Log.i("Hi","onResume")
//        getThemePref(sharedPreferences.getString("key_theme", ""))

    }


    override fun onRestart() {
        super.onRestart()
        Log.i("Hi","onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Hi","onPause")

    }


}



