package com.example.practice_test_api.services.impl;

import com.example.practice_test_api.dto.UserDto;
import com.example.practice_test_api.entities.User;
import com.example.practice_test_api.repositories.UserRepository;
import com.example.practice_test_api.services.Exceptions.DataIntegratyViolationException;
import com.example.practice_test_api.services.Exceptions.ObjectNotFoundException;
import com.example.practice_test_api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow( () -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(UserDto obj){
        findByEmail(obj);
        return userRepository.save(modelMapper.map(obj, User.class));
    }

    @Override
    public User update(UserDto obj){
        findByEmail(obj);
        return userRepository.save(modelMapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserDto obj){
        Optional<User> user = userRepository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals((obj.getId()))){
            throw new DataIntegratyViolationException(("Email já cadastrado"));

        }
    }

}
