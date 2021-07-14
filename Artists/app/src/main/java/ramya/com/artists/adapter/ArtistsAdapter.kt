package ramya.com.artists.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_artist.view.*
import ramya.com.artists.R
import ramya.com.artists.model.Artist
import ramya.com.artists.model.Artists


class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.PicsViewHolder>() {

    inner class PicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var artistsData: List<Artist>? = emptyList()

    fun setData(artists: Artists) {
        artistsData = artists.artists?.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PicsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_artist,
            parent,
            false
        )
    )

    override fun getItemCount() = artistsData?.size!!

    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
        val artist = artistsData?.get(position)
        Log.d("ramya", "onBindViewHolder: date " + artist?.releaseDate)
        holder.itemView.apply {
            tvArtistName.text = artist?.artistName
            tvTrackName.text = artist?.trackName
            tvReleaseDate.text =artist?.releaseDate.toString().split("T")[0]
            tvPrimaryGenerName.text = artist?.primaryGenreName
            tvTrackPrice.text = "$ "+artist?.trackPrice.toString()

        }
    }

}