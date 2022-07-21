package com.vynohradov.requesttrap.controllers;

import com.vynohradov.requesttrap.configuration.ApplicationProperties;
import com.vynohradov.requesttrap.services.RequestDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor()
@Slf4j
@Controller
public class TrapsUiController {

    RequestDataService requestDataService;
    ApplicationProperties applicationProperties;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", applicationProperties.getName());
        model.addAttribute("description", applicationProperties.getDescription());
        model.addAttribute("version", applicationProperties.getVersion());
        return "index";
    }

    @GetMapping("/{trapId}/requests")
    public String listRequests(Model model, @PathVariable String trapId) {
        var requests = requestDataService.getRequestsListByTrapId(trapId);
        model.addAttribute("trapId", trapId);
        model.addAttribute("requests", requests);
        return "trap-requests";
    }
}
