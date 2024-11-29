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
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "유저 이름은 필수 입력 값입니다."),
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 존재하는 유저 이름입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "MEMBER4003", "비밀번호가 틀렸습니다."),

    // 일기 관련 에러
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4001", "일기가 존재하지 않습니다."),
    SUBJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4002", "소재 카테고리가 존재하지 않습니다."),
    EMOTION_NOT_FOUND(HttpStatus.NOT_FOUND, "DIARY4003", "감정 카테고리가 존재하지 않습니다."),
    NOT_TODAY_DIARY(HttpStatus.BAD_REQUEST, "DIARY4004", "오늘 작성한 일기가 아닙니다."),
    DIARY_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "DIARY4005", "이미 오늘 일기를 작성했습니다."),
    MEMBER_NOT_MATCH(HttpStatus.BAD_REQUEST, "DIARY4006", "해당 유저의 일기가 아닙니다."),

    // 편지 관련 에러
    TRANSMISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "TRANSMISSION4001", "전송할 편지가 존재하지 않습니다."),
    RECEIVED_NOT_FOUND(HttpStatus.NOT_FOUND, "TRANSMISSION4002", "받은 편지가 존재하지 않습니다."),

    // 토큰 관련 에러
    TOKEN_EMPTY(HttpStatus.UNAUTHORIZED, "MEMBER4020", "토큰이 비어있습니다."),
    WRONG_TYPE_SIGNATURE(HttpStatus.UNAUTHORIZED, "MEMBER4021", "잘못된 JWT 서명입니다."),
    NOT_VALID_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER4022", "해당 토큰은 유효한 토큰이 아닙니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "MEMBER4023", "토큰이 만료되었습니다."),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER4024", "지원되지 않는 JWT 토큰입니다."),
    EMPTY_CLAIMS_TOKEN(HttpStatus.UNAUTHORIZED, "MEMBER4025", "JWT Claims 문자열이 비어있습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "MEMBER4026", "올바르지 않은 JWT 토큰입니다."),
    JWT_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4027", "유효한 JWT 토큰이 없습니다."),
    NO_MATCH_REFRESHTOKEN(HttpStatus.BAD_REQUEST, "MEMBER4028", "일치하는 리프레시 토큰이 존재하지 않습니다."),
    ;

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