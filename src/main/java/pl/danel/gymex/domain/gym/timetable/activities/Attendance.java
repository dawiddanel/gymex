package pl.danel.gymex.domain.gym.timetable.activities;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.danel.gymex.domain.person.member.Member;

import javax.persistence.*;

@Entity
@Table(name = "ATTENDANCE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_sequence")
    @SequenceGenerator(name = "attendance_sequence", sequenceName = "SEQ_ATTENDANCE", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Activity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private boolean attended;
}
