package tk.core.exception;

public class ExceptionCreator {
	public static TenSinException create(Throwable e){
		return new TenSinException(e);
	}
	public static TenSinException create(String cause){
		return new TenSinException(cause);
	}
}
