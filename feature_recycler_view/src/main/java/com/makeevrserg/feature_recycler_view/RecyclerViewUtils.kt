package su.crm.glavcontact.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addOnEndReachedListener(onEndReached: () -> Unit) {
    addOnScrollListener(object : EndReachedScrollManager() {
        override fun onEndReached() {
            onEndReached()
        }
    })
}

abstract class EndReachedScrollManager : RecyclerView.OnScrollListener() {
    abstract fun onEndReached()
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val manager = (recyclerView.layoutManager as LinearLayoutManager)
        val lastVisibleIndex =
            (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        if (lastVisibleIndex == manager.itemCount - 1)
            onEndReached()
    }
}