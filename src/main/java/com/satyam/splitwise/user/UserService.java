package com.satyam.splitwise.user;

import com.satyam.splitwise.expense_group.ExpenseGroupModel;
import com.satyam.splitwise.user.dtos.RegisterUserRequestDto;
import com.satyam.splitwise.user.dtos.UserLoginRequestDto;
import com.satyam.splitwise.user.dtos.UserDetailsDto;
import com.satyam.splitwise.user.exceptions.InvalidCredentialsException;
import com.satyam.splitwise.user.exceptions.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private PasswordEncoder encoder;





    public UserDetailsDto registerUser(RegisterUserRequestDto user){
        UserModel newUser = modelMapper.map(user, UserModel.class);
        newUser.setPassword(encoder.encode(user.getPassword()));
        UserModel regiteredUser =  userRepository.save(newUser);
        return modelMapper.map(regiteredUser, UserDetailsDto.class);
    }

    public UserDetailsDto loginUser(UserLoginRequestDto user){
        Optional<UserModel> userModel = userRepository.findByUsername(user.getUsername());
        if(userModel.isEmpty()){
            throw new UserNotFoundException("User with username: "+user.getUsername()+" not found");
        }
        if(!encoder.matches(user.getPassword(), userModel.get().getPassword())){
            throw new InvalidCredentialsException("Incorrect username, password combination");
        }
        return modelMapper.map(userModel.get(), UserDetailsDto.class);
    }

    public UserModel findUserById(Long userId){
        Optional<UserModel> userModel = userRepository.findById(userId);
        if(userModel.isEmpty()){
            throw new UserNotFoundException("User with id: "+userId+" not found");
        }
        return userModel.get();
    }

    public Set<UserModel> findAllUsersByIds(List<Long> userIds){
        List<UserModel> users = userRepository.findAllById(userIds);
        return users.stream().collect(Collectors.toSet());
    }
    public UserModel updateUser(UserModel user){
        return userRepository.save(user);
    }

    public Set<UserModel> getUserListfromEmailList(List<String> emailList){
        Set<UserModel> userModelList = userRepository.findByUsersByEmails(emailList);
        return userModelList;
    }

    public Set<ExpenseGroupModel> findGroupsByUser(Long id) {
        return userRepository.findWithGroupsByUserId(id);
    }



}
