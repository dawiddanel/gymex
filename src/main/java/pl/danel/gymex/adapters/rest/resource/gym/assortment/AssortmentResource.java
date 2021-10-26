package pl.danel.gymex.adapters.rest.resource.gym.assortment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.danel.gymex.application.gym.assortment.AssortmentService;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDefinitionDto;
import pl.danel.gymex.domain.gym.GymRepository;

@RestController
@RequestMapping(value = "assortment", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AssortmentResource {

    private final AssortmentService assortmentService;


}
