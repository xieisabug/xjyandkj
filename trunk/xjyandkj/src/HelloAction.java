import com.opensymphony.xwork2.ActionSupport;

public class HelloAction extends ActionSupport {
	private static final long serialVersionUID = 8849404110156371731L;

	@Override
	public String execute() throws Exception {
		System.out.println("HelloAction:execute()...");
		return "success";
	}
}
