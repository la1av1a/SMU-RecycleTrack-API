package com.smu.smuenip.application.user;

import com.smu.smuenip.application.login.dto.ResponseDto;
import com.smu.smuenip.application.user.dto.UserImageUploadRequestDto;
import com.smu.smuenip.application.user.dto.UserReceiptResponseDto;
import com.smu.smuenip.application.user.dto.UserSetCommentRequestDto;
import com.smu.smuenip.infrastructure.config.CustomUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Api(tags = "영수증 업로드 등")
public interface UserControllerSwagger {

    @ApiOperation(value = "영수증을 업로드", notes = "영수증 업로드시 필요한 API")
    @ApiImplicitParam(name = "Authorization", dataType = "String", paramType = "header",
            value = "Bearer <token>")
    ResponseDto uploadImage(
            @RequestBody UserImageUploadRequestDto requestDTO,
            @AuthenticationPrincipal CustomUserDetails customUserDetails);

    @ApiOperation(value = "업로드한 영수증들 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "당신이 원하는 페이지"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "한 페이지에 몇 개나 보여줬으면 좋겠는지"),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "정렬"),
            @ApiImplicitParam(name = "Authorization", dataType = "String", paramType = "header",
                    value = "Bearer <token>"),
    })
    public List<UserReceiptResponseDto> getUploadedItems(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PageableDefault Pageable pageable);


    @ApiOperation(value = "업로드한 영수증에 코멘트 남기기")
    @ApiImplicitParam(name = "Authorization", dataType = "String", paramType = "header",
            value = "Bearer <token>")
    public ResponseDto<Void> setComment(
            @RequestBody UserSetCommentRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails);
}
