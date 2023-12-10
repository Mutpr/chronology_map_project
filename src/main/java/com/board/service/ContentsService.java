package com.board.service;

import com.board.model.ContentsDTO;
import jdk.jfr.ContentType;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentsService {

    SqlSession sqlSession;
    private final String NAMESPACE ="mapper.contentsMapper";
    @Autowired
    public ContentsService(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public List<ContentsDTO> selectAllByCharacterId(int characterId){
        return sqlSession.selectList(NAMESPACE+".selectAllByCharacterId", characterId);
    }

    public boolean insertContentsInfo(ContentsDTO contentsDTO){
        if(contentsDTO.getTitle() != null){
            sqlSession.insert(NAMESPACE+".insertContentsInfo", contentsDTO);
            return true;
        }else{
            return false;
        }
    }
}
