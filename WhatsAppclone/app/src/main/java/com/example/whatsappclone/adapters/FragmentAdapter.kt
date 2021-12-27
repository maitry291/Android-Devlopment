package com.example.whatsappclone.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.whatsappclone.tabs.CallFragment
import com.example.whatsappclone.tabs.ChatFragment
import com.example.whatsappclone.tabs.StatusFragment

class FragmentAdapter( var fm :FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title:String?=null
        when(position){
            0->title="Chats"
            1->title="Status"
            2->title="Calls"

            else->title="Chat"
        }
        return title
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0->return ChatFragment()
            1->return StatusFragment()
            2->return CallFragment()

            else->return ChatFragment()
        }
    }
}