package com.security.security.config.auth;


import com.security.security.mapper.UserMapper;
import com.security.security.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessUrl("/login");
// 로그인 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    // Security session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("service principal 23line : " + username);
        UserVO userVO = new UserVO();
        try{
            userVO = userMapper.check_user_id(username);
            if (userVO != null) {
                return new PrincipalDetails(userVO);
            }
        }catch (Exception e){

        }
        return null;
    }
}