package io.userfeeds.demo.ranking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.userfeeds.demo.R
import io.userfeeds.demo.contexts.ShareContext
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
        AlgorithmsApiProvider.get()
                .call(shareContext.id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onAlgorithms, this::onError)
    }

    private fun onAlgorithms(algorithms: AlgorithmsResponse) {
        algorithms.items.map { tabLayout.newTab().setText(it.description) }.forEach(tabLayout::addTab)
    }

    private fun onError(error: Throwable) {
        Log.e("ContextsActivity", "error", error)
    }
}
