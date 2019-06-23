export class UserProfile{
    public userID:string;
    public name:string;
    public avatar:string;
    public phoneString:string;
    public contacts:UserProfile[];

    constructor(){
        this.contacts = new Array<UserProfile>();
    }
}