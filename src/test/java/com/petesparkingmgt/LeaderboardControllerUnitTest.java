package com.petesparkingmgt;

import com.petesparkingmgt.ctl.LeaderboardCtl;
import com.petesparkingmgt.dao.UserDAO;
import com.petesparkingmgt.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LeaderboardControllerUnitTest {

    @InjectMocks
    private LeaderboardCtl leaderboardController;

    @Mock
    private UserDAO dao;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    private UserDTO user;
    private List<UserDTO> users;

    @BeforeEach
    void setUp() {
        user = new UserDTO();
        user.setId(1);
        user.setPoints(10);

        UserDTO user2 = new UserDTO();
        user2.setId(2);
        user2.setPoints(5);

        users = new ArrayList<>();
        users.add(user);
        users.add(user2);
    }

    @Test
    void leaderboard_withValidUser() {
        when(session.getAttribute("user")).thenReturn(user);
        when(dao.getAllByUserRole("Student")).thenReturn(users);

        String result = leaderboardController.leaderboard(model, session);

        verify(session).getAttribute("user");
        verify(dao).getAllByUserRole("Student");
        verify(model).addAttribute("leaderboardList", users);

        assert result.equals("leaderboard");
    }

    @Test
    void leaderboard_withNullUser() {
        when(session.getAttribute("user")).thenReturn(null);

        String result = leaderboardController.leaderboard(model, session);

        verify(session).getAttribute("user");
        verifyNoMoreInteractions(dao, model);

        assert result.equals("error");
    }
}
