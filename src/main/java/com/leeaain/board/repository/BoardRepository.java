package com.leeaain.board.repository;

import com.leeaain.board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    // mybatiss가 제공하는 SqlSessionTemplate 객체를 이용해서 mapper를 호출한다.
    private final SqlSessionTemplate sql;

    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
    }

    public List<BoardDTO> findAll() {
        return sql.selectList("Board.findAll");
    }

    public void updateHits(Long id) {
        // db에 저장되어 있는 조회수 값을 바꾼다.
        // 매퍼 파일에서 Board.updateHits로 정의된 update 쿼리를 실행
        sql.update("Board.updateHits", id);
    }

    public BoardDTO findById(Long id) {
        // 매퍼 파일에서 Board.findById로 정의된 select 쿼리를 실행
        return sql.selectOne("Board.findById", id);
    }

    public void update(BoardDTO boardDTO) {
        System.out.println("레포지토리에서 확인하는 boardDTO: " + boardDTO);
        sql.update("Board.update", boardDTO);
    }

    public void delete(Long id) {
        sql.delete("Board.delete", id);
    }
}
