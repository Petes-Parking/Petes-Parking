package com.petesparkingmgt;

import com.petesparkingmgt.UnitControllerLayerTest;
import com.petesparkingmgt.UserRepositoryLayerTests;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("All Tests")
@SelectClasses({

        UserRepositoryLayerTests.class,
        UnitControllerLayerTest.class,
        LeaderboardControllerUnitTest.class,
        ManagePointsControllerTest.class,
        UserControllerTest.class,
        AdminControllerTest.class,
        HistoryCtlTest.class,

})
public class AllTests {
}