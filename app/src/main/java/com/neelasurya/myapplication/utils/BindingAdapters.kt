package com.neelasurya.myapplication.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neelasurya.myapplication.R
import com.neelasurya.myapplication.utils.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value ->
            view.visibility = value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("mutableText")
fun setMutableText(view: TextView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value -> view.text = value ?: "" })
    }
}

@BindingAdapter("mutableImage")
fun setMutableImage(view: ImageView, text: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null) {
        text?.apply {
            observe(parentActivity, Observer {
                Glide.with(parentActivity).load(it).into(view)
            })
        } ?: Glide.with(parentActivity).load(R.drawable.ic_account_circle_black_24dp).into(view)
    }
}


@BindingAdapter("mutableStatusText")
fun setmutableStatusText(view: TextView, text: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            var textVal = ""
            when (value) {
                EnumStatusType.STATUS_QUEUE.value -> textVal = parentActivity.getString(R.string.queue)
                EnumStatusType.STATUS_TRANSIT.value -> textVal = parentActivity.getString(R.string.transit)
                EnumStatusType.STATUS_DELIVERED.value -> textVal = parentActivity.getString(R.string.delivered)
                EnumStatusType.STATUS_CANCELLED.value -> textVal = parentActivity.getString(R.string.cancelled)

            }
            view.text = textVal
        })

    }
}


@BindingAdapter("mutableButtonStatus")
fun setmutableButtonStatus(view: TextView, text: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            when (value) {
                EnumStatusType.STATUS_QUEUE.value -> view.text = parentActivity.getString(R.string.transit)
                EnumStatusType.STATUS_TRANSIT.value -> view.text = parentActivity.getString(R.string.delivered)
                else -> {
                    view.text = ""
                    view.visibility = View.GONE;
                }

            }
        })
    }
}

@BindingAdapter("mutableClickEnabled")
fun setmutableClickEnabled(view: View, status: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && status != null) {
        status.observe(parentActivity, Observer { value ->
            when (value) {
                EnumStatusType.STATUS_CANCELLED.value,
                EnumStatusType.STATUS_DELIVERED.value -> view.isEnabled = false;
                else ->
                    view.isEnabled = true;


            }

        })
    }
}



