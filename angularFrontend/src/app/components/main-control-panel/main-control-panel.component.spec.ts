import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MainControlPanelComponent } from './main-control-panel.component';

describe('MainControlPanelComponent', () => {
  let component: MainControlPanelComponent;
  let fixture: ComponentFixture<MainControlPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MainControlPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MainControlPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
