import {Injectable} from "@angular/core";
import Swal from 'sweetalert2/dist/sweetalert2.js';

@Injectable({
  providedIn: 'root'
})
export class ToastsService {

  constructor() {
  }

  showSuccessToast(message: string) {
    Swal.fire(
      'Sukces', message, 'success'
    )
  }

  showCancelledToast(message: string) {
    Swal.fire(
      'Rezygnacja', message, 'success'
    )
  }

  showErrorToast(message: string) {
    Swal.fire(
      'Nieprzewidziany błąd', message, 'error'
    )
  }

}
