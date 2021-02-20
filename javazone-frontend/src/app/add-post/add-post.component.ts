import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { PostPayload } from './post-payload';
import { AddPostServiceService } from './add-post-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent implements OnInit {
  addPostForm:FormGroup;
  postPayload:PostPayload;
  submitted = false;
  constructor(private formBuilder: FormBuilder,private addPostService:AddPostServiceService,private router:Router) { 

    this.addPostForm = this.formBuilder.group({
      title : ['', Validators.required],
      body : ['', Validators.required]
    });

    this.postPayload = {
      postId:'',
      content: '',
      title: '',
      userName: ''
    }
  }
  get fval() {
    return this.addPostForm.controls;
    }
  ngOnInit(): void {
  }

  addPost(){
    this.submitted = true;

       // stop here if form is invalid
    if (this.addPostForm.invalid) {
      return;
    }

    this.postPayload.content = this.addPostForm.get('body').value;
    this.postPayload.title = this.addPostForm.get('title').value;
    this.addPostService.addPost(this.postPayload).subscribe(data => {
      console.log(data);
      this.router.navigateByUrl('');
    }, error => {
      console.log(error);
    })
  }

}
