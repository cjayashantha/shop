import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-price-list',
  templateUrl: './price-list.component.html',
  styleUrls: ['./price-list.component.scss']
})
export class PriceListComponent implements OnInit {

  priceList: any;

  productCode;
  numberOfUnits;
  unitsUpTo = new Array(50);

  constructor(
    private orderService: OrderService
  ) { }

  ngOnInit(): void {
    this.productCode = 'penguinear';
    this.numberOfUnits = 50;
    this.onClickGetPriceList();
  }


  getPricingList(code:string, units:number) {
    this.orderService.getPricingList(code, units)
      .subscribe(res => {
        this.priceList = res.body
      }, err => { console.log(err)});
    
  }

  onClickGetPriceList(){
    this.getPricingList(this.productCode, this.numberOfUnits);
  }

}
