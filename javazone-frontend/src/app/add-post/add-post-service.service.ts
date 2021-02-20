import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PostPayload } from './post-payload';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AddPostServiceService {
  private postUrl = '/api/post';
  private getUrl='/api/post/getAllPosts';
  
  constructor(private httpClient:HttpClient) { }
   addPost(postPayload:PostPayload) : Observable<any>{
      return this.httpClient.post(this.postUrl,postPayload,{responseType:'text'});
  }


  getAllPosts():Observable<Array<PostPayload>>{
    return this.httpClient.get<Array<PostPayload>>(this.getUrl);
  }
  getPost(permaLink: Number):Observable<PostPayload>{
    return this.httpClient.get<PostPayload>('/api/post/getPost/' +permaLink);
  }
}
