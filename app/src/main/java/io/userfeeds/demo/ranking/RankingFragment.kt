package io.userfeeds.demo.ranking

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.userfeeds.demo.R
import io.userfeeds.sdk.core.algorithm.Algorithm
import io.userfeeds.sdk.core.context.ShareContext
import io.userfeeds.sdk.core.ranking.RankingItem
import io.userfeeds.sdk.core.ranking.getRanking
import kotlinx.android.synthetic.main.ranking_fragment.*
import pl.mg6.rxjava2.disposeondestroy.disposeOnDestroyView

class RankingFragment : Fragment() {

    companion object {

        fun newInstance(shareContext: ShareContext, algorithm: Algorithm) = RankingFragment().apply {
            arguments = Bundle().apply {
                putParcelable("context", shareContext)
                putParcelable("algorithm", algorithm)
            }
        }
    }

    private val shareContext by lazy(LazyThreadSafetyMode.NONE) {
        arguments.getParcelable<ShareContext>("context")
    }

    private val algorithm by lazy(LazyThreadSafetyMode.NONE) {
        arguments.getParcelable<Algorithm>("algorithm")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.ranking_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getRanking(shareContext, algorithm)
                .observeOn(AndroidSchedulers.mainThread())
                .disposeOnDestroyView(this)
                .doFinally { progressBar.visibility = View.GONE }
                .subscribe(this::onRanking, this::onError)
    }

    private fun onRanking(ranking: List<RankingItem>) {
        rankingList.layoutManager = LinearLayoutManager(activity)
        rankingList.adapter = RankingAdapter(ranking) { item, label ->
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, item.value)
            intent.putExtra("io.userfeeds.share.context.id", shareContext.id)
            intent.putExtra("io.userfeeds.share.context.hashtag", shareContext.hashtag)
            intent.putExtra("io.userfeeds.share.context.imageUrl", shareContext.imageUrl)
            intent.putExtra("io.userfeeds.share.label", label)
            activity.startActivity(intent)
        }
    }

    private fun onError(error: Throwable) {
        Log.e("RankingFragment", "error", error)
    }
}
