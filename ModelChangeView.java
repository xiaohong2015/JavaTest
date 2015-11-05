import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author xiaohong
 * @date 2015年11月5日
 * 生成  editform.jsp 文件
 */
public class ModelChangeView extends JFrame {
	
	// xiaohong the file name
	private String str_editform= "editform.jsp";

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new ModelChangeView().setVisible(true);
	}

	public ModelChangeView() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.DragNow();
	}

	JPanel jp;

	public void DragNow() {
		jp = new JPanel();
		jp.setBackground(Color.green);
		getContentPane().add(jp, BorderLayout.CENTER);
		setSize(300, 300);
		setTitle("生成 view.jsp 文件");

		new DropTarget(jp, DnDConstants.ACTION_MOVE, new DropTargetListener() {

			@Override
			public void dropActionChanged(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub

			}

			@SuppressWarnings("unchecked")
			@Override
			public void drop(DropTargetDropEvent dtde) {
				try {
					if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
						dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
						List<File> list_file;
						list_file = (List<File>) dtde.getTransferable()
								.getTransferData(DataFlavor.javaFileListFlavor);

						// transaction
						/*
						 * String temp = ""; for (File f : list_file) { temp +=
						 * f.getAbsolutePath() + "\n"; } Say(temp); //
						 */
						GetListFile(list_file);

						dtde.dropComplete(true);
					}
				} catch (UnsupportedFlavorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void dragOver(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragExit(DropTargetEvent dte) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragEnter(DropTargetDragEvent dtde) {
				// TODO Auto-generated method stub

			}
		});
	}

	public void GetListFile(List<File> list_file) {
		for(File f: list_file) {
			GenerateEditform(SwapNow(f), f.getParentFile().getAbsolutePath());
		}
	}
	
	public void GenerateEditform(String content, String path) {
		File f= new File(path+ str_editform);
		if(null== f|| !f.exists()) {
			try {
				f.createNewFile();
				BufferedWriter bw= new BufferedWriter(new FileWriter(f));
				bw.write(content);
				
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String SwapNow(File f) {
		String str = "";

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(f));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				str += temp+ "\n";
			}						
			
			// xiaohong transaction
			
			/*
			String str="2007年12月11日Qprivate.*;EWindows 95号【大师傅】yyzprivate int i;z---adfecfeabewrecwdabewrwrciew";
			String[] reg= new String[]{"[年月日]", "^[0-9]*$", "ab.*c", "Windows [0-9]", "private[^;]*;", "【[^【】]*】"};
			Pattern p= null;
			
			p= Pattern.compile(reg[5]);
			Matcher m= p.matcher(str);
			
			System.out.println("--------------------");
			while(m.find()) {
				System.out.println(m.group(0));
			}
			*/
			
			List<String> list_result= new ArrayList<String>();
			// 字符串处理
			String reg1= "private[^;]*;"; // column
			String reg2= "【[^【】]*】"; // comment			
			Pattern p=null;
			Matcher m= null;
			String buf= "";
			
			int i= 0;
			p= Pattern.compile(reg1);
			m= p.matcher(str);
			while(m.find()) {
				i++;
				buf= m.group();
				buf= buf.split(" ")[2];
				list_result.add(buf.substring(0, buf.length()-1));
			}
			
			p= Pattern.compile(reg2);
			m= p.matcher(str);
			while(m.find()&& i> 0) {
				i--;
				buf= m.group();
				list_result.add(buf.substring(1, buf.length()-1));
			}
			//System.out.println(list_result.toString());
			
			str= "<%@ page pageEncoding=\"GB18030\"%>\n"
					+"<CENTER><H2><c:if test=\"${viewflag=='false'}\">${empty command.id?\"新增\":\"编辑\"}</c:if>GATHER_PHONE_MESSAGE</H2></CENTER>\n"
					+"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%;background-color:#ffffff;\" class=\"tableView\">\n"
					+"<!-- formbody begin -->\n";
			
			int j= list_result.size()/2;
			for(i=0; i< j; i++) {
				str+= "<TR><jk:input label=\""+ list_result.get(j+ i)+"\" title=\""+ list_result.get(j+ i)+"\" name=\""+ list_result.get(i)+"\" datatype=\"\" styleclass=\"${_styleclass}\" view=\"${viewflag}\"/></TR>\n";
			}
			str+= "<!-- formbody end -->\n</table>\n";

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return str;
	}

	public void Say(String str) {
		JOptionPane.showMessageDialog(null, str);
	}

}
