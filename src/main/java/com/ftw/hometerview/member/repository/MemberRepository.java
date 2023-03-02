package com.ftw.hometerview.member.repository;

import com.ftw.hometerview.member.domain.Member;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {

    Optional<Member> findByOauthId(String oauthId);
}
