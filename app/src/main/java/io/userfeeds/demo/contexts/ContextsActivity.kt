package io.userfeeds.demo.contexts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.main_activity.*

class ContextsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ContextsApiProvider.get()
                .call()
                .map(this::toContextList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onContexts, this::onError)
    }

    private fun toContextList(contexts: Map<String, ContextFromApi>): List<ShareContext> {
        return contexts.map { (id, context) -> toContext(id, context) }
    }

    private fun toContext(id: String, context: ContextFromApi): ShareContext {
        return ShareContext(
                id = id,
                hashtag = context.hashtag,
                imageUrl = "https://beta.userfeeds.io/api/contexts${context.images.avatar}"
        )
    }

    private fun onContexts(contexts: List<ShareContext>) {
        contextList.layoutManager = LinearLayoutManager(this)
        contextList.adapter = ContextListAdapter(contexts) {
        }
    }

    private fun onError(error: Throwable) {
        Log.e("ContextsActivity", "error", error)
    }
}
