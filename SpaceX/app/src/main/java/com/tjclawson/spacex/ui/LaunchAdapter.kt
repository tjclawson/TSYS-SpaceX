package com.tjclawson.spacex

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tjclawson.spacex.model.Launch
import kotlinx.android.synthetic.main.launch_list_view.view.*

class LaunchAdapter(private val onItemClicked: (position: Int) -> Unit)
    : RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {

    private var launchList = mutableListOf<Launch>()
    // selected var to keep track of which launch the user has selected so that it can be highlighted
    private var selected: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.launch_list_view, parent, false)
        return LaunchViewHolder(view)
    }

    override fun getItemCount() = launchList.size

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val data = launchList[position]
        holder.missionNameView.text = data.missionName
        holder.rocketNameView.text = data.rocket.rocketName
        holder.siteNameView.text = data.launchSite.siteName
        // Splits UTC string format to just show YYYY-MM-DD
        holder.launchDateView.text = data.launchDateLocal.split("T")[0]
        Picasso.get().load(data.links.missionPatchSmall).into(holder.launchImageView)

        // highlights launch if it has been selected
        if (position == selected) holder.parentCardView.setCardBackgroundColor(Color.GRAY)
        else holder.parentCardView.setCardBackgroundColor(Color.WHITE)

        holder.parentCardView.setOnClickListener {
            // set click listener to selectLaunch
            selectLaunch(position)
            // invokes custom click listener passed in when adapter is instantiated
            onItemClicked.invoke(position)
        }
    }

    // takes new list, sorts by unix time descending so launches are listed from newest to oldest
    // then clears launchList and adds new list
    fun submitList(list: MutableList<Launch>) {
        launchList.clear()
        launchList.addAll(list)
        notifyDataSetChanged()
    }

    // set selected to position of launch user clicked, notifies last launch selected
    // and new selected launch to re-bind data
    private fun selectLaunch(position: Int) {
        val lastSelected = selected
        selected = position
        lastSelected?.let { notifyItemChanged(it) }
        selected?.let { notifyItemChanged(it) }
    }

    inner class LaunchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val missionNameView: TextView = view.tv_list_mission_name
        val rocketNameView: TextView = view.tv_list_rocket_name
        val siteNameView: TextView = view.tv_list_site_name
        val launchDateView: TextView = view.tv_list_launch_date
        val launchImageView: ImageView = view.iv_list_launch_image
        val parentCardView: CardView = view.cv_list_parent
    }
}