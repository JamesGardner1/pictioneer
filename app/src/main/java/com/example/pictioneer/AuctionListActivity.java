package com.example.pictioneer;

import androidx.fragment.app.Fragment;

public class AuctionListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment() { return new AuctionListFragment();}

}
