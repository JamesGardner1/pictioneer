package com.example.pictioneer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AuctionListFragment extends Fragment {

    private RecyclerView mAuctionRecyclerView;
    private AuctionAdapter mAdapter;

    private class AuctionHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private AuctionItem mAuctionItem;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSoldImageView;

        public AuctionHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_auction, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.auction_title);
            mDateTextView = itemView.findViewById(R.id.auction_date);
            mSoldImageView = itemView.findViewById(R.id.auction_sold);
        }

        //Crime Layout Display

        public void bind(AuctionItem auction) {
            mAuctionItem = auction;
            mTitleTextView.setText(mAuctionItem.getTitle());
            mDateTextView.setText(mAuctionItem.getDate().toString());
            mSoldImageView.setVisibility(auction.isSold() ? View.VISIBLE : View.GONE);
        }

        @Override

        // Display Crime Details
        public void onClick(View view) {
            Intent intent = MainActivity.newIntent(getActivity(), mAuctionItem.getAuctionId());
            startActivity(intent);
        }
    }

    private class AuctionAdapter extends RecyclerView.Adapter<AuctionHolder> {

        private List<AuctionItem> mAuctionItems;

        public AuctionAdapter(List<AuctionItem> auctions) {
            mAuctionItems = auctions;
        }

        @Override
        public AuctionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new AuctionHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AuctionHolder holder, int position) {
            AuctionItem auction = mAuctionItems.get(position);
            holder.bind(auction);
        }

        @Override
        public int getItemCount() {
            return mAuctionItems.size();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auction_list, container, false);

        mAuctionRecyclerView = view.findViewById(R.id.auction_recycler_view);
        mAuctionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    //Reloads List
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        AuctionArchive auctionArchive = AuctionArchive.get(getActivity());
        List<AuctionItem> auctions = auctionArchive.getCrimes();

        if (mAdapter == null) {
            mAdapter = new AuctionAdapter(auctions);
            mAuctionRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

}