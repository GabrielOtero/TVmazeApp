package com.otero.tvmazeapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.otero.tvmazeapp.R
import com.otero.tvmazeapp.domain.model.TvShowModel

class TvShowAdapter(private val cardClickListener: (Int) -> Unit) :
    ListAdapter<TvShowModel, ViewHolder>(ParticipantRulesDiffcalback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tv_show_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), cardClickListener)
    }

    class ParticipantRulesDiffcalback : DiffUtil.ItemCallback<TvShowModel>() {
        override fun areItemsTheSame(
            oldItem: TvShowModel,
            newItem: TvShowModel
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: TvShowModel,
            newItem: TvShowModel
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var tvShowId: TextView = itemView.findViewById(R.id.tv_show_id)
    private var tvShowCard: ConstraintLayout = itemView.findViewById(R.id.tv_show_card)

    fun bind(
        item: TvShowModel,
        cardClickListener: (Int) -> Unit
    ) {
        tvShowId.text = item.id.toString()
        tvShowCard.setOnClickListener { cardClickListener(item.id) }
    }

}
