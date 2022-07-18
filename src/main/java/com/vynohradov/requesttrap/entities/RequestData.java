package com.vynohradov.requesttrap.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "REQUESTS",
        indexes = {
                @Index(name = "trap_id_idx", columnList = "trapId")
        }
)
public class RequestData extends BaseEntity {
    @NotNull
    @Size(min = 1, max = 200)
    @Column(length = 200, nullable = false)
    private String trapId;

    @Column(length = 600)
    private String params;

    @Column(length = 2000)
    private String headers;

    @Column(length = 2000)
    private String body;

    public String getTrapId() {
        return trapId;
    }

    public void setTrapId(String trapId) {
        this.trapId = trapId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RequestData{");
        sb.append("trapId='").append(trapId).append('\'');
        sb.append(", params='").append(params).append('\'');
        sb.append(", headers='").append(headers).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
