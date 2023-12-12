import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoyerUpdateComponent } from './foyer-update.component';

describe('FoyerUpdateComponent', () => {
  let component: FoyerUpdateComponent;
  let fixture: ComponentFixture<FoyerUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoyerUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoyerUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
