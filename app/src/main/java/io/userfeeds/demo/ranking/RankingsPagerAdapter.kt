package io.userfeeds.demo.ranking

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.userfeeds.demo.contexts.ShareContext

class RankingsPagerAdapter(fm: FragmentManager, val shareContext: ShareContext, val algorithms: List<Algorithm>) : FragmentPagerAdapter(fm) {

    override fun getCount() = algorithms.size

    override fun getItem(position: Int) = RankingFragment.newInstance(shareContext, algorithms[position])

    override fun getPageTitle(position: Int) = algorithms[position].description
}
