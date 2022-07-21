package com.vynohradov.requesttrap.controllers;

import com.vynohradov.requesttrap.dto.RequestDataDto;
import com.vynohradov.requesttrap.services.RequestDataService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(TrapsApiController.class)
public class TrapsApiControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public RequestDataService requestDataService;

    @MockBean
    public SimpMessagingTemplate simpMessagingTemplate;

    @Test
    public void successCheckListRequests() throws Exception {
        // given
        var trapId = "test";
        var data = new RequestDataDto();
        data.setTrapId(trapId);
        var mockedRequestsList = List.of(data);

        // when
        when(requestDataService.getRequestsListByTrapId(trapId)).thenReturn(mockedRequestsList);
        doNothing().when(simpMessagingTemplate).convertAndSend(TrapsApiController.WS_DESTINATION + trapId, mockedRequestsList);

        // then
        this.mockMvc.perform(get("/api/traps/%s/requests".formatted(trapId)))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
