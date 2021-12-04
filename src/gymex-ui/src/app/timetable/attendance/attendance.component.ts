import {Component, Input, OnInit} from '@angular/core';
import {Activity, Attendance} from "../../default/models/activity.model";
import {ToastsService} from "../../default/toasts.service";
import {TimetableService} from "../timetable.service";

@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent implements OnInit {

  @Input() attendance: Attendance
  @Input() activity: Activity
  @Input() gymId: number
  @Input() timetableId: number

  constructor(private timetableService: TimetableService,
              private toasts: ToastsService) { }

  ngOnInit(): void {
  }

  confirmAttendance(): void {
    this.timetableService.confirmAttendance(this.gymId, this.timetableId, this.activity.id, this.attendance.member.id)
      .subscribe({
          next: value => {
            this.activity = value
            this.attendance.attended = true;
            this.toasts.showSuccessToast("Potwierdzono obecność!")
          },
          error: err => {
            this.toasts.showErrorToast(`Nie udało się wykonać operacji`)
          }
        }
      )
  }

  resignAttendance(): void {
    this.timetableService.resignAttendance(this.gymId, this.timetableId, this.activity.id, this.attendance.member.id)
      .subscribe({
          next: value => {
            this.activity = value
            this.attendance.attended = false;
            this.toasts.showSuccessToast("Potwierdzono brak obecności")
          },
          error: err => {
            this.toasts.showErrorToast(`Nie udało się wykonać operacji`)
          }
        }
      )
  }

}
