package result;

import lombok.Data;

@Data
public class Result<T> {
    // 状态码
    private Integer code;
    // 信息
    private String message;
    // 数据
    private T data;

    private Result() {}

    // 构建返回结果
    public static<T> Result<T> build (T data, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());

        return result;
    }

    // 业务成功
    public static<T> Result<T> ok (T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    // 业务失败
    public static<T> Result<T> fail (T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

}
