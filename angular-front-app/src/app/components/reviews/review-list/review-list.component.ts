import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ConferenceService } from '../../../services/conference.service';

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './review-list.component.html',
  styleUrl: './review-list.component.css'
})
export class ReviewListComponent implements OnInit {

  reviews: any[] = [];
  conferenceId!: number;

  constructor(
    private route: ActivatedRoute,
    private conferenceService: ConferenceService
  ) {}

  ngOnInit(): void {
    this.conferenceId = Number(this.route.snapshot.paramMap.get('id'));

    this.conferenceService.getConferenceReviews(this.conferenceId)
      .subscribe((data: any[]) => {
        this.reviews = data;
      });
  }

}

