package PageFactory;

public class RegLocators {

	//SignUp Locator
	public String signUp         = "//a[text()='Sign up']";
	public String logIn          = "//a[text()='Log in']";
	public String fullName       = "//section[@label=\"Full Name\"]/section/input";
	public String emaiId         = "//section[@label=\"Email\"]/section/input";
	public String checkBox       = "//input[@type=\"checkbox\"]";
	public String createAccount  = "//span[text()='Create account']";
	
	//LogIn Locator
	public String phoneNumber    = "//input[@placeholder=\"Phone\"]";
	public String sentOTP        = "//span[text()='Send One Time Password']";
	public String profile        = "//a[text()='Add restaurant']//following::div[1]";
	public String logout         = "//div[text()='Log out']";
	
}
