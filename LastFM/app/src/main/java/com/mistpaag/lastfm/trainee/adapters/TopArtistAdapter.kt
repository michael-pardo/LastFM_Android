package com.mistpaag.lastfm.trainee.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mistpaag.lastfm.trainee.R
import com.mistpaag.lastfm.trainee.databinding.TopArtistItemBinding
import com.mistpaag.lastfm.trainee.models.TopArtist
import com.mistpaag.lastfm.trainee.utils.inflate
import kotlinx.android.synthetic.main.top_artist_item.view.*

class TopArtistAdapter(val itemClick:(Int)-> Unit) : ListAdapter<TopArtist, TopArtistAdapter.ViewHolder>(TopArtistDiffCallback()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.top_artist_item), itemClick = itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("lol", position.toString())
        holder.bindTo(getItem(position))
    }




    class ViewHolder( itemView: View, val itemClick:(Int)-> Unit) : RecyclerView.ViewHolder(itemView){
        fun bindTo(topArtist: TopArtist){
            with(topArtist){
                itemView.artist_image.load(image)
                itemView.artist_name.text = name
                itemView.listeners_count.text = "$listeners Listeners"
            }

        }
    }

}



class TopArtistDiffCallback : DiffUtil.ItemCallback<TopArtist>() {
    override fun areItemsTheSame(oldItem: TopArtist, newItem: TopArtist): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: TopArtist, newItem: TopArtist): Boolean {
        return oldItem == newItem
    }
}
