import { Injectable } from '@angular/core';
import { HttpCallesService } from './http-calles.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private httpCalles: HttpCallesService
  ) { }

  getPricingList(code: string, units: number) {
     return this.httpCalles.makeGet(`pricing/multiple?productCode=${code}&units=${units}`);
  }

  getPrice(productCode: string, numberOfUnits: number) {
     return this.httpCalles.makeGet(`pricing?productCode=${productCode}&units=${numberOfUnits}`)
  }
}
