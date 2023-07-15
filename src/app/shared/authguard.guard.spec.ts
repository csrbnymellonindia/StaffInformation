import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { of } from 'rxjs';

import { AuthService } from '../services/auth.service';
import { AuthGuard } from './authguard.guard';

describe('AuthGuard', () => {
  let guard: AuthGuard;
  let router: Router;
  let authService: AuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      providers: [AuthGuard, AuthService],
    });

    guard = TestBed.inject(AuthGuard);
    router = TestBed.inject(Router);
    authService = TestBed.inject(AuthService);
  });

  it('should allow access when user is logged in', () => {
    // Mock the logged in status to be true
    spyOnProperty(authService, '$loggedInStatus', 'get').and.returnValue(of(true));

    const routeSnapshot: ActivatedRouteSnapshot = {} as ActivatedRouteSnapshot;
    const stateSnapshot: RouterStateSnapshot = {} as RouterStateSnapshot;

    const result = guard.canActivate(routeSnapshot, stateSnapshot);

    expect(result).toBe(true);
  });

  it('should redirect to login page when user is not logged in', () => {
    // Mock the logged in status to be false
    spyOnProperty(authService, '$loggedInStatus', 'get').and.returnValue(of(false));

    const routeSnapshot: ActivatedRouteSnapshot = {} as ActivatedRouteSnapshot;
    const stateSnapshot: RouterStateSnapshot = {} as RouterStateSnapshot;

    const navigateSpy = spyOn(router, 'navigate');

    const result = guard.canActivate(routeSnapshot, stateSnapshot);

    expect(result).toBe(false);
    expect(navigateSpy).toHaveBeenCalledWith(['/login']);
  });
});
