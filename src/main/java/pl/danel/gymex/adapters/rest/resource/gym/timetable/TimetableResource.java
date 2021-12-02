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

    @PostMapping("/{gymId}/activity")
    public TimetableDto createActivity(@PathVariable Long gymId, @RequestBody CreateActivityCommand command) {
        return timetableService.createActivity(gymId, command);
    }

    @PutMapping("/{gymId}/activity/{activityId}")
    public TimetableDto updateActivity(@PathVariable Long gymId, @PathVariable Long activityId, @RequestBody UpdateActivityCommand command) {
        return timetableService.updateActivity(gymId, activityId, command);
    }

    @DeleteMapping("/{gymId}/activity/{activityId}")
    public TimetableDto deleteActivity(@PathVariable Long gymId, @PathVariable Long activityId) {
        return timetableService.deleteActivity(gymId, activityId);
    }

    @PostMapping("/{gymId}/activity/{activityId}/join")
    public ActivityDto joinActivity(@PathVariable Long gymId, @PathVariable Long activityId) {
        return timetableService.joinActivity(gymId, activityId);
    }

    @PostMapping("/{gymId}/activity/{activityId}/resign")
    public ActivityDto resignFromActivity(@PathVariable Long gymId, @PathVariable Long activityId) {
        return timetableService.resignFromActivity(gymId, activityId);
    }

    @PostMapping("/{gymId}/activity/{activityId}/confirm/{userId}")
    public ActivityDto confirmAttendance(@PathVariable Long gymId, @PathVariable Long activityId, @PathVariable Long userId) {
        return timetableService.confirmAttendance(gymId, activityId, userId);
    }

    @PostMapping("/{gymId}/activity/{activityId}/unconfirm/{userId}")
    public ActivityDto resignAttendance(@PathVariable Long gymId, @PathVariable Long activityId, @PathVariable Long userId) {
        return timetableService.resignAttendance(gymId, activityId, userId);
    }

    @GetMapping("/{gymId}")
    public TimetableDto getTimetable(@PathVariable Long gymId) {
        return timetableService.getTimetable(gymId);
    }

    @GetMapping("/{gymId}/activity/{activityId}")
    public ActivityDto getActivity(@PathVariable Long gymId, @PathVariable Long activityId) {
        return timetableService.getActivity(gymId, activityId);
    }

}
