import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoyerDetailsComponent } from './foyer-details.component';

describe('FoyerDetailsComponent', () => {
  let component: FoyerDetailsComponent;
  let fixture: ComponentFixture<FoyerDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoyerDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoyerDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
