package com.vynohradov.requesttrap.services;

import com.vynohradov.requesttrap.dto.RequestDataDto;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Map;

public interface RequestDataService {

    RequestDataDto saveGetRequest(String trapId, Map<String, String> params, HttpHeaders headers);

    RequestDataDto saveRequestWithBody(String trapId, Map<String, String> params, HttpHeaders headers, String body);

    List<RequestDataDto> getRequestsListByTrapId(String trapId);

}
