import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ConferenceService } from '../../../services/conference.service';

@Component({
  selector: 'app-conference-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './conference-list.component.html',
  styleUrl: './conference-list.component.css'
})
export class ConferenceListComponent implements OnInit {

  conferences: any[] = [];

  constructor(private conferenceService: ConferenceService) {}

  extractId(link: string): number {
    return +link.split('/').pop()!;
  }

  ngOnInit(): void {
    this.conferenceService.getAllConferences().subscribe(data => {
      this.conferences = data;
    });
  }
}
