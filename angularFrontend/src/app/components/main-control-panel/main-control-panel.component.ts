import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery'; 
import { Observable, of} from 'rxjs';
import { map } from "rxjs/operators";
import { ConversationsService} from '../../services/conversations.service';
import { UserProfile } from 'src/app/models/userProfile';
import { Conversaton } from 'src/app/models/conversation';
import { MatDialogConfig, MatDialog } from '@angular/material';
import { AddContactDialogComponent } from '../add-contact-dialog/add-contact-dialog.component';
import { RestService} from '../../services/rest.service';
import { ChatService } from 'src/app/services/chat.service';



@Component({
  selector: 'app-main-control-panel',
  templateUrl: './main-control-panel.component.html',
  styleUrls: ['./main-control-panel.component.css']
})
export class MainControlPanelComponent implements OnInit {

  user:UserProfile;
  p :string;
  recipient:UserProfile;
  chatroom:Conversaton;
  contacts: Observable<UserProfile[]>;
  

  constructor(private chatService:ChatService ,private conversationService:ConversationsService, private restService:RestService,private dialog: MatDialog) { 
    //the first user will be changed to the conversation data provided by the service
      // this.conversationService.userphone$.subscribe(phone=>{
      // this.p = phone;
      // console.log("user phone subscription: " + phone );
      // this.login(phone);
      // }) 
      
      this.login(this.conversationService.phone)
  }

  ngOnInit() {
    //this.restService.getUserByID("Louis Litt").subscribe(user =>{console.log(user)});
    
    //this.contacts = of(this.user.contacts);
    //console.log("username is  "+ this.user.name)
    

    
    this.uiStatusInit();
     
    
  }

  login(phone:string){
    this.restService.getUserByPhone(phone).subscribe(user => {
      this.user = user;
      this.conversationService.addUser(user);
      this.contacts = of(user.contacts);
      this.conversationService.connected$.subscribe(() => {
        this.chatService.registerSession(this.user.userID);
      })
      //this.changeChatroom(event, this.contacts[0])
      console.log(this.user)});

  }

  changeChatroom(event ,contact:UserProfile){
    //will be chagged this is dummy data
      let chatroom:Conversaton;
      chatroom = new Conversaton();
      chatroom.members.push(contact);
      this.chatroom = chatroom;
      this.chatService.registerSession(this.user.userID);
      this.notify();
       
  }

  notify(){
    this.conversationService.addRoom(this.chatroom);
  }


  onKeyupSearch(value:string){
    this.contacts = of(this.user.contacts).pipe(map(x=>{
      return x.filter(y=>y.name.toLowerCase().indexOf(value.toLowerCase()) > -1);
    }))
  }

  openDialog() {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '80%';
    dialogConfig.panelClass = 'custom-modalbox';
    
    
    dialogConfig.data = {
      id: 1,
      title: 'Angular For Beginners'
    };
    
    const dialogRef = this.dialog.open(AddContactDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
        data => console.log("Dialog output:", data)
    );    
}

  statusOnline(){
    //this.user.status = "online";
    $("#status-options").removeClass("active");
  }
  statusAway(){
    //this.user.status = "away";
    $("#status-options").removeClass("active");
  }
  statusBusy(){
    //this.user.status = "busy";
    $("#status-options").removeClass("active");
  }
  statusOffline(){
    //this.user.status = "offline";
    $("#status-options").removeClass("active");
  }

    

  uiStatusInit(){
    
    $("#profile-img").click(function() {
      $("#status-options").toggleClass("active");
    });
    
    $(".expand-button").click(function() {
      $("#profile").toggleClass("expanded");
      $("#contacts").toggleClass("expanded");
    });
    
   
     
  }

}
