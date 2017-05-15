package io.userfeeds.demo.ranking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.userfeeds.demo.R
import kotlinx.android.synthetic.main.rankings_activity.*

class RankingsActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            val intent = Intent(context, RankingsActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rankings_activity)
        setSupportActionBar(toolbar)
    }
}
