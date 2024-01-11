package cn.coloray.website.web;

import cn.coloray.website.ex.ServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * 统一的响应结果类型
 *
 * @author Phuoc
 * @version 0.0.1
 */
@Data
public class JsonResult<T> implements Serializable {
    @ApiModelProperty("业务状态码")
    private Integer state;

    @ApiModelProperty("业务信息")
    private String message;

    @ApiModelProperty("业务返回数据")
    private T data;


    public static JsonResult<Void> ok(){
        return ok(null);
    }
    public static <T> JsonResult<T> ok(Object data){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(ServiceCode.OK.getValue());
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult<Void> fail(ServiceCode serviceCode,String message){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setState(serviceCode.getValue());
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static JsonResult<Void> fail(ServiceException e){
        return fail(e.getServiceCode(),e.getMessage());
    }
}
