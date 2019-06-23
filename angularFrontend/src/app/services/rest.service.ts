import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserProfile } from '../models/userProfile';
import { Observable } from 'rxjs';
import { LoginResultModel } from '../models/LoginResultModel';
import { ConversationsService } from './conversations.service';
import { Conversaton } from '../models/conversation';

const TOKEN = 'TOKEN';

@Injectable({
  providedIn: 'root'
})
export class RestService {
  private userURL:string;
  private userPhone:string;
  constructor(private http:HttpClient, private conversationService:ConversationsService) {
    localStorage.removeItem(TOKEN);
    this.userURL = 'http://10.10.69.83:8080';
   }

   public getUserByID(id:string):Observable<UserProfile>{
    return this.http.get<UserProfile>(this.userURL + "/get/byid" ,{params: {userid:id}} );
   }

   public getUserByPhone(phoneNumber:string):Observable<UserProfile>{
    return this.http.get<UserProfile>(this.userURL + "/get/byphone" ,{params: {phone:phoneNumber}} );
   }

   public getConversation(memberA:string , memberB:string):Observable<Conversaton>{
    return this.http.get<Conversaton>(this.userURL + "/get/conversation" ,{params: {from:memberA , to:memberB}} );
   }

  login(phoneNumber: string, pass: string): Observable<LoginResultModel>{
    return this.http.get<LoginResultModel>( this.userURL+ '/login', {params: {phone:phoneNumber , password:pass}});
  }

  setToken(token: string): void {
    localStorage.setItem(TOKEN, token);
    
  }

  setuser(phone:string){
    console.log("setUser : "+ localStorage.getItem(TOKEN));
    this.conversationService.addUserphone(localStorage.getItem(TOKEN));
  }

  

  isLogged() {
    console.log("ISLogged :"+localStorage.getItem(TOKEN))
      return localStorage.getItem(TOKEN) != null;
  }



}
