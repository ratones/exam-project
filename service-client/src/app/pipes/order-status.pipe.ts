import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'orderStatus',
})
export class OrderStatusPipe implements PipeTransform {
  transform(value: string | undefined, ...args: unknown[]): string {
    console.log(value);

    switch (value) {
      case 'sent':
        return 'NEW';
      case 'vehicleReceived':
        return 'VEHICLE RECEIVED';
      case 'partsOrdered':
        return 'MATERIALS ORDERED';
      case 'partsReceived':
        return 'PARTS RECEIVED';
      case 'partsDelivered':
        return 'PARTS DELIVERED';
      case 'closed':
        return 'CLOSED';
      default:
        return value ? value?.toUpperCase() : '';
    }
  }
}
