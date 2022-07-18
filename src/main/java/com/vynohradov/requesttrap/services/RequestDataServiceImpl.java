package com.vynohradov.requesttrap.services;

import com.vynohradov.requesttrap.dto.RequestDataDto;
import com.vynohradov.requesttrap.entities.RequestData;
import com.vynohradov.requesttrap.repositories.RequestDataRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class RequestDataServiceImpl implements RequestDataService {

    private RequestDataRepository requestDataRepository;
    private ModelMapper modelMapper;

    @Override
    public RequestDataDto saveGetRequest(String trapId, Map<String, String> params, HttpHeaders headers) {
        RequestData requestDataEntity = RequestData.builder()
                .trapId(trapId)
                .params(requestParamsToString(params))
                .headers(requestParamsToString(headers.toSingleValueMap()))
                .build();
        RequestData data = requestDataRepository.save(requestDataEntity);

        return modelMapper.map(data, RequestDataDto.class);
    }

    @Override
    public RequestDataDto saveRequestWithBody(String trapId, Map<String, String> params, HttpHeaders headers, String body) {
        RequestData requestDataEntity = RequestData.builder()
                .trapId(trapId)
                .params(requestParamsToString(params))
                .headers(requestParamsToString(headers.toSingleValueMap()))
                .body(body)
                .build();
        RequestData data = requestDataRepository.save(requestDataEntity);

        return modelMapper.map(data, RequestDataDto.class);
    }

    public List<RequestDataDto> getRequestsListByTrapId(String trapId) {
        var requests = requestDataRepository.findByTrapId(trapId);
        return modelMapper.map(requests, new TypeToken<List<RequestDataDto>>() {}.getType());
    }

    private String requestParamsToString(Map<String, String> params) {
        String data = params
                .entrySet()
                .stream()
                .map(e -> "\"%s\": \"%s\"".formatted(e.getKey(), e.getValue()))
                .collect(Collectors.joining(", "));
        return "{" + data + "}";
    }
}
