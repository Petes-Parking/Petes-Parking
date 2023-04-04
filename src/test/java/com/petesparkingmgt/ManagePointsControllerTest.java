package com.petesparkingmgt;

import com.petesparkingmgt.ctl.AdminCtl;
import com.petesparkingmgt.dao.users.UserDAO;
import com.petesparkingmgt.dto.user.UserDTO;
import com.petesparkingmgt.form.ManagePointForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ManagePointsControllerTest {

    @InjectMocks
    private AdminCtl managePointsController;

    @Mock
    private UserDAO dao;

    @Mock
    private Model model;

    private UserDTO user;
    private List<UserDTO> adminUserList;

    @BeforeEach
    void setUp() {
        user = new UserDTO();
        user.setId(1);
        user.setPoints(10);

        UserDTO admin = new UserDTO();
        admin.setId(2);
        admin.setPoints(20);

        adminUserList = new ArrayList<>();
        adminUserList.add(user);
        adminUserList.add(admin);
    }

    @Test
    void updateByOne_addPoints() {
        ManagePointForm form = new ManagePointForm();
        form.setUserId(1);
        form.setType("add");
        form.setAmount(5);

        when(dao.getById((long) 1)).thenReturn(user);
        when(dao.findAll()).thenReturn(adminUserList);

        String result = managePointsController.updateByOne(form, model);

        verify(dao).getById((long) 1);
        verify(dao).save(user);
        verify(dao, times(1)).findAll();
        verify(model, times(1)).addAttribute("adminUserList", adminUserList);

        assert result.equals("managePoints");
        assert user.getPoints() == 15;
    }





    @Test
    void updateByOne_subtractPoints() {
        ManagePointForm form = new ManagePointForm();
        form.setUserId(1);
        form.setType("subtract");
        form.setAmount(5);

        when(dao.getById((long) 1)).thenReturn(user);
        when(dao.findAll()).thenReturn(adminUserList);

        String result = managePointsController.updateByOne(form, model);

        verify(dao).getById((long) 1);
        verify(dao).save(user);
        verify(dao, times(1)).findAll();
        verify(model, times(1)).addAttribute("adminUserList", adminUserList);

        assert result.equals("managePoints");
        assert user.getPoints() == 5;
    }

    @Test
    void updateByOne_negativeAmount() {
        ManagePointForm form = new ManagePointForm();
        form.setUserId(1);
        form.setType("add");
        form.setAmount(-5);

        when(dao.getById((long) 1)).thenReturn(user);
        when(dao.findAll()).thenReturn(adminUserList);

        String result = managePointsController.updateByOne(form, model);

        verify(dao).getById((long) 1);
        verify(dao, times(1)).findAll();
        verify(model).addAttribute("adminUserList", adminUserList);
        verify(model).addAttribute("errors", "Do not use negative values!");
        verifyNoMoreInteractions(dao);

        assert result.equals("managePoints");
    }

    @Test
    void updateByOne_insufficientPoints() {
        ManagePointForm form = new ManagePointForm();
        form.setUserId(1);
        form.setType("subtract");
        form.setAmount(15);

        when(dao.getById((long) 1)).thenReturn(user);
        when(dao.findAll()).thenReturn(adminUserList);
        String result = managePointsController.updateByOne(form, model);

        verify(dao).getById((long) 1);
        verify(dao, times(1)).findAll();
        verify(model).addAttribute("adminUserList", adminUserList);
        verify(model).addAttribute("errors", "User's points cannot be negative!");
        verifyNoMoreInteractions(dao);

        assert result.equals("managePoints");
    }
}