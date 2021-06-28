package com.pbbutton.pbbuttonlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.fragment.app.DialogFragment

class PayboxWebDialogFragment : DialogFragment() {

    private var webView: WebView? = null
    private var exitIv: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_fragment_paybox_web, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.web_dialog_wv)
        exitIv = view.findViewById(R.id.exit_iv)
        initWebViewSettings()

        webView?.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        webView?.webChromeClient = object : WebChromeClient() {}
        val url = arguments?.getString(TERMS_URL)
        url?.let {
            webView?.loadUrl(url)
        }
        //dialog?.window?.statusBarColor = resources.getColor(R.color.paybox_color_statusbar_default, null)

        exitIv?.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()

        dialog?.window?.setWindowAnimations(R.style.PayboxAnimation_Window)
    }

    override fun onResume() {
        super.onResume()
        val params = dialog?.window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        dialog?.window?.attributes = params
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }

    private fun initWebViewSettings() {
        val settings = webView?.settings
        settings?.javaScriptEnabled = true
        settings?.allowContentAccess = true
        settings?.allowFileAccess = true
        settings?.setSupportZoom(true)
        settings?.displayZoomControls = true
    }

    companion object {
        const val TERMS_URL = "terms_url"

        @JvmStatic
        fun newInstance(url: String): DialogFragment {
            val args = Bundle()
            args.putString(TERMS_URL, url)
            val dialogFragment = PayboxWebDialogFragment()
            dialogFragment.arguments = args
            return dialogFragment
        }
    }
}