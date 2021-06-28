package com.pbbutton.pbbuttonlibrary

import android.content.Context
import android.content.pm.PackageManager
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity


class PayboxButton : AppCompatButton, View.OnClickListener {

    private var clickListener: OnClickListener? = null
    private var pbAmount = "0"

    constructor(context: Context) : this(context, null) {
        setOnClickListener(this)
        background = AppCompatResources.getDrawable(context, R.drawable.ic_launcher_round)
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        setOnClickListener(this)
        background = AppCompatResources.getDrawable(context, R.drawable.ic_launcher_round)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        setOnClickListener(this)
        background = AppCompatResources.getDrawable(context, R.drawable.ic_launcher_round)
    }

    override fun onClick(v: View?) {
        clickListener?.onClick(v)
        val activity = (v?.context) as? FragmentActivity
        val fragmentManager = activity?.supportFragmentManager
        fragmentManager?.let {
            activity?.packageManager?.getApplicationInfo(activity.packageName, PackageManager.GET_META_DATA)?.apply {
                val key = metaData?.getString("paybox_button_key")
                PayboxWebDialogFragment.newInstance("https://zeta-buckeye-574.web.app?uid=$key?amount=$pbAmount")
                    .show(fragmentManager, null)
            }
        }
    }

    fun setAmount(amount: Double) {
        pbAmount = roundDecimal(amount)
    }

    fun onPayboxClickListener(listener: OnClickListener?) {
        clickListener = listener
    }
    fun roundDecimal(number: Double) = "%.2f".format(number)
}