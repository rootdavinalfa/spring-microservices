package xyz.dvnlabs.serviceuser.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.dvnlabs.corelibrary.exception.ResourceExistException;
import xyz.dvnlabs.serviceuser.entity.User;
import xyz.dvnlabs.serviceuser.repository.UserRepository;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findById(String s) {
        return userRepository.findById(s)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found! " + s)
                );
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(User entity) throws ResourceExistException {
        if (userRepository.existsById(entity.getId())){
            throw new ResourceExistException("User exist!");
        }
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return null;
    }

    @Override
    public void delete(String s) {

    }
}
