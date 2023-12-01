package com.board.service;

import com.board.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    final String NAMESPACE = "mapper.userMapper";
    SqlSession sqlSession;

    @Autowired
    public UserService(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public boolean userRegister(UserDTO userDTO){
        if(userDTO.getUsername() != null){
            sqlSession.insert(NAMESPACE + ".insertUser", userDTO);
            return true;
        } else {
            return false;
        }
    }

    public List<UserDTO> selectAll(){
        return sqlSession.selectList(NAMESPACE+".selectAll");
    }

    public UserDTO selectOne(UserDTO userDTO){
        return sqlSession.selectOne(NAMESPACE+".selectId",userDTO);
    }

    public UserDTO selectOneByID(int id){
        return sqlSession.selectOne(NAMESPACE+".selectOneById", id);
    }
}
