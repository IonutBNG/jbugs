import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuthenticationInterceptorComponent } from './authentication-interceptor.component';

describe('AuthenticationInterceptorComponent', () => {
  let component: AuthenticationInterceptorComponent;
  let fixture: ComponentFixture<AuthenticationInterceptorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuthenticationInterceptorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuthenticationInterceptorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
