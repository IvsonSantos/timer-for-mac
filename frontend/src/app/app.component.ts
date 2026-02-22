import { Component, OnInit, OnDestroy } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true
})
export class AppComponent implements OnInit, OnDestroy {
  private totalSeconds = 10 * 60;
  private currentSeconds = this.totalSeconds;
  private intervalId?: any;

  timeLeftDisplay = '10:00';
  dashOffset = 283;

  ngOnInit() {
    this.startTimer();
  }

  ngOnDestroy() {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  private startTimer() {
    this.updateDisplay();
    this.intervalId = setInterval(() => {
      if (this.currentSeconds > 0) {
        this.currentSeconds--;
        this.updateDisplay();
      } else {
        clearInterval(this.intervalId);
      }
    }, 1000);
  }

  private updateDisplay() {
    const minutes = Math.floor(this.currentSeconds / 60);
    const seconds = this.currentSeconds % 60;
    this.timeLeftDisplay = `${minutes}:${seconds.toString().padStart(2, '0')}`;
    
    // Update SVG progress
    const progress = this.currentSeconds / this.totalSeconds;
    this.dashOffset = 283 * (1 - progress);
  }
}
