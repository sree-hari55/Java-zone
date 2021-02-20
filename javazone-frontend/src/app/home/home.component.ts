import { Component, OnInit } from '@angular/core';
import { AddPostServiceService } from '../add-post/add-post-service.service';
import { Observable } from 'rxjs';
import { PostPayload } from '../add-post/post-payload';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

 posts: Observable<Array<PostPayload>>;
  constructor(private postService: AddPostServiceService) { }

  ngOnInit() {
    this.posts=this.postService.getAllPosts();
  }
}
