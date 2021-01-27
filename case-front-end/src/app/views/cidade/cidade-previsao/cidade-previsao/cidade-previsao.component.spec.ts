import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CidadePrevisaoComponent } from './cidade-previsao.component';

describe('CidadePrevisaoComponent', () => {
  let component: CidadePrevisaoComponent;
  let fixture: ComponentFixture<CidadePrevisaoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CidadePrevisaoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CidadePrevisaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
