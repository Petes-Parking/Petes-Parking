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
        AdminControllerTest.class,
        CarpoolCtlTest.class,
        CarpoolUsersCtlTest.class,
        HistoryCtlTest.class,
        MainCtlTest.class,
        ParkingPalsCtlTest.class,
        ProfileCtlTest.class,
        UserRepositoryLayerTests.class,
        UserControllerTest.class,
        LeaderboardControllerUnitTest.class,
        ManagePointsControllerTest.class

})
public class AllTests {
}