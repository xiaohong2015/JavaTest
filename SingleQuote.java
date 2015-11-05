import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SingleQuote {

	public void Test1_SwapQuote() {
		String result = ReadFile("D:\\index.html");		
		SwapQuote(result);
		System.out.println(result.replace("'", "''")); // 替换单引号！！！
	}
	public void Test2_StringReplace() {
		String str= "123------ABC------456";
		System.out.println(str);
		String re= "7T$R";
		re= java.util.regex.Matcher.quoteReplacement(re);
		System.out.println(str.replaceAll("ABC", re));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SingleQuote sq= new SingleQuote();
		sq.Test2_StringReplace();
	}
	
	// 读取文件
	public String ReadFile(String filename) {
		String result = "";
		/*
		 * Parser p; p= Parser.createParser(filename, "GBK"); HtmlPage hp= new
		 * HtmlPage(p); try { p.visitAllNodesWith(hp); } catch (ParserException
		 * e) { e.printStackTrace(); } return
		 * hp.getBody().toNodeArray().toString(); //
		 */
		/*
		 * 一般读取文件方式 File f= new File(filename); InputStream is= null; try { is=
		 * new FileInputStream(f); int t= -1; while((t= is.read())!= -1) {
		 * System.out.println(t); } } catch(IOException e) {
		 * e.printStackTrace(); } //
		 */

		File f = new File(filename);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), "GBK"));

			String str;
			while ((str = br.readLine()) != null) {
				result += str + "\n";
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// */

		return result;
	}

	// 单引号的下标  eg: select 'I''m a pig' from dual;  --输出I'm a pig
	public void SwapQuote(String str) {
		System.out.println(str.length()+ " ----------------------- Total Character00");
		char c= '\'';
		for(int i=0; i<str.length(); i++) {
			if(c== str.charAt(i)) {
				System.out.println(i+ " ----------------------- the index");
			}
		}
	}
	
	// next
	
}
