import { Routes } from '@angular/router';
import { KeynoteListComponent } from './components/keynotes/keynote-list/keynote-list.component';
import { ConferenceListComponent } from './components/conferences/conference-list/conference-list.component';
import { ReviewListComponent } from './components/reviews/review-list/review-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'keynotes', pathMatch: 'full' },
  { path: 'keynotes', component: KeynoteListComponent },
  { path: 'conferences', component: ConferenceListComponent },
  { path: 'conference/:id/reviews', component: ReviewListComponent },

];
