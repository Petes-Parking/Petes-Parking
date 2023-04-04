package com.petesparkingmgt;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.petesparkingmgt.ctl.AdminCtl;
import com.petesparkingmgt.dao.reports.ExpReportDAO;
import com.petesparkingmgt.dao.reports.PoorParkReportDAO;
import com.petesparkingmgt.dto.reports.ExpReportDTO;
import com.petesparkingmgt.dto.reports.PoorParkReportDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminCtl adminController;

    @Mock
    private PoorParkReportDAO poorParkDAO;

    @Mock
    private ExpReportDAO expDAO;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testAdminReviewPoorPark() {
        List<PoorParkReportDTO> poorParks = new ArrayList<>();
        when(poorParkDAO.findAll()).thenReturn(poorParks);

        String result = adminController.adminReviewPoorPark(model);

        assertEquals("adminPoorParkReport", result);
        verify(model).addAttribute("adminPoorParkList", poorParks);
    }

    @Test
    public void testAdminReviewExpDetailed() {
        Long expReportID = 1L;
        ExpReportDTO expReport = new ExpReportDTO();
        expReport.setImageData(new byte[0]);
        when(expDAO.getById(expReportID)).thenReturn(expReport);

        String result = adminController.adminReviewExpDetailed(expReportID, model);

        assertEquals("adminReviewExpDetailed", result);
        verify(model).addAttribute("report", expReport);

        String base64Image = Base64.getEncoder().encodeToString(expReport.getImageData());
        verify(model).addAttribute("reportImage", base64Image);
    }
}
