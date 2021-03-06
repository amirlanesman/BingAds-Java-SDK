package com.microsoft.bingads.api.test.entities.targets.adgroup;

import com.microsoft.bingads.api.test.entities.Util;
import com.microsoft.bingads.bulk.entities.BulkAdGroupAgeTargetBid;
import com.microsoft.bingads.bulk.entities.BulkAdGroupTarget;
import com.microsoft.bingads.bulk.entities.BulkEntity;
import com.microsoft.bingads.campaignmanagement.AgeRange;
import com.microsoft.bingads.campaignmanagement.AgeTargetBid;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BulkAdGroupTargetReadWriteTest {

    @Test
    public void readBulkAdGroupTargetWithDifferentAdGroupIds() {
        ArrayList<BulkEntity> targetBids = new ArrayList<BulkEntity>();

        BulkAdGroupAgeTargetBid bulkBid = new BulkAdGroupAgeTargetBid();
        AgeTargetBid bid = new AgeTargetBid();
        bulkBid.setAgeTargetBid(bid);
        bulkBid.setAdGroupId(1001L);
        bid.setAge(AgeRange.EIGHTEEN_TO_TWENTY_FIVE);
        bid.setBidAdjustment(10);
        targetBids.add(bulkBid);

        bulkBid = new BulkAdGroupAgeTargetBid();
        bid = new AgeTargetBid();
        bulkBid.setAgeTargetBid(bid);
        bulkBid.setAdGroupId(1002L);
        bid.setAge(AgeRange.FIFTY_TO_SIXTY_FIVE);
        bid.setBidAdjustment(-10);
        targetBids.add(bulkBid);

        ArrayList<BulkEntity> readBack = Util.WriteAndReadBack(targetBids);
        Assert.assertEquals(2, readBack.size());

        BulkAdGroupTarget a = (BulkAdGroupTarget) readBack.get(0);
        Assert.assertEquals(1001L, a.getAdGroupId().longValue());
        Assert.assertEquals(
                Util.toJson(((BulkAdGroupAgeTargetBid) targetBids.get(0)).getAgeTargetBid()),
                Util.toJson(a.getAgeTarget().getBids().get(0).getAgeTargetBid())
        );

        BulkAdGroupTarget b = (BulkAdGroupTarget) readBack.get(1);
        Assert.assertEquals(1002L, b.getAdGroupId().longValue());
        Assert.assertEquals(
                Util.toJson(((BulkAdGroupAgeTargetBid) targetBids.get(1)).getAgeTargetBid()),
                Util.toJson(b.getAgeTarget().getBids().get(0).getAgeTargetBid())
        );
    }

}