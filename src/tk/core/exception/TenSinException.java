package tk.core.exception;

public class TenSinException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3854695572673033881L;


	public TenSinException(String msg,Throwable cause){
		super(msg,cause);
	}
	public TenSinException(String msg){
		super(msg);
	}
	public TenSinException(Throwable cause){
		super(cause);
	}
}
