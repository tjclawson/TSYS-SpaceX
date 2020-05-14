package com.tjclawson.spacex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tjclawson.spacex.R
import kotlinx.android.synthetic.main.fragment_launch_list.*

class LaunchListFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Gets view model with state to preserve recycler view state
        viewModel = ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(activity?.application, this)
        )
            .get(SharedViewModel::class.java)
        initRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveAdapterState(rv_launch_list.layoutManager?.onSaveInstanceState())
    }

    private fun initRecyclerView() {
        val launchAdapter = LaunchAdapter {
            selectLaunch(it)
        }

        rv_launch_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = launchAdapter
        }

        // Sets observer on launchList in view model to submit list to adapter when it changes
        viewModel.launchList.observe(viewLifecycleOwner, Observer {
            if (it != null) launchAdapter.submitList(it)
        })

        // Restores adapter scroll position when orientation changes
        rv_launch_list.layoutManager?.onRestoreInstanceState(viewModel.getAdapterState())
    }

    private fun selectLaunch(position: Int) {
        viewModel.selectLaunch(position)

        // Only launches detail fragment if in portrait mode
        if (!viewModel.twoPane) {
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.main_container, LaunchDetailFragment())
                .commit()
        }
    }
}
