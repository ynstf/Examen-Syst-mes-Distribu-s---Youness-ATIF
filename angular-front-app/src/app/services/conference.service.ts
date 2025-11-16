import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConferenceService {

  private baseUrl = "http://localhost:8888/conference-service";

  constructor(private http: HttpClient) {}

  getAllConferences(): Observable<any[]> {
    return this.http.get<any>(`${this.baseUrl}/conferences`).pipe(
      map(resp => resp._embedded ? resp._embedded.conferences : [])
    );
  }

  getConferenceReviews(conferenceId: number): Observable<any[]> {
    return this.http.get<any>(`${this.baseUrl}/conferences/${conferenceId}/reviews`).pipe(
      map(resp => resp._embedded ? resp._embedded.reviews : [])
    );
  }
}
