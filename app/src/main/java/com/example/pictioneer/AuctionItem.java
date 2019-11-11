package com.example.pictioneer;


import java.util.Date;
import java.util.UUID;

public class AuctionItem {

    private UUID mAuctionId;
    private String mTitle;
    private Date mDate;
    private boolean mSold;

    public AuctionItem() {
        mAuctionId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getAuctionId() { return mAuctionId; }

    public void setAuctionId(UUID auctionId) { mAuctionId = auctionId; }

    public String getTitle() { return mTitle; }

    public void setTitle(String title) { mTitle = title; }

    public Date getDate() { return mDate; }

    public void setDate(Date date) { mDate = date; }

    public boolean isSold() { return mSold; }

    public void setSold(boolean sold) { this.mSold = sold; }
}
