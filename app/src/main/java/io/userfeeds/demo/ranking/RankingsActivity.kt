package io.userfeeds.demo.ranking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.userfeeds.demo.R
import io.userfeeds.sdk.core.UserfeedsService
import io.userfeeds.sdk.core.algorithm.Algorithm
import io.userfeeds.sdk.core.context.ShareContext
import kotlinx.android.synthetic.main.rankings_activity.*

class RankingsActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context, shareContext: ShareContext) {
            val intent = Intent(context, RankingsActivity::class.java)
            intent.putExtra("context", shareContext)
            context.startActivity(intent)
        }
    }

    private val shareContext by lazy(LazyThreadSafetyMode.NONE) {
        intent.getParcelableExtra<ShareContext>("context")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rankings_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        UserfeedsService.get().getAlgorithms(shareContext)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAlgorithms, this::onError)
    }

    private fun onAlgorithms(algorithms: List<Algorithm>) {
        rankingsPager.adapter = RankingsPagerAdapter(supportFragmentManager, shareContext, algorithms)
        tabLayout.setupWithViewPager(rankingsPager)
    }

    private fun onError(error: Throwable) {
        Log.e("RankingsActivity", "error", error)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
