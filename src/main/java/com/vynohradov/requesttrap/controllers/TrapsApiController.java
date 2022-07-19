package com.vynohradov.requesttrap.controllers;

import com.vynohradov.requesttrap.dto.RequestDataDto;
import com.vynohradov.requesttrap.services.RequestDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

@AllArgsConstructor()
@Slf4j
@RestController
@RequestMapping("/api/traps")
public class TrapsApiController {

    private static final String WS_DESTINATION = "/topic/";

    private RequestDataService requestDataService;
    private SimpMessagingTemplate simpMessagingTemplate;

    @GetMapping("/{trapId}/requests")
    @ResponseStatus(code = HttpStatus.OK)
    public List<RequestDataDto> listRequests(
            @PathVariable(required = true) String trapId
    ) {
        return requestDataService.getRequestsListByTrapId(trapId);
    }

    @GetMapping("/{trapId}/**")
    @ResponseStatus(code = HttpStatus.OK)
    public RequestDataDto getTrapEndpoint(
            @PathVariable(required = true) String trapId,
            @RequestParam Map<String, String> params,
            HttpEntity<String> httpEntity
    ) {
        var data = requestDataService.saveGetRequest(trapId, params, httpEntity.getHeaders());
        simpMessagingTemplate.convertAndSend(getWsDestinationPath(trapId), data);
        return data;
    }

    @RequestMapping(path = "/{trapId}/**", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @ResponseStatus(code = HttpStatus.OK)
    public RequestDataDto bodyTrapEndpoint(
            @PathVariable @NotBlank String trapId,
            @RequestParam Map<String, String> params,
            HttpEntity<String> httpEntity
    ) {
        var data = requestDataService.saveRequestWithBody(trapId, params, httpEntity.getHeaders(), httpEntity.getBody());
        simpMessagingTemplate.convertAndSend(getWsDestinationPath(trapId), data);
        return data;
    }

    private String getWsDestinationPath(String trapId) {
        return WS_DESTINATION + trapId;
    }

}
