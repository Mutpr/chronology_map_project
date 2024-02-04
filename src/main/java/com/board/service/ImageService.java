package com.board.service;


import com.board.model.ImageDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private SqlSession sqlsession;
    private final String NAMESPACE = "mapper.imageMapper";
    //userid, imageid

   @Autowired
   public ImageService(SqlSession sqlsession){
        this.sqlsession = sqlsession;
    }

    //이미지 정보 캐릭터 번호 기반으로 받아오는 메소드
    public ImageDTO selectImageByCharacterID(int characterId){
        return sqlsession.selectOne(NAMESPACE+"selectImageById", characterId);
    }
    //이미지 파일 저장하는 메소드
}
