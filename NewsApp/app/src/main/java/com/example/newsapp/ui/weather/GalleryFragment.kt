package com.example.newsapp.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.WebViewController
import com.example.newsapp.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //to use channel link as webview in app
        val web=root.findViewById<WebView>(R.id.webView2)
        web.loadUrl("https://weather.com/")

        //to open links in app instead of chrome we have to make class that extends webviewclient and then
        //it is used here like this.
        val controller: WebViewController = WebViewController()
        web.webViewClient=controller

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}