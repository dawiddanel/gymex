package pl.danel.gymex.domain.gym.pass;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Getter
public class Pass {
    private LocalDate startDate;
    private LocalDate activeStartDate;
    private LocalDate endDate;
}
