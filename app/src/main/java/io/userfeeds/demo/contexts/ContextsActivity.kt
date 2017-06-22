package io.userfeeds.demo.contexts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.userfeeds.widget.analytics.firebase.FirebaseAnalyticsEventLogger
import io.userfeeds.demo.R
import io.userfeeds.demo.ranking.RankingsActivity
import io.userfeeds.sdk.core.UserfeedsService
import io.userfeeds.sdk.core.context.ShareContext
import kotlinx.android.synthetic.main.contexts_activity.*

class ContextsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contexts_activity)
        setSupportActionBar(toolbar)
        initAdViewAnalytics()
        UserfeedsService.get().getContexts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onContexts, this::onError)
    }

    private fun initAdViewAnalytics() {
        adView.addListener(FirebaseAnalyticsEventLogger(this))
    }

    private fun onContexts(contexts: List<ShareContext>) {
        contextList.layoutManager = LinearLayoutManager(this)
        contextList.adapter = ContextListAdapter(contexts) {
            RankingsActivity.start(this, it)
        }
    }

    private fun onError(error: Throwable) {
        Log.e("ContextsActivity", "error", error)
    }
}
