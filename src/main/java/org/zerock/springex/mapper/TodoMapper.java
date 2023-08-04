package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {


    // 연습용 걍
    String getTime();

    // 연습용 직접 추가하는거
    void insert(TodoVO todoVO);

    // 글 전체 조회
    List<TodoVO> selectAll();


    // 글 하나 조회
    TodoVO selectOne(Long tno);


    void update(TodoVO todoVO);


    void delete(Long tno);


    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);

}
