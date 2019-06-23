import { Component, OnInit } from '@angular/core';
import {UserProfile} from '../../models/userProfile'
import {Router} from '@angular/router';
import { ConversationsService} from '../../services/conversations.service'
import { RestService } from  '../../services/rest.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
 
})
export class LoginComponent implements OnInit {

  username :string;

  constructor(private restService:RestService , private router:Router ,private conversationService:ConversationsService) { }

  ngOnInit() {
  }

  tryLogin( ){
    this.restService.login(this.username , "passWD").subscribe(
      r => {
        if (r.token != null) {
          console.log('setting token');
          this.restService.setToken(r.token);
          console.log('navigating url');
          this.router.navigateByUrl('');
          console.log('setting user');
          this.restService.setuser(r.token);
          
        }
      },
      r => {
        alert(r.error.error);
      });
    this.router.navigateByUrl('/login');
  }
}


