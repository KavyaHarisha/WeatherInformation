package com.kavya.weatherinformation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_github_dialog.*

class GithubDialog : DialogFragment(),View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_github_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        git_hub_img.setOnClickListener(this)
        git_hub_txt.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
             R.id.git_hub_img,R.id.git_hub_txt ->{
                 val url = getString(R.string.github_url)
                 val i = Intent(Intent.ACTION_VIEW)
                 i.data = Uri.parse(url)
                 startActivity(i)
             }
        }
    }
}
