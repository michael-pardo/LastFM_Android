package com.mistpaag.lastfm.trainee.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.models.database.TopTrack
import com.mistpaag.lastfm.trainee.utils.inflate
import kotlinx.android.synthetic.main.top_track_item.view.*

class TopTrackAdapter(val itemClick:(TopTrack)-> Unit) : ListAdapter<TopTrack, TopTrackAdapter.ViewHolder>(TopTrackDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.top_track_item), itemClick = itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    class ViewHolder( itemView: View, var itemClick:(TopTrack)-> Unit) : RecyclerView.ViewHolder(itemView){
        fun bindTo(topTrack: TopTrack){
            with(topTrack){
                itemView.track_image.load(image)
                itemView.track_name.text = name
                itemView.listeners_track_count.text = "$listeners Listeners"
                itemView.time_count.text = "$duration"
                itemView.setOnClickListener {
                    itemClick(topTrack)
                }
            }

        }
    }

}



class TopTrackDiffCallback : DiffUtil.ItemCallback<TopTrack>() {
    override fun areItemsTheSame(oldItem: TopTrack, newItem: TopTrack): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TopTrack, newItem: TopTrack): Boolean {
        return oldItem == newItem
    }
}
