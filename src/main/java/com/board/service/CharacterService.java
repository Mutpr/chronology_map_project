package com.board.service;

import com.board.model.CharacterDTO;
import com.board.model.ContentsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    final String NAMESPACE = "mapper.characterMapper";
    SqlSession sqlSession;
    public CharacterService(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }

    public List<CharacterDTO> selectCharactersById(int userid){
        return sqlSession.selectList(NAMESPACE+".characterSelectByUserId", userid);
    }

    public CharacterDTO selectedOneChar(int charId){
        return sqlSession.selectOne(NAMESPACE+".characterSelect", charId);
    }

    public boolean insertCharacterInfo(CharacterDTO characterDTO){
        if(characterDTO.getName() != null){
            sqlSession.insert(NAMESPACE+".insertCharacterInfo", characterDTO);
            return true;
        }else{
            return false;
        }
    }

    public boolean insertContentsInfo(ContentsDTO contentsDTO){
        if(contentsDTO.getTitle() != null){
            sqlSession.insert(NAMESPACE+".insertContentsInfo", contentsDTO);
            return true;
        }
        else{
            return false;
        }
    }

    public List<ContentsDTO> selectAllByCharacterId(int character_id){
        return sqlSession.selectList(NAMESPACE+".selectAllByCharacterId", character_id);
    }
}