import { HttpClient, HttpHeaders, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom } from "rxjs";
import { Registration, ResponseMessage } from "./models";

const URL_POST_API_REGISTER = 'http://localhost:8080/api/register'

@Injectable()
export class RegistrationService {

  constructor(private http: HttpClient) { }

  postRegistration(reg: Registration): Promise<ResponseMessage> {
    const headers = new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded')

    const params = new HttpParams()
        .set('name', reg.name)
        .set('email', reg.email)

    return lastValueFrom(
      //this.http.post<ResponseMessage>(URL_POST_API_REGISTER, params.toString(), { headers })
      //this.http.post<ResponseMessage>(URL_POST_API_REGISTER, reg)
      this.http.post<ResponseMessage>(URL_POST_API_REGISTER, reg, { headers })
    )
  }

}
