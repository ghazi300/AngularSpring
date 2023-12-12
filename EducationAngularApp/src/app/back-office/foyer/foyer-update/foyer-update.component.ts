import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FoyerService } from 'src/app/Services/foyer.service';
@Component({
  selector: 'app-foyer-update',
  templateUrl: './foyer-update.component.html',
  styleUrls: ['./foyer-update.component.css']
})
export class FoyerUpdateComponent implements OnInit {
  foyerForm!: FormGroup;
  foyerId: number | undefined;

  constructor(
    private formBuilder: FormBuilder,
    private foyerService: FoyerService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.foyerForm = this.formBuilder.group({
      nomFoyer: ['', [Validators.required, Validators.maxLength(50), Validators.pattern(/^[a-zA-Z\s]*$/)]],
      capaciteFoyer: ['', [Validators.required, Validators.maxLength(50)]],
      // Add other fields if necessary
    });

    // Check if a university ID is present in the URL (for updating)
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.foyerId = +id;
        this.loadFoyerData(this.foyerId);
      }
    });
  }

  loadFoyerData(id: number): void {
    this.foyerService.getFoyerById(id).subscribe(
      foyer => {
        // Update the form with existing university data
        this.foyerForm.patchValue(foyer);
      },
      error => {
        console.error('Error fetching foyer details:', error);
      }
    );
  }

  onSubmit() {
    if (this.foyerForm.valid) {
      const formData = this.foyerForm.value;

        // Update the university if an ID is present
        this.foyerService.updateFoyer(this.foyerId, formData).subscribe(
          (response) => {
            console.log('Foyer updated successfully!', response);
            this.router.navigate(['/back/foyer/foyerList']);
          },
          (error) => {
            console.error('Error updating foyer:', error);
          }
        );
     
        // Add a new university if no ID is present
       
      
    }
  }
}