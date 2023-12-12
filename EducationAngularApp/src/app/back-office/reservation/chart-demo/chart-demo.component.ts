import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {Chart} from 'chart.js';


@Component({
  selector: 'app-chart-demo',
  templateUrl: './chart-demo.component.html',
  styleUrls: ['./chart-demo.component.css']
})
export class ChartDemoComponent implements OnInit {


  ctx : any;
  config : any;
  chartData : number[] =[];
  chartDatalabels : any[] = [];

  constructor(private router:Router){}
  ngOnInit(): void {
    this.chartData.push(250);
    this.chartData.push(200);
    this.chartData.push(100);
    this.chartData.push(70);
    this.chartData.push(150);
    this.chartData.push(110);

   

    this.chartDatalabels.push('ToTal Number of Chambre of Foyer');
    this.chartDatalabels.push('Etudiant Without reservations');
    this.chartDatalabels.push('Rooms SIMPLE Reserved');
    this.chartDatalabels.push('Rooms DOUBLE Reserve');
    this.chartDatalabels.push('Rooms TRIPLE Reserve');
    this.chartDatalabels.push('Etudiant Reserved Room');
   

    this.ctx = document.getElementById('myChart');
    this.config = {
      type: 'pie',
      option: {},
      data: {
        labels: this.chartDatalabels,
        datasets: [{
          label: 'Chart Data',
          data: this.chartData,
          backgroundColor: [
            'rgba(255, 99, 132, 0.7)',   // Rouge
            'rgba(54, 162, 235, 0.7)',  // Bleu
            'rgba(255, 206, 86, 0.7)',  // Jaune
            'rgba(75, 192, 192, 0.7)',  // Vert d'eau
            'rgba(153, 102, 255, 0.7)', // Violet
            
          ],
        }],
      },
    };

    const myChart = new Chart(this.ctx,this.config)

  }
  retour(){
    this.router.navigate(['/back/reservation/list']);

  }

}
