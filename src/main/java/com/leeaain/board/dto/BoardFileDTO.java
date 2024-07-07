package com.leeaain.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO {
    private Long id;
    private Long boardId;
    private String originalFileName; // 사용자가 올리는 원본 파일명
    private String storedFileName; // db에서의 유니크함을 보장하기 위한 가공된 파일명
}
