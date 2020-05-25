package com.neelasurya.myapplication.feature.post

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DatabaseReference
import com.neelasurya.myapplication.base.BaseViewModel
import com.neelasurya.myapplication.model.Order
import com.neelasurya.myapplication.utils.EnumStatusType

class PostViewModel(var postDao: DatabaseReference) : BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postInfo = MutableLiveData<Int>()
    private val postAddress = MutableLiveData<String>()
    private val postNumber = MutableLiveData<String>()
    private val postImage = MutableLiveData<String>()
    private var postIsExpanded = MutableLiveData<Int>()


    fun bind(post: Order) {
        post.apply {
            postInfo.value = status
            name?.apply { postTitle.value = name }
            postAddress.value = address
            postNumber.value = contact
            postIsExpanded.value = isExpanded
        }
    }

    fun getPostIsExpanded(): MutableLiveData<Int> {
        return postIsExpanded
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostImage(): MutableLiveData<String> {
        return postImage
    }

    fun getPostInfo(): MutableLiveData<Int> {
        return postInfo
    }

    fun getPostAddress(): MutableLiveData<String> {
        return postAddress
    }

    fun getPostNumber(): MutableLiveData<String> {
        return postNumber
    }

    fun onExpandedClick(order: Order) {
        if (order.isExpanded == View.VISIBLE) order.isExpanded = View.GONE else order.isExpanded = View.VISIBLE

    }

    fun onCancelClick(order: Order) {
        order.isExpanded = View.GONE
        order.status = EnumStatusType.STATUS_CANCELLED.value;
        postDao.child(order.id.toString()).setValue(order);

    }

    fun onButtonClickClick(order: Order) {
        when (order.status) {
            EnumStatusType.STATUS_QUEUE.value -> order.status = EnumStatusType.STATUS_TRANSIT.value
            EnumStatusType.STATUS_TRANSIT.value -> {
                order.status = EnumStatusType.STATUS_DELIVERED.value
                order.isExpanded = View.GONE
            }

        }
        postDao.child(order.id.toString()).setValue(order);
    }

}