package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    //시작 페이지 번호  this.end - 9
    private int start;

    //끝 페이지 번호 (int)(Math.ceil (this.page / 10.0))
    private int end;

    //이전 페이지의 존재 여부
    private boolean prev;
    //다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;


    //PageResponseDTO 는 여러 정보를 생성자를 이용해 받아서 처리하는 것이 안전하다.
    // 예를 들면 PageRequestDTO 의 page, size, TodoDTO 의 목록 데이터와 전체 데이터의 개수도 필요


    @Builder(builderMethodName = "withAll") // 빌더 메서드의 이름을 사용자가 원하는 대로 지정한다.
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10; // 마지막 페이지


        this.start = this.end - 9;  // 시작 페이지

        // 마지막 페이지의 경우 한페이지에 글이 다섯개 세개 이렇게 나올 수도 있으니까
    

        int last = (int) (Math.ceil(total / (double) size));

        this.end = end > last ? last : end;// 마지막 페이지는 앞에서 구한 last 값 보다 작은 경우에 last 값이 end 가 되어야만 함
        

        // 이전 페이지의 존재여부는 시작페이지(start)가 1이라면 무조건 트루
        // 다음(naxt)은 마지막 페이지(end)와
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}
