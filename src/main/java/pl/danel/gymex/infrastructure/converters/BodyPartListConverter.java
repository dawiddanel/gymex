package pl.danel.gymex.infrastructure.converters;

import pl.danel.gymex.domain.common.BodyPart;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class BodyPartListConverter implements AttributeConverter<List<BodyPart>, String> {

    @Override
    public String convertToDatabaseColumn(List<BodyPart> bodyParts) {
        return bodyParts.stream().map(BodyPart::name).collect(Collectors.joining("-"));
    }

    @Override
    public List<BodyPart> convertToEntityAttribute(String dbData) {
        return Arrays.stream(dbData.split("-"))
                .map(BodyPart::valueOf)
                .collect(Collectors.toList());
    }

}
