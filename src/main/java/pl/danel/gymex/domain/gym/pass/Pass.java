package pl.danel.gymex.domain.gym.pass;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Pass {
    private LocalDate startDate;
    private LocalDate activeStartDate;
    private LocalDate endDate;
}
