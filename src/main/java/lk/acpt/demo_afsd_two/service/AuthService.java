package lk.acpt.demo_afsd_two.service;

public interface AuthService {
   boolean UserRegister(String email, String password);
   String UserLogin(String email, String password);
}
