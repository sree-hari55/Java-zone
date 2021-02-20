import { Component, OnInit } from '@angular/core';
import { AddPostServiceService } from '../add-post/add-post-service.service';
import { ActivatedRoute } from '@angular/router';
import { PostPayload } from '../add-post/post-payload';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  permaLink: Number;
  post:PostPayload;
  constructor(private postService:AddPostServiceService,private router:ActivatedRoute) { }

  ngOnInit() {
    this.router.params.subscribe(params => {
      this.permaLink = params['id'];
    });

  this.postService.getPost(this.permaLink).subscribe((data:PostPayload) => {
    this.post = data;
  },(err: any) => {
    console.log('Failure Response');
  })
  }
}
