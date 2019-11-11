package com.example.pictioneer;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuctionArchive {
    private static AuctionArchive sAuctionArchive;

    private List<AuctionItem> mAuctionItems;

    public static AuctionArchive get(Context context) {
        if (sAuctionArchive == null) {
            sAuctionArchive = new AuctionArchive(context);
        }
        return sAuctionArchive;
    }

    private AuctionArchive(Context context) {
        mAuctionItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            AuctionItem auction = new AuctionItem();
            auction.setTitle("Crime #" + i);
            auction.setSold(i % 2 == 0);
            mAuctionItems.add(auction);
        }
    }

    public List<AuctionItem> getCrimes() {
        return mAuctionItems;
    }

    public AuctionItem getAuction(UUID id) {
        for (AuctionItem auction : mAuctionItems) {
            if (auction.getAuctionId().equals(id)) {
                return auction;
            }
        }

        return null;
    }
}
}
