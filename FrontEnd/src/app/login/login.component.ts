import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private userService: UserService) {
    this.user = new User();
  }
  user: User;
  email : string
  password : string

  ngOnInit() {
  }

  login(){
    this.userService.validLogIn(this.user).subscribe( res => {
      if (res != null) {
        localStorage.setItem("name", res.name);
        this.userService.updateUser(res.id, {active: true});
        if(res.role=="admin"){
          this.router.navigate(["home"]);
        }
        else{
          this.router.navigate(["home/user/user"]);
        }
      }
    }, err => {
      alert('LogIn invalido');
    });
  }

  updateActive(user: User) {
    this.userService.updateUser(this.user.id,
      { active: true })
      .subscribe(
        data => {
          console.log(data);
          this.user = data as User;
        },
        error => console.log(error));
  }

}