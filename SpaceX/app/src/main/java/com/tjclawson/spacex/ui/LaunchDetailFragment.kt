package com.tjclawson.spacex.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

import com.tjclawson.spacex.R
import com.tjclawson.spacex.model.Launch
import kotlinx.android.synthetic.main.fragment_launch_detail.*

class LaunchDetailFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_launch_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.selectedLaunch.observe(viewLifecycleOwner, Observer {
            it?.let { initUI(it) }
        })
    }

    private fun initUI(selectedLaunch: Launch) {
        Picasso.get().load(selectedLaunch.links.missionPatchSmall).into(iv_detail_launch_image)
        tv_detail_mission_name.text = "Mission Name: " + selectedLaunch.missionName
        tv_detail_rocket_name.text = "Rocket Name: " + selectedLaunch.rocket.rocketName
        tv_detail_site_name.text = "Launch Site: " + selectedLaunch.launchSite.siteNameLong
        tv_detail_launch_date.text = "Launch Date: " + selectedLaunch.launchDateLocal
        tv_detail_flight_number.text = "Flight Number: " + selectedLaunch.flightNumber.toString()
        tv_detail_rocket_type.text = "Rocket Type: " + selectedLaunch.rocket.rocketType
        tv_detail_launch_success.text = "Suceeded: " + selectedLaunch.launchSuccess.toString()
        tv_detail_launch_details.text = "Details: " + selectedLaunch.details
        tv_detail_rocket_payloads.text = "No. of Payloads: " + selectedLaunch.rocket.secondStage.payloads.size
    }
}
