package com.example.wallet

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.wallet.view.IUpdateUI
import com.example.wallet.presenter.PresenterForMain
import com.example.wallet.utils.Constant
import com.example.wallet.utils.UpdateUIReason
import com.example.wallet.view.FragmentStore
import com.example.wallet.view.FragmentWallet
import com.example.wallet.view.IFragmentState
import com.example.wallet.view.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), IUpdateUI {

    private var mPresenter: PresenterForMain? = null
    private var mViewPager: ViewPager? = null
    private var mStatusBar: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        mPresenter = PresenterForMain(this)
        mPresenter?.getStatus()

        mViewPager = findViewById<ViewPager>(R.id.view_pager)?.let { setupViewPager(it) }
        findViewById<TabLayout>(R.id.tab_layout)?.setupWithViewPager(mViewPager)

        mStatusBar = findViewById<TextView>(R.id.status_bar)
    }

    private fun setupViewPager(viewPager: ViewPager): ViewPager? {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FragmentStore(), "Store")
        adapter.addFragment(FragmentWallet(), "Wallet")
        viewPager.adapter = adapter
        ((viewPager.adapter as ViewPagerAdapter).getItem(0) as IFragmentState).pageSelected()

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                Log.d(Constant.TAG, "position :$position")
                ((viewPager.adapter as ViewPagerAdapter).getItem(position) as IFragmentState).pageSelected()
                ((viewPager.adapter as ViewPagerAdapter).getItem((position + 1) % 2) as IFragmentState).pageReleased()
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
        return viewPager
    }

    override fun getContext(): Context {
        return this
    }

    override fun updateUI(type: UpdateUIReason) {
        if(type == UpdateUIReason.UPDATE_UI_REASON_REQUEST_FAIL) {
            mStatusBar?.text = "Request fail"
        } else if (type == UpdateUIReason.UPDATE_UI_REASON_REQUEST_SUCCESS) {
            mStatusBar?.text = mPresenter?.getResponse().toString()
        }
    }
}