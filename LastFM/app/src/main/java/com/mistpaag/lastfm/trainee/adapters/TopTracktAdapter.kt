package com.mistpaag.lastfm.trainee.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.models.TopTrack
import com.mistpaag.lastfm.trainee.utils.inflate
import kotlinx.android.synthetic.main.top_artist_item.view.*

class TopTracktAdapter(val itemClick:(Int)-> Unit) : ListAdapter<TopTrack, TopTracktAdapter.ViewHolder>(TopTrackDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.top_track_item), itemClick = itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position), position)
    }

    class ViewHolder( itemView: View, var itemClick:(Int)-> Unit) : RecyclerView.ViewHolder(itemView){
        fun bindTo(topTrack: TopTrack, position: Int){
            with(topTrack){
                itemView.artist_image.load(image)
                itemView.artist_name.text = name
                itemView.listeners_count.text = "$listeners Listeners"
                itemView.setOnClickListener {
                    itemClick(position)
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
