package pl.danel.gymex.domain.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.danel.gymex.domain.person.member.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
