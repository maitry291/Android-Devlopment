package com.example.newsapp.ui.BBC

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
import com.example.newsapp.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //to use channel link as webview in app
        val web=root.findViewById<WebView>(R.id.webView3)
        web.loadUrl("https://www.bbc.com/news/av/10473693")

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