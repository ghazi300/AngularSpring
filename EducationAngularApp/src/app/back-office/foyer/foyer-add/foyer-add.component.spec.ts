import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoyerAddComponent } from './foyer-add.component';

describe('FoyerAddComponent', () => {
  let component: FoyerAddComponent;
  let fixture: ComponentFixture<FoyerAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoyerAddComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoyerAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
