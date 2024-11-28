package com.example.HyThon.apiPayload.code.status;

import com.example.HyThon.apiPayload.code.BaseErrorCode;
import com.example.HyThon.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    // 멤버 관려 에러
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 존재하지 않습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수입니다."),

    // 일기 관련 에러
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4001", "일기가 존재하지 않습니다."),
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4002", "소재 카테고리가 존재하지 않습니다."),
    EMOTION_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4003", "감정 카테고리가 존재하지 않습니다."),

    // 편지 관련 에러
    TRANSMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "TRANSMISSION4001", "편지가 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}