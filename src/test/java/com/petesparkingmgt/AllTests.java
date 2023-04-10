package com.petesparkingmgt;

import com.petesparkingmgt.UnitControllerLayerTest;

import com.petesparkingmgt.UserRepositoryLayerTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.platform.commons.annotation.Testable;

//@RunWith(JUnitPlatform.class)
@DisplayName("All Tests")
@SuiteClasses({
        UserRepositoryLayerTests.class,
        UnitControllerLayerTest.class,
        LeaderboardControllerUnitTest.class,
        ManagePointsControllerTest.class

})
public class AllTests {
}