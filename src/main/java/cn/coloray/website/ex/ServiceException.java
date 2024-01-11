package cn.coloray.website.ex;

import cn.coloray.website.web.ServiceCode;

//创建此异常处理类希望把出现异常的信息能显示自己描述的文字，以下构造方法就可以实现。
public class ServiceException extends RuntimeException{
    private ServiceCode serviceCode;
    public ServiceException(ServiceCode serviceCode,String msg){
        super(msg);
        this.serviceCode = serviceCode;
    }

    public ServiceCode getServiceCode(){
        return serviceCode;
    }
}
