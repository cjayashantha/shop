import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  productCode;
  numberOfUnits;

  price= null;

  constructor(private orderService:OrderService) { }

  ngOnInit(): void {
  }



  onClickGetPrice() {
      this.orderService.getPrice(this.productCode, this.numberOfUnits)
        .subscribe(res => {
          this.price = res.body;
        }, err => { console.log(err)});
      
  }

}
