import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name:'strDate'})
export class StringToDate implements PipeTransform{
  transform(value: any): Date | string | number  {
    if(!value) return value;
    if(typeof value.getMonth === 'function') return value
    let dateArray = value.split('.')
    let d = dateArray[0]
    let m = dateArray[1]
    let y = dateArray[2]
    return new Date(`${y}-${m}-${d}`)
  }
}
