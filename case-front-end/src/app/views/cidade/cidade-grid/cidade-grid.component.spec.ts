import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CidadeGridComponent } from './cidade-grid.component';

describe('CidadeGridComponent', () => {
  let component: CidadeGridComponent;
  let fixture: ComponentFixture<CidadeGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CidadeGridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CidadeGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
