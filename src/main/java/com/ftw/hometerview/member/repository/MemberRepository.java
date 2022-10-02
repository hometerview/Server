package com.ftw.hometerview.member.repository;

import com.ftw.hometerview.member.domain.Member;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, ObjectId> {

    Optional<Member> findByMemberId(String memberId);
}
