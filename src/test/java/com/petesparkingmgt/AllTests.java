package com.petesparkingmgt;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("All Tests")
@SelectClasses({

        UserRepositoryLayerTests.class,
        CarpoolCtlTest.class,
        LeaderboardControllerUnitTest.class,
        ManagePointsControllerTest.class,
        ParkingPalsCtlTest.class,
        ProfileCtlTest.class,
        UserControllerTest.class,
        AdminControllerTest.class,
        HistoryCtlTest.class,
        MainCtlTest.class,
        CarpoolUsersCtlTest.class,

})
public class AllTests {
}