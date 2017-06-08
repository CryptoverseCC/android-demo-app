package io.userfeeds.demo.ranking

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.userfeeds.demo.R
import io.userfeeds.sdk.core.ranking.RankingItem

class RankingAdapter(
        private val rankingItems: List<RankingItem>,
        private val onShare: (RankingItem, String) -> Unit)
    :
        RecyclerView.Adapter<RankingAdapter.Holder>() {

    override fun getItemCount() = rankingItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.ranking_item, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = rankingItems[position]
        val nameView = holder.itemView.findViewById(R.id.name) as TextView
        nameView.text = item.target
        holder.itemView.findViewById(R.id.like).setOnClickListener { onShare(item, "positive") }
        holder.itemView.findViewById(R.id.dislike).setOnClickListener { onShare(item, "negative") }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
