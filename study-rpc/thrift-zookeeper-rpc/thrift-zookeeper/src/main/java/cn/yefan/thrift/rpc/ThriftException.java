package cn.yefan.thrift.rpc;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class ThriftException extends RuntimeException {

    public ThriftException() {
        super();
    }

    public ThriftException(String msg) {
        super(msg);
    }

    public ThriftException(Throwable e) {
        super(e);
    }

    public ThriftException(String msg, Throwable e) {
        super(msg, e);
    }


}
