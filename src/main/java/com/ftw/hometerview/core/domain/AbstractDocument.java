package com.ftw.hometerview.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@ToString
public abstract class AbstractDocument {

    @JsonIgnore
    @CreatedDate
    protected LocalDateTime createdAt;
    @JsonIgnore
    @LastModifiedDate
    protected LocalDateTime updatedAt;
    @JsonIgnore
    protected LocalDateTime deletedAt;

}
