package com.leeaain.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
// 필드 정의
public class BoardDTO {
    private Long id; // 글번호
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private String createdAt;

    private int fileAttached;
    private MultipartFile boardFile;
}
