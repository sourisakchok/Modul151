package com.example.jwt.domain.user.dto;

import com.example.jwt.domain.authority.Authority;
import com.example.jwt.domain.authority.dto.AuthorityDTO;
import com.example.jwt.domain.role.Role;
import com.example.jwt.domain.role.dto.RoleDTO;
import com.example.jwt.domain.user.User;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-13T22:39:42+0100",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromDTO(UserDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        User user = new User();

        user.setId( arg0.getId() );
        user.setAddress( arg0.getAddress() );
        user.setBirthday( arg0.getBirthday() );
        user.setEmail( arg0.getEmail() );
        user.setFirstName( arg0.getFirstName() );
        user.setLastName( arg0.getLastName() );
        user.setLevel( arg0.getLevel() );
        user.setOrt( arg0.getOrt() );
        user.setPassword( arg0.getPassword() );
        user.setPlz( arg0.getPlz() );
        user.setRoles( roleDTOSetToRoleSet( arg0.getRoles() ) );

        return user;
    }

    @Override
    public List<User> fromDTOs(List<UserDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( arg0.size() );
        for ( UserDTO userDTO : arg0 ) {
            list.add( fromDTO( userDTO ) );
        }

        return list;
    }

    @Override
    public Set<User> fromDTOs(Set<UserDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Set<User> set = new LinkedHashSet<User>( Math.max( (int) ( arg0.size() / .75f ) + 1, 16 ) );
        for ( UserDTO userDTO : arg0 ) {
            set.add( fromDTO( userDTO ) );
        }

        return set;
    }

    @Override
    public UserDTO toDTO(User arg0) {
        if ( arg0 == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( arg0.getId() );
        userDTO.setAddress( arg0.getAddress() );
        userDTO.setBirthday( arg0.getBirthday() );
        userDTO.setEmail( arg0.getEmail() );
        userDTO.setFirstName( arg0.getFirstName() );
        userDTO.setLastName( arg0.getLastName() );
        userDTO.setLevel( arg0.getLevel() );
        userDTO.setOrt( arg0.getOrt() );
        userDTO.setPassword( arg0.getPassword() );
        userDTO.setPlz( arg0.getPlz() );
        userDTO.setRoles( roleSetToRoleDTOSet( arg0.getRoles() ) );

        return userDTO;
    }

    @Override
    public List<UserDTO> toDTOs(List<User> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( arg0.size() );
        for ( User user : arg0 ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    @Override
    public Set<UserDTO> toDTOs(Set<User> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        Set<UserDTO> set = new LinkedHashSet<UserDTO>( Math.max( (int) ( arg0.size() / .75f ) + 1, 16 ) );
        for ( User user : arg0 ) {
            set.add( toDTO( user ) );
        }

        return set;
    }

    @Override
    public User fromUserRegisterDTO(UserRegisterDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setId( dto.getId() );
        user.setAddress( dto.getAddress() );
        user.setBirthday( dto.getBirthday() );
        user.setEmail( dto.getEmail() );
        user.setFirstName( dto.getFirstName() );
        user.setLastName( dto.getLastName() );
        user.setOrt( dto.getOrt() );
        user.setPassword( dto.getPassword() );
        user.setPlz( dto.getPlz() );

        return user;
    }

    protected Authority authorityDTOToAuthority(AuthorityDTO authorityDTO) {
        if ( authorityDTO == null ) {
            return null;
        }

        Authority authority = new Authority();

        authority.setId( authorityDTO.getId() );
        authority.setName( authorityDTO.getName() );

        return authority;
    }

    protected Set<Authority> authorityDTOSetToAuthoritySet(Set<AuthorityDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Authority> set1 = new LinkedHashSet<Authority>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( AuthorityDTO authorityDTO : set ) {
            set1.add( authorityDTOToAuthority( authorityDTO ) );
        }

        return set1;
    }

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setAuthorities( authorityDTOSetToAuthoritySet( roleDTO.getAuthorities() ) );
        role.setName( roleDTO.getName() );

        return role;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRole( roleDTO ) );
        }

        return set1;
    }

    protected AuthorityDTO authorityToAuthorityDTO(Authority authority) {
        if ( authority == null ) {
            return null;
        }

        AuthorityDTO authorityDTO = new AuthorityDTO();

        authorityDTO.setId( authority.getId() );
        authorityDTO.setName( authority.getName() );

        return authorityDTO;
    }

    protected Set<AuthorityDTO> authoritySetToAuthorityDTOSet(Set<Authority> set) {
        if ( set == null ) {
            return null;
        }

        Set<AuthorityDTO> set1 = new LinkedHashSet<AuthorityDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Authority authority : set ) {
            set1.add( authorityToAuthorityDTO( authority ) );
        }

        return set1;
    }

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setAuthorities( authoritySetToAuthorityDTOSet( role.getAuthorities() ) );
        roleDTO.setName( role.getName() );

        return roleDTO;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new LinkedHashSet<RoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDTO( role ) );
        }

        return set1;
    }
}
