import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class KeynoteService {

  private baseUrl = "http://localhost:8888/keynote-service";

  constructor(private http: HttpClient) {}

  getAllKeynotes(): Observable<any[]> {
    return this.http.get<any>(`${this.baseUrl}/keynotes`).pipe(
      map(resp => resp._embedded ? resp._embedded.keynotes : [])
    );
  }
}
