package com.ftw.hometerview.review.domain;

import com.ftw.hometerview.core.domain.AbstractDocument;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(collection = "review")
public class Review extends AbstractDocument {

    @Id
    ObjectId id;

    String memberId;
    String buildingId;
    String companyId;
    Integer rating;

    String advantage;
    String disadvantage;

    String period;
    Price price;
    List<String> certification;
    Integer like_count;
    Integer bookmark_count;

    @Getter
    public static class Price {

        String monthly;     // 월세
        String deposit;     //전세
        String maintainFee; // 관리비
    }

    public Review setCertification(List<String> cert) {
        this.certification = cert;
        return this;
    }

}
