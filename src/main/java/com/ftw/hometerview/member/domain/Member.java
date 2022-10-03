package com.ftw.hometerview.member.domain;

import com.ftw.hometerview.auth.controller.dto.ProviderType;
import com.ftw.hometerview.core.domain.AbstractDocument;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Builder
@Getter
@Document(collection = "member")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Member extends AbstractDocument {

    @MongoId
    String id;

    String memberId;

    String nickname;

    ProviderType providerType;

    Certification certification;

    String refreshToken;

    private class Certification {
        String locationCode;    // 지역 코드
        Boolean isPostedReview;   // 리뷰를 작성했는지에 대한 여부
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
