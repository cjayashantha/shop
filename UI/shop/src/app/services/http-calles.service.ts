import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class HttpCallesService {

  constructor(private http:HttpClient) { }

  makeGet(url:string) : any {
    return this.http.get<any>(environment.baseUrl + environment.apiPath +url,{observe: 'response'});
  }
}
