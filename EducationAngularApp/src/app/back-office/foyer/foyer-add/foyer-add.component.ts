import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl, ValidationErrors, AsyncValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import { FoyerService } from 'src/app/Services/foyer.service';
import { of, Observable } from 'rxjs';
import { switchMap, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-foyer-add',
  templateUrl: './foyer-add.component.html',
  styleUrls: ['./foyer-add.component.css']
})
export class FoyerAddComponent implements OnInit {
  foyerForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private foyerService: FoyerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.foyerForm = this.formBuilder.group({
      nomFoyer: [
        '',
        [
          Validators.required,
          Validators.pattern('^[a-zA-Z ]*$'), // Ajout du pattern
        ],
        [this.asyncMaxLengthValidator(20), this.asyncUniqueNameValidator()],
      ],
      capaciteFoyer: [
        '',
        [
          Validators.required,
          Validators.min(50), // Ajout du validateur pour une valeur minimale
          Validators.max(1000), // Ajout du validateur pour une valeur maximale
        ],
      ],
    });

  }


  onSubmit() {
    if (this.foyerForm.valid) {
      
      this.foyerService.addFoyer(this.foyerForm.value).subscribe(
        (response: any) => {
          console.log('Réponse de l\'ajout du foyer :', response);
          this.router.navigate(['/back/foyer/foyerList']);
        },
        (error) => {
          console.error('Erreur lors de l\'ajout du foyer :', error);
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

      // Appeler votre service côté serveur pour vérifier l'unicité
      return this.foyerService.checkUniqueName(value).pipe(
        catchError(() => of(null)),
        switchMap((isUnique: boolean) => {
          return isUnique ? of(null) : of({ uniqueName: true });
        })
      );
    };
  }
}

