package mtyx.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import mtyx.result.Result;

@ControllerAdvice  // AOP
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)  // 当为异常时，作为切面加入，进行处理
    @ResponseBody  // 将返回值以json数据返回
    public Result error (Exception e) {
        e.printStackTrace();
        return Result.fail(null);
    }

    // 自定义异常
    @ExceptionHandler(MtyxException.class)  // 当为异常时，作为切面加入，进行处理
    @ResponseBody  // 将返回值以json数据返回
    public Result error (MtyxException e) {
        e.printStackTrace();
        return Result.build(null, e.getCode(), e.getMessage());
    }
}
