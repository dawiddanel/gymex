package pl.danel.gymex.domain.gym.timetable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.danel.gymex.domain.common.ActivityStatus;
import pl.danel.gymex.domain.person.member.Member;
import pl.danel.gymex.domain.person.trainer.Trainer;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByStatus(ActivityStatus status);

    List<Activity> findAllByTrainer(Trainer trainer);

    List<Activity> findAllByParticipantsContains(Member member);

}
