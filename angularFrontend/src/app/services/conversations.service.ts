import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject } from 'rxjs';
import { Conversaton } from '../models/conversation';
import { UserProfile } from '../models/userProfile';
import { Message } from '../models/message';
//import { } from '../models/conversation'

@Injectable({
  providedIn: 'root'
})
export class ConversationsService {

  constructor() { console.log('the coveresation Service Creating'); }

  public phone : string;
  //Observabel current user phone
  private userPhone = new Subject<string>();


  //Observable conversation data source
  private chatroom = new Subject<Conversaton>();

  private connected = new BehaviorSubject<string>("default");
  connected$ = this.connected.asObservable();

  private messageRecieved = new Subject<string>();
  messageRecieved$ = this.messageRecieved.asObservable();

  //Observable user profile
  private user = new Subject<UserProfile>();

  //Observabel stream
  userphone$  = this.userPhone.asObservable();

  //Obsrvable conversation stream
  conversations$ = this.chatroom.asObservable();
  
  //Observable user stram
  user$ = this.user.asObservable();

  addUserphone(phone:string){
    this.phone = phone;
    this.userPhone.next(phone);
  }

  addRoom(chatroom:Conversaton){
    this.chatroom.next(chatroom);  
  }

  addUser(user:UserProfile){
    this.user.next(user);
  }

  setConnected(){
    this.connected.next("connected");
  }

  setRecieved(message:string){
    console.log("message set : " + message)
    this.messageRecieved.next(message);
  }

  // setRecieved(message:Message){
  //   console.log("message set : " + message.txt)
  //   this.messageRecieved.next(message);
  // }

  



}
