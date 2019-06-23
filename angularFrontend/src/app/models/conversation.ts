import {Message} from './message';
import { UserProfile } from './userProfile';
import { MessOut } from './messout';


export class Conversaton{
    
    public sessionId:string;
    public conversationID:string;
    public members:UserProfile[];
    public messages:MessOut[];

    constructor(){
        this.messages = new Array<MessOut>();
        this.members = new Array<UserProfile>();
    }
        
}