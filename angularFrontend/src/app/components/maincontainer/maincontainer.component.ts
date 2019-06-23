import { Component, OnInit } from '@angular/core';
import { ConversationsService } from 'src/app/services/conversations.service';
import { RestService } from 'src/app/services/rest.service';

@Component({
  selector: 'app-maincontainer',
  templateUrl: './maincontainer.component.html',
  styleUrls: ['./maincontainer.component.css'],
  
})
export class MaincontainerComponent implements OnInit {
  title = 'chat-frontend';
  constructor(private restService:RestService , private conversationService:ConversationsService) { }

  ngOnInit() {
  }

}
