package curs.hotel.controller.controller.admin;

import curs.hotel.service.view.admin.BillsTableService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class AdminBillsTableController {
    private BillsTableService service;

    @Autowired
    public AdminBillsTableController(BillsTableService service) {
        this.service = service;
    }

    @GetMapping("/admin/bills")
    public String getBills(Model model,
                           @PageableDefault( sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getBills(pageable, model);
    }
}
