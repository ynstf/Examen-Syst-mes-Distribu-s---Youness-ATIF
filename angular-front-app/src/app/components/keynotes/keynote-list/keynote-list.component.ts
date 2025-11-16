import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KeynoteService } from '../../../services/keynote.service';

@Component({
  selector: 'app-keynote-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './keynote-list.component.html',
  styleUrl: './keynote-list.component.css'
})
export class KeynoteListComponent implements OnInit {

  keynotes: any[] = [];

  constructor(private keynoteService: KeynoteService) {}

  ngOnInit(): void {
    this.keynoteService.getAllKeynotes().subscribe(data => {
      this.keynotes = data;
    });
  }
}
