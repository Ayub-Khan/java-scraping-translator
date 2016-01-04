import com.jaunt.*;
import com.jaunt.component.*;
import java.io.*;

public class Translator 
{
	private UserAgent userAgent;
	
	Translator()
	{
		userAgent = new UserAgent();
		try {
			userAgent.visit("http://translate.google.com");
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetTextForTranslation(String unTranslatedText)
	{
		Form form = this.userAgent.doc.getForm(0);
		try {
			form.setTextArea("text", unTranslatedText);
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SetTanslationLanguage(String language_code)
	{
		Form form = this.userAgent.doc.getForm(0);
		try {
			form.set("tl", language_code);
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void submit()
	{
		try {
			userAgent.doc.submit("Translate");
		} catch (SearchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getTranslation()
	{
		String translation="";
		try {
			translation = userAgent.doc.findFirst("<span id=result_box>").getChildElements().get(0).getText();
		} catch (NotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return translation;
	}
	
	public static void main(String[] args)
	{
		String line = "you only live once.";
		String language_code = "es";
		Translator translator = new Translator();
		translator.SetTextForTranslation(line);
		translator.SetTanslationLanguage(language_code);
		translator.submit();
		String translation = translator.getTranslation();
		System.out.println("Line to Translate : " + line);
		System.out.println("Translation Language : " + language_code);
		System.out.println("Translated Line : " + translation);
		
	 }
}
