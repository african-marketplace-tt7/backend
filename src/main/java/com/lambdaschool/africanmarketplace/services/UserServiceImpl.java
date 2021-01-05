package com.lambdaschool.africanmarketplace.services;

import com.lambdaschool.africanmarketplace.exceptions.ResourceNotFoundException;
import com.lambdaschool.africanmarketplace.models.*;
import com.lambdaschool.africanmarketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements UserService Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl
    implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private MarketLocationService marketLocationService;


    /**
     * Connects this service to the Role table
     */
    @Autowired
    private RoleService roleService;

    @Autowired
    private HelperFunctions helperFunctions;

    public User findUserById(long id) throws
                                      ResourceNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {

        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {

        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername()
            .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setEmail(user.getEmail()
            .toLowerCase());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setCity(user.getCity());
        newUser.setCountry(user.getCountry());
        newUser.setPreferredCurrency(user.getPreferredCurrency());
        newUser.setPrimaryLanguage(user.getPrimaryLanguage());
        newUser.setPhotoURL(user.getPhotoURL());

        newUser.getRoles()
            .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser,
                    addRole));
        }

        newUser = userrepos.save(newUser);


        for (MarketLocation ml : user.getMarketLocations())
        {
            ml.setUser(newUser);
            MarketLocation newMarketLocation = marketLocationService.save(ml);
            newUser.getMarketLocations().add(newMarketLocation);
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
        User user,
        long id)
    {
        User currentUser = findUserById(id);

        // update own thing
        // admin update
        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        {
            if (user.getUsername() != null)
            {
                currentUser.setUsername(user.getUsername()
                    .toLowerCase());
            }

            if (user.getPassword() != null)
            {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }

            if (user.getEmail() != null)
            {
                currentUser.setEmail(user.getEmail()
                    .toLowerCase());
            }

            if (user.getFirstName() != null)
            {
                currentUser.setFirstName(user.getFirstName());
            }

            if (user.getLastName() != null)
            {
                currentUser.setLastName(user.getLastName());
            }

            if (user.getCity() != null)
            {
                currentUser.setCity(user.getCity());
            }

            if (user.getCountry() != null)
            {
                currentUser.setCountry(user.getCountry());
            }

            if (user.getPreferredCurrency() != null)
            {
                currentUser.setPreferredCurrency(user.getPreferredCurrency());
            }

            if (user.getPrimaryLanguage() != null)
            {
                currentUser.setPrimaryLanguage(user.getPrimaryLanguage());
            }

            if(user.getPhotoURL() != null)
            {
                currentUser.setPhotoURL(user.getPhotoURL());
            }

            if (user.getRoles()
                .size() > 0)
            {
                currentUser.getRoles()
                    .clear();
                for (UserRoles ur : user.getRoles())
                {
                    Role addRole = roleService.findRoleById(ur.getRole()
                        .getRoleid());

                    currentUser.getRoles()
                        .add(new UserRoles(currentUser,
                            addRole));
                }
            }

            if(user.getMarketLocations().size() > 0)
            {
                currentUser.getMarketLocations().clear();
                for (MarketLocation ml : user.getMarketLocations())
                {
                    ml.setUser(currentUser);
                    MarketLocation newMarketLocation = marketLocationService.save(ml);
                    currentUser.getMarketLocations().add(newMarketLocation);
                }
            }

            return userrepos.save(currentUser);
        } else
        {
            // note we should never get to this line but is needed for the compiler
            // to recognize that this exception can be thrown
            throw new ResourceNotFoundException("This user is not authorized to make change");
        }
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
