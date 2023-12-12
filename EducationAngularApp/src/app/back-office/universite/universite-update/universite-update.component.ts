import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UniversiteService } from 'src/app/Services/universite.service';
@Component({
  selector: 'app-universite-update',
  templateUrl: './universite-update.component.html',
  styleUrls: ['./universite-update.component.css']
})
export class UniversiteUpdateComponent implements OnInit {
  universiteForm!: FormGroup;
  universiteId: number | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private universiteService: UniversiteService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.universiteForm = this.formBuilder.group({
      nomUniversite: ['', [Validators.required, Validators.maxLength(50), Validators.pattern(/^[a-zA-Z\s]*$/)]],
      adresse: ['', [Validators.required, Validators.maxLength(50)]],
      // Add other fields if necessary
    });

    // Check if a university ID is present in the URL (for updating)
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.universiteId = +id;
        this.loadUniversiteData(this.universiteId);
      }
    });
  }

  loadUniversiteData(id: number): void {
    this.universiteService.getUniversiteById(id).subscribe(
      universite => {
        // Update the form with existing university data
        this.universiteForm.patchValue(universite);
      },
      error => {
        console.error('Error fetching university details:', error);
      }
    );
  }

  onSubmit() {
    if (this.universiteForm.valid) {
      const formData = this.universiteForm.value;

        // Update the university if an ID is present
        this.universiteService.updateUniversite(this.universiteId, formData).subscribe(
          (response) => {
            console.log('University updated successfully!', response);
            this.router.navigate(['/back/universite/universiteList']);
          },
          (error) => {
            console.error('Error updating university:', error);
          }
        );
     
        // Add a new university if no ID is present
       
      
    }
  }
}