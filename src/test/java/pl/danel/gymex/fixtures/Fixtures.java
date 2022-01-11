package pl.danel.gymex.fixtures;

import pl.danel.gymex.adapters.rest.resource.gym.command.CreateAddressCommand;
import pl.danel.gymex.adapters.rest.resource.gym.command.CreateGymCommand;
import pl.danel.gymex.adapters.rest.resource.person.command.CreatePassCommand;
import pl.danel.gymex.domain.common.BodyPart;
import pl.danel.gymex.domain.common.EquipmentType;
import pl.danel.gymex.domain.common.Level;
import pl.danel.gymex.domain.common.Purpose;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipment;
import pl.danel.gymex.domain.gym.assortment.command.CreateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.command.UpdateEquipmentDefinition;
import pl.danel.gymex.domain.gym.assortment.definitions.EquipmentDefinition;
import pl.danel.gymex.domain.gym.command.*;
import pl.danel.gymex.domain.gym.timetable.activities.definitions.ActivityDefinition;
import pl.danel.gymex.domain.gym.timetable.command.*;
import pl.danel.gymex.domain.person.Person;
import pl.danel.gymex.domain.person.user.Role;
import pl.danel.gymex.domain.person.user.User;
import pl.danel.gymex.domain.person.user.command.CreatePerson;
import pl.danel.gymex.domain.person.user.command.CreateTechnicalUser;
import pl.danel.gymex.domain.person.user.command.CreateUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Fixtures {

    public static CreatePass.CreatePassBuilder createPass() {
        return CreatePass.builder()
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(15));
    }

    public static CreatePassCommand createPassCommand() {
        CreatePassCommand command = new CreatePassCommand();
        command.setStartDate(LocalDate.now());
        command.setEndDate(LocalDate.now().plusDays(15));
        return command;
    }

    public static CreateAddress.CreateAddressBuilder createAddress() {
        return CreateAddress.builder()
                .country("Polska")
                .city("Cieszyn")
                .postalCode("44-523")
                .street("Ulica")
                .buildingNumber("13");
    }

    public static CreatePerson.CreatePersonBuilder createPerson() {
        return CreatePerson.builder()
                .firstName("Dawid")
                .lastName("Test")
                .birthDate(LocalDate.of(1998, 7, 31))
                .pesel("98073108730");
    }

    public static CreateUser.CreateUserBuilder createUser() {
        return CreateUser.builder()
                .username("Username")
                .password("Password")
                .email("email@wp.pl")
                .person(createPerson().build());
    }

    public static CreateTechnicalUser.CreateTechnicalUserBuilder createTechnicalUser() {
        return CreateTechnicalUser.builder()
                .username("Username")
                .password("Password")
                .email("email@wp.pl")
                .person(createPerson().build())
                .role(Role.MEMBER);
    }

    public static CreateEquipmentDefinition.CreateEquipmentDefinitionBuilder createEquipmentDefinition() {
        return CreateEquipmentDefinition.builder()
                .name("Name")
                .description("Description")
                .type(EquipmentType.WEIGHT)
                .purpose(Purpose.CARDIO)
                .weight(12.5)
                .aimedBodyParts(List.of(BodyPart.BACK));
    }

    public static UpdateEquipmentDefinition.UpdateEquipmentDefinitionBuilder createUpdateEquipmentDefinition() {
        return UpdateEquipmentDefinition.builder()
                .name("Name")
                .description("Description")
                .type(EquipmentType.WEIGHT)
                .purpose(Purpose.CARDIO)
                .weight(12.5)
                .aimedBodyParts(List.of(BodyPart.BACK));
    }

    public static CreateActivityDefinition.CreateActivityDefinitionBuilder createActivityDefinition() {
        return CreateActivityDefinition.builder()
                .name("Name")
                .description("Description")
                .level(Level.ADVANCED);
    }

    public static UpdateActivityDefinition.UpdateActivityDefinitionBuilder createUpdateActivityDefinition() {
        return UpdateActivityDefinition.builder()
                .name("Name")
                .description("Description")
                .level(Level.ADVANCED);
    }

    public static CreateEquipment.CreateEquipmentBuilder createEquipment() {
        return CreateEquipment.builder()
                .quantity(1)
                .definition(EquipmentDefinition.create(createEquipmentDefinition().build()));
    }

    public static CreateActivity.CreateActivityBuilder createActivity() {
        return CreateActivity.builder()
                .activityDefinition(ActivityDefinition.create(createActivityDefinition().build()))
                .trainer(Person.createTrainer(User.createTechnical(createTechnicalUser().role(Role.TRAINER).build()), createPerson().build()))
                .startTime(LocalDateTime.now().plusHours(2))
                .endTime(LocalDateTime.now().plusHours(2))
                .startTime(LocalDateTime.now().plusHours(3))
                .capacity(15);
    }

    public static UpdateActivity.UpdateActivityBuilder updateActivity() {
        return UpdateActivity.builder()
                .activityDefinition(ActivityDefinition.create(createActivityDefinition().build()))
                .trainer(Person.createTrainer(User.createTechnical(createTechnicalUser().role(Role.TRAINER).build()), createPerson().build()))
                .startTime(LocalDateTime.now().plusHours(2))
                .endTime(LocalDateTime.now().plusHours(2))
                .startTime(LocalDateTime.now().plusHours(3))
                .capacity(15);
    }

    public static AddParticipant.AddParticipantBuilder addParticipant() {
        return AddParticipant.builder()
                .member(Person.createMember(User.createTechnical(createTechnicalUser().role(Role.MEMBER).build()), createPerson().build()));
    }

    public static RemoveParticipant.RemoveParticipantBuilder removeParticipant() {
        return RemoveParticipant.builder()
                .member(Person.createMember(User.createTechnical(createTechnicalUser().role(Role.MEMBER).build()), createPerson().build()));
    }

    public static ConfirmAttendance.ConfirmAttendanceBuilder confirmAttendance() {
        return ConfirmAttendance.builder()
                .member(Person.createMember(User.createTechnical(createTechnicalUser().role(Role.MEMBER).build()), createPerson().build()));
    }

    public static ResignAttendance.ResignAttendanceBuilder resignAttendance() {
        return ResignAttendance.builder()
                .member(Person.createMember(User.createTechnical(createTechnicalUser().role(Role.MEMBER).build()), createPerson().build()));
    }

    public static CreateGym.CreateGymBuilder createGym() {
        return CreateGym.builder()
                .name("Name")
                .capacity(15)
                .address(createAddress().build())
                .createTimetable(CreateTimetable.builder()
                        .startDate(LocalDate.now().minusDays(1))
                        .endDate(LocalDate.now().plusDays(15))
                        .build());
    }

    public static UpdateGym.UpdateGymBuilder updateGym() {
        return UpdateGym.builder()
                .name("Name")
                .capacity(15)
                .address(createAddress().build());
    }

    public static CreateGymCommand createGymCommand() {
        CreateGymCommand command = new CreateGymCommand();
        command.setName("Test");
        command.setCapacity(250);
        command.setTimetableStartDate(LocalDate.now());
        command.setTimetableEndDate(LocalDate.now().plusDays(15));
        command.setAddress(createAddressCommand());
        return command;
    }

    public static CreateAddressCommand createAddressCommand() {
        CreateAddressCommand createAddressCommand = new CreateAddressCommand();
        createAddressCommand.setCountry("PL");
        createAddressCommand.setCity("Miasto");
        createAddressCommand.setStreet("Ulica");
        createAddressCommand.setPostalCode("43-523");
        createAddressCommand.setBuildingNumber("12");
        return createAddressCommand;
    }
}
