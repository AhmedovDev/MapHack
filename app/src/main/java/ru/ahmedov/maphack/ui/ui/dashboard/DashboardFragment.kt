package ru.ahmedov.maphack.ui.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_dashboard.*
import ru.ahmedov.maphack.R


class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    var scanContent = ""
    var scanFormat = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val integrator = IntentIntegrator.forSupportFragment(this)
        integrator.setOrientationLocked(false)
        integrator.setPrompt("Scan QR code")
        integrator.setBeepEnabled(false)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        integrator.initiateScan()
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        @Nullable data: Intent?
    ) {
        val result =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
            } else {

                webView.getSettings().setJavaScriptEnabled(true)
                webView.getSettings().setLoadWithOverviewMode(true)
                webView.getSettings().setUseWideViewPort(true)
                webView.getSettings().setBuiltInZoomControls(true)
                webView.getSettings().setPluginState(WebSettings.PluginState.ON)
                webView.setWebViewClient(WebViewClient())

                webView.loadUrl(result.contents)
            }
        }
    }
}