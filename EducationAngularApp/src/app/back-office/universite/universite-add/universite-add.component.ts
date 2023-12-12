
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { UniversiteService } from 'src/app/Services/universite.service';
import { of, Observable } from 'rxjs';
import { switchMap, catchError } from 'rxjs/operators';

import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
@Component({
  selector: 'app-universite-add',
  templateUrl: './universite-add.component.html',
  styleUrls: ['./universite-add.component.css']
})
export class UniversiteAddComponent implements OnInit {
  universiteForm!: FormGroup;
  foyerNames: string[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private universiteService: UniversiteService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.universiteForm = this.formBuilder.group({
      nomUniversite: [
        '',
        [
          Validators.required,
          Validators.pattern('^[a-zA-Z ]*$'),
        ],
        [this.asyncMaxLengthValidator(20), this.asyncUniqueNameValidator()],
      ],
      adresse: ['', [Validators.required], [this.asyncMaxLengthValidator(20)]],
      nomFoyer: [''],
      
    });

    this.fetchFoyerNames();
  }

  fetchFoyerNames() {
    this.universiteService.getFoyerNames().subscribe(
      names => {
        this.foyerNames = names;
      },
      error => {
        console.error('Error fetching foyer names:', error);
      }
    );
  }


  onSubmit() {
    if (this.universiteForm.valid) {
      const formData = this.universiteForm.value;
      this.universiteService.addUniversite(formData).subscribe(
        (response: any) => {
          console.log('Réponse de l\'ajout de l\'université :', response);
          this.fetchFoyerNames();
          this.router.navigate(['/back/universite/universiteList']);
        },
        (error) => {
          console.error('Erreur lors de l\'ajout de l\'université :', error);
          console.error('Détail de l\'erreur :', error.error);
        }
      );
    }
  }
  private asyncMaxLengthValidator(maxLength: number): AsyncValidatorFn {
    return (control: AbstractControl):
      | Promise<ValidationErrors | null>
      | Observable<ValidationErrors | null> => {
      const value: string = control.value;

      if (!value) {
        return of(null);
      }

      return of(value.length <= maxLength ? null : { maxLength: true });
    };
  }

  private asyncUniqueNameValidator(): AsyncValidatorFn {
    return (control: AbstractControl):
      | Promise<ValidationErrors | null>
      | Observable<ValidationErrors | null> => {
      const value: string = control.value;

      if (!value) {
        return of(null);
      }

      return this.universiteService.checkUniqueName(value).pipe(
        catchError(() => of(null)),
        switchMap((isUnique: boolean) => {
          return isUnique ? of(null) : of({ uniqueName: true });
        })
      );
    };
  }

}
