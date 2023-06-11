package mtyx.result;

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

    //设置数据,返回对象的方法
    public static<T> Result<T> build(T data,Integer code,String message) {
        //创建Result对象，设置值，返回对象
        Result<T> result = new Result<>();
        //判断返回结果中是否需要数据
        if(data != null) {
            //设置数据到result对象
            result.setData(data);
        }
        //设置其他值
        result.setCode(code);
        result.setMessage(message);
        //返回设置值之后的对象
        return result;
    }

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
        return build(data, ResultCodeEnum.FAIL);
    }

}
