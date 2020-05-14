package com.tjclawson.spacex.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.tjclawson.spacex.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Sets twoPane to true if in landscape, main_container will be null if activity_main (land)
        // layout is inflated
        viewModel.twoPane = main_container == null

        // Replaces appropriate layout based on screen orientation
        if (viewModel.twoPane) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_item_list, LaunchListFragment())
                .replace(R.id.frame_item_detail, LaunchDetailFragment())
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, LaunchListFragment())
                .commit()
        }
    }
}
