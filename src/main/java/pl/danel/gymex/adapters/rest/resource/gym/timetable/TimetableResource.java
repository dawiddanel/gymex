package pl.danel.gymex.adapters.rest.resource.gym.timetable;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.CreateActivityCommand;
import pl.danel.gymex.adapters.rest.resource.gym.timetable.command.UpdateActivityCommand;
import pl.danel.gymex.application.gym.assortment.dto.EquipmentDto;
import pl.danel.gymex.application.gym.timetable.TimetableService;
import pl.danel.gymex.application.gym.timetable.dto.ActivityDto;
import pl.danel.gymex.application.gym.timetable.dto.TimetableDto;

@RestController
@RequestMapping(value = "timetable", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TimetableResource {

    private final TimetableService timetableService;

    @PostMapping("/{gymId}/{timetableId}/activity")
    public TimetableDto createActivity(@PathVariable Long gymId, @PathVariable Long timetableId, @RequestBody CreateActivityCommand command) {
        return timetableService.createActivity(gymId, timetableId, command);
    }

    @PutMapping("/{gymId}/{timetableId}/activity/{activityId}")
    public TimetableDto updateActivity(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId, @RequestBody UpdateActivityCommand command) {
        return timetableService.updateActivity(gymId, timetableId, activityId, command);
    }

    @PutMapping("/{gymId}/{timetableId}/activity/{activityId}/cancel")
    public TimetableDto cancelActivity(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId) {
        return timetableService.cancelActivity(gymId, timetableId, activityId);
    }

    @PostMapping("/{gymId}/{timetableId}/activity/{activityId}/join")
    public ActivityDto joinActivity(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId) {
        return timetableService.joinActivity(gymId, timetableId, activityId);
    }

    @PostMapping("/{gymId}/{timetableId}/activity/{activityId}/resign")
    public ActivityDto resignFromActivity(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId) {
        return timetableService.resignFromActivity(gymId, timetableId, activityId);
    }

    @PostMapping("/{gymId}/{timetableId}/activity/{activityId}/confirm/{userId}")
    public ActivityDto confirmAttendance(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId, @PathVariable Long userId) {
        return timetableService.confirmAttendance(gymId, timetableId, activityId, userId);
    }

    @PostMapping("/{gymId}/{timetableId}/activity/{activityId}/unconfirm/{userId}")
    public ActivityDto resignAttendance(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId, @PathVariable Long userId) {
        return timetableService.resignAttendance(gymId, timetableId, activityId, userId);
    }

    @GetMapping("/{gymId}")
    public TimetableDto getActualTimetable(@PathVariable Long gymId) {
        return timetableService.getActualTimetable(gymId);
    }

    @GetMapping("/{gymId}/{timetableId}")
    public TimetableDto getActualTimetable(@PathVariable Long gymId, @PathVariable Long timetableId) {
        return timetableService.getTimetable(gymId, timetableId);
    }

    @GetMapping("/{gymId}/{timetableId}/activity/{activityId}")
    public ActivityDto getActivity(@PathVariable Long gymId, @PathVariable Long timetableId, @PathVariable Long activityId) {
        return timetableService.getActivity(gymId, timetableId, activityId);
    }

}
