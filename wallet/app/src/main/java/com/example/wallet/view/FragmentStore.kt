package com.example.wallet.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.R
import com.example.wallet.model.ProductItem
import com.example.wallet.presenter.PresenterForStore
import com.example.wallet.utils.Constant
import com.example.wallet.utils.UpdateUIReason

class FragmentStore : BaseFragment(), StoreListAdapter.OnItemClickListener {

    private var mStorePresenter: PresenterForStore? = null
    private var mRecyclerView: RecyclerView? = null
    private var mStoreListAdapter: StoreListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mStorePresenter = PresenterForStore(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var contentView = inflater.inflate(R.layout.fragment_store, container, false)
        initRecyclerView(contentView)
        return contentView
    }

    private fun initRecyclerView(contentView: View) {
        mRecyclerView = contentView.findViewById(R.id.store_list_view)
        mStoreListAdapter = context?.let { StoreListAdapter(it) }
        mStoreListAdapter?.onItemClickListener = this
        mRecyclerView?.layoutManager = LinearLayoutManager(context)
        mStoreListAdapter?.let { mRecyclerView?.adapter = it }
        mStoreListAdapter?.updateDataList(mStorePresenter?.createMockData())
    }

    override fun onClick(item: ProductItem) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle("Confirm to buy")
                .setNegativeButton("No") { dialog, _ ->
                    dialog?.dismiss()
                }.setPositiveButton("Yes") { _, _->
                    mStorePresenter?.doInsertItem(item)
                }
                .show()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    @Synchronized
    override fun pageSelected() {
        Log.d(Constant.TAG, "FragmentStore selected")
        mStoreListAdapter?.updateDataList(mStorePresenter?.createMockData())
    }

    override fun pageReleased() {
        Log.d(Constant.TAG, "FragmentStore released")
    }

    override fun updateUI(type: UpdateUIReason) {
    }

    override fun onDestroy() {
        super.onDestroy()
        mStoreListAdapter?.destroy()
        mStoreListAdapter = null
    }
}