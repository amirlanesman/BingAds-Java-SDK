package com.microsoft.bingads.api.test.entities.ads.mobile.write;

import com.microsoft.bingads.api.test.entities.ads.mobile.BulkMobileAdTest;
import com.microsoft.bingads.bulk.entities.BulkMobileAd;
import com.microsoft.bingads.internal.functionalinterfaces.BiConsumer;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BulkMobileAdWriteToRowValuesDevicePreferenceTest extends BulkMobileAdTest {

    @Parameter(value = 1)
    public Long propertyValue;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {"All", 0L},
            {"Mobile", 30001L},
            {"All", null},});
    }

    @Test
    public void testWrite() {
        this.<Long>testWriteProperty("Device Preference", this.datum, this.propertyValue, new BiConsumer<BulkMobileAd, Long>() {
            @Override
            public void accept(BulkMobileAd c, Long v) {
                c.getMobileAd().setDevicePreference(v);
            }
        });
    }
}
