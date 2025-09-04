package com.example.panicbuttonproject.UserLoginAndRegistration.Service;

import com.example.panicbuttonproject.UserLoginAndRegistration.DTOclass.PanicAlertRequest;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.PanicAlert;
import com.example.panicbuttonproject.UserLoginAndRegistration.Entity.UserEntity;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.PanicAlertRepository;
import com.example.panicbuttonproject.UserLoginAndRegistration.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@Service
public class PanicAlertServiceImpl implements PanicAlertService {

    @Autowired
    private PanicAlertRepository panicAlertRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PanicAlert sendPanicAlert(PanicAlertRequest panicAlertRequest, String username ){
    UserEntity user = userRepository.findByUserName(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

    PanicAlert alert = PanicAlert.builder()
            .message(panicAlertRequest.getMessage())
            .user(user)
            .build();

    return panicAlertRepository.save(alert);

}


}
