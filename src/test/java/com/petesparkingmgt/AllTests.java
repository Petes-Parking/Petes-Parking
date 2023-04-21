package com.petesparkingmgt;


import com.petesparkingmgt.UserRepositoryLayerTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.platform.commons.annotation.Testable;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("All Tests")

@SelectClasses({
        FutureBookingCtlTest.class,
        ReferralsCtlTest.class,
        MainCtlTest.class,
        SettingsCtlTest.class,
        ProfileCtlTest.class,
        BookingControllerTest.class,
        ParkingPalsCtlTest.class,
        ManagePointsControllerTest.class,
        UserControllerTest.class,
     //   AdminControllerTest.class,
        UserRepositoryLayerTests.class,
        //CarpoolCtlTest.class,
})
public class AllTests {
}