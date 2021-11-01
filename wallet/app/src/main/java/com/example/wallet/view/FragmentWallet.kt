package com.example.wallet.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.R
import com.example.wallet.model.room.WalletEntity
import com.example.wallet.presenter.PresenterForWallet
import com.example.wallet.utils.Constant
import com.example.wallet.utils.UpdateUIReason

class FragmentWallet : BaseFragment(), WalletListAdapter.OnItemClickListener {

    private var mWalletPresenter: PresenterForWallet? = null
    private var mRecyclerView: RecyclerView? = null
    private var mWalletListAdapter: WalletListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWalletPresenter = PresenterForWallet(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var contentView = inflater.inflate(R.layout.fragment_wallet, container, false)
        initRecyclerView(contentView)
        return contentView
    }

    private fun initRecyclerView(contentView: View) {
        mRecyclerView = contentView.findViewById(R.id.wallet_list_view)
        mWalletListAdapter = context?.let { WalletListAdapter(it) }
        mWalletListAdapter?.onItemClickListener = this
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mWalletListAdapter?.let { mRecyclerView?.adapter = it }
    }

    override fun onClick(item: WalletEntity) {
        mWalletPresenter?.doActivate(item)
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun pageSelected() {
        mWalletPresenter?.getAllItems()
        Log.d(Constant.TAG, "FragmentWallet selected")
    }

    override fun pageReleased() {
        Log.d(Constant.TAG, "FragmentWallet released")
    }

    override fun updateUI(type: UpdateUIReason) {
        if (type == UpdateUIReason.UPDATE_UI_QUERY_WALLET_LIST) {
            mWalletListAdapter?.updateDataList(mWalletPresenter?.getDataSet())
        } else if (type == UpdateUIReason.UPDate_UI_UPDATE_WALLET_LIST) {
            mWalletPresenter?.getAllItems()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}