package org.zerock.springex.service;

import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll();


    TodoDTO getOne(Long tno);


    public void modify(TodoDTO todoDTO);

    public void remove(Long tno);


    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);


}
