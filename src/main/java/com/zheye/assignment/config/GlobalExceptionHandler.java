package com.zheye.assignment.config;

import com.zheye.assignment.constant.BusinessException;
import com.zheye.assignment.dto.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import static com.zheye.assignment.constant.ResultCode.INTERNAL_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultVO BusinessExceptionHandler(HttpServletRequest req, BusinessException e) {
        log.error("business exception found: {}", e.getMessage());
        return new ResultVO(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO ExceptionHandler(HttpServletRequest req, Exception e) {
        log.error(e.getMessage());
        return new ResultVO(INTERNAL_ERROR);
    }

}
